package test.weather.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import test.weather.Controller.WeatherResponse;

@Service
public class WeatherService {

    @Value("${api.openweathermap.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/data/3.0/onecall";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeatherData(double latitude, double longitude) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", BASE_URL, latitude, longitude, apiKey);
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response;
    }
}
