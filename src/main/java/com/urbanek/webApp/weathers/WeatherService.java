package com.urbanek.webApp.weathers;

import com.urbanek.webApp.daos.Employee;
import com.urbanek.webApp.exceptions.EmployeeCityNotFound;
import com.urbanek.webApp.exceptions.EmployeeNotFoundException;
import com.urbanek.webApp.repositories.EmployeeRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

@RestController
@Log
public class WeatherService {
    private final String KEY = "6b2032e377bdf6347ed6c439eb9902d3";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final String EMPLOYEE_NOT_FOUND  = "Couldn't find employee with id - ";
    @Autowired
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder builder){
        restTemplate = builder.build();
    }

    @GetMapping("/weather")
    public String getWeatherFromEmployeeCity(@RequestParam long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + employeeId));

        String encodedCity = encodeCity(employee.getCity());
        log.info("Encoded city " + encodedCity);
        String url = String.format("%s?q=%s&appid=%s", BASE_URL, encodedCity, KEY);
        log.info("Generated URL " + url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    private String encodeCity(String city){
        String encodedCity = "";
        if (city != null) {
            try {
                city = Normalizer
                        .normalize(city, Normalizer.Form.NFD);
                encodedCity = URLEncoder.encode(city, String.valueOf(StandardCharsets.UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new EmployeeCityNotFound("employees doesen't have asseinted city");
        }
        return encodedCity;
    }
}
