package test.weather.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import test.weather.Model.GetCurrentLocation;
import test.weather.Security.CustomUserDetails;
import test.weather.Service.UserLocationService;



@Controller
public class WeatherController {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserLocationService userLocationService;

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String getWeather(
        @RequestParam(value = "lat", required = false) Double latitude,
        @RequestParam(value = "lon", required = false) Double longitude,
        Model model) {

        // If the latitude and longitude parameters are not provided, set default values
        double lat = (latitude != null) ? latitude : 13.7563; // Default latitude for Bangkok
        double lon = (longitude != null) ? longitude : 100.5018; // Default longitude for Bangkok

        // Construct the API URL with the provided or default coordinates
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric";
        String url_locate = "https://geocode.maps.co/reverse?lat="+lat+"&lon="+lon;
        // Use RestTemplate to fetch the weather data
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        GetCurrentLocation getCurrentLocation = restTemplate.getForObject(url_locate, GetCurrentLocation.class);

        if (response != null) {
            // Extract the city name from the timezone field
            String[] timezoneParts = response.getTimezone().split("/");
            String city = timezoneParts.length > 1 ? timezoneParts[1] : "Unknown City";
            model.addAttribute("city", city); // Add the extracted city name to the model
        }

        // Add the complete weather data to the model
        model.addAttribute("weather", response);
        model.addAttribute("location", getCurrentLocation);


        // Return the name of the view template, which is 'weather' in this case
        return "weather";
    }

    @GetMapping("/login")
	public String login() {
		return "login";
	}

    @GetMapping("/bookmark")
    public String bookmark() {
        return "bookmark";
    }

    @GetMapping("/login/weather")
    public String loginSuccess(
        @RequestParam(value = "lat", required = false) Double latitude,
        @RequestParam(value = "lon", required = false) Double longitude,
        Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = null;

        // If the latitude and longitude parameters are not provided, set default values
        double lat = (latitude != null) ? latitude : 13.7563; // Default latitude for Bangkok
        double lon = (longitude != null) ? longitude : 100.5018; // Default longitude for Bangkok

        // Construct the API URL with the provided or default coordinates
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric";
        String url_locate = "https://geocode.maps.co/reverse?lat="+lat+"&lon="+lon;
        // Use RestTemplate to fetch the weather data
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        GetCurrentLocation getCurrentLocation = restTemplate.getForObject(url_locate, GetCurrentLocation.class);
        
        if (response != null) {
            // Extract the city name from the timezone field
            String[] timezoneParts = response.getTimezone().split("/");
            String city = timezoneParts.length > 1 ? timezoneParts[1] : "Unknown City";
            model.addAttribute("city", city); // Add the extracted city name to the model
        }

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            userId = userDetails.getUserId();
            model.addAttribute("userId", userId);
            userLocationService.saveUserLocation(lat, lon, userId);  // Retrieve the userId
        }
         

        // Add the complete weather data to the model
        model.addAttribute("weather", response);
        model.addAttribute("location", getCurrentLocation);
        // Return the name of the view template, which is 'weather' in this case
        return "weather_login"; 
    }

    
    
}

