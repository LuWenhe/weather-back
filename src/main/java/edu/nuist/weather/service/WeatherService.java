package edu.nuist.weather.service;

import edu.nuist.weather.client.WeatherDayClient;
import edu.nuist.weather.client.WeatherHourClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherHourClient weatherHourClient;

    @Autowired
    private WeatherDayClient weatherDayClient;

    public ResponseEntity<byte[]> hourlyForecastForTwentyFourHours(String location, String key) {
        return weatherHourClient.hourlyForecastForTwentyFourHours(location, key);
    }

    public ResponseEntity<byte[]> dailyForecastForSevenDays(String location, String key) {
        return weatherDayClient.dailyForecastForSevenDays(location, key);
    }

    public ResponseEntity<byte[]> dailyForecastForTenDays(String location, String key) {
        return weatherDayClient.dailyForecastForTenDays(location, key);
    }

    public ResponseEntity<byte[]> dailyForecastForFifteenDays(String location, String key) {
        return weatherDayClient.dailyForecastForFifteenDays(location, key);
    }

}
