package test.weather.Util;


import java.util.HashMap;
import java.util.Map;

public class WeatherUtils {

    private static final Map<String, String> iconMap;

    static {
        // Initialize the map with OpenWeatherMap icon codes mapped to Font Awesome class names
        iconMap = new HashMap<>();
        iconMap.put("01d", "fa-sun");
        iconMap.put("01n", "fa-moon");
        iconMap.put("02d", "fa-cloud-sun");
        iconMap.put("02n", "fa-cloud-moon");
        iconMap.put("03d", "fa-cloud");
        iconMap.put("03n", "fa-cloud");
        iconMap.put("04d", "fa-cloud-meatball");
        iconMap.put("04n", "fa-cloud-meatball");
        iconMap.put("09d", "fa-cloud-rain");
        iconMap.put("09n", "fa-cloud-rain");
        iconMap.put("10d", "fa-cloud-sun-rain");
        iconMap.put("10n", "fa-cloud-moon-rain");
        iconMap.put("11d", "fa-poo-storm");
        iconMap.put("11n", "fa-poo-storm");
        iconMap.put("13d", "fa-snowflake");
        iconMap.put("13n", "fa-snowflake");
        iconMap.put("50d", "fa-smog");
        iconMap.put("50n", "fa-smog");
        // Add more mappings as necessary
    }

    public static String getWeatherIconClass(String iconCode) {
        // Return the Font Awesome class corresponding to the icon code, or a default icon if not found
        return iconMap.getOrDefault(iconCode, "fa-question-circle"); // 'fa-question-circle' is a placeholder for unknown conditions
    }
}

