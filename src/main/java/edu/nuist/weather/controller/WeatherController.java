package edu.nuist.weather.controller;

import edu.nuist.weather.annotation.LogAnnotation;
import edu.nuist.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Value("${QWeatherAPI.key}")
    private String key;

    @LogAnnotation(operator = "未来24小时逐小时预报")
    @GetMapping("24h")
    public Object hourlyForecastForTwentyFourHours(@RequestParam("location") String location) {
        return weatherService.hourlyForecastForTwentyFourHours(location, key);
    }

    @LogAnnotation(operator = "未来7天预报")
    @GetMapping("7d")
    public Object dailyForecastForSevenDays(@RequestParam("location") String location) {
        return weatherService.dailyForecastForSevenDays(location, key);
    }

    @LogAnnotation(operator = "未来10天预报")
    @GetMapping("10d")
    public Object dailyForecastForTenDays(@RequestParam("location") String location) {
        return weatherService.dailyForecastForTenDays(location, key);
    }

    @LogAnnotation(operator = "未来15天预报")
    @GetMapping("15d")
    public Object dailyForecastForFifteenDays(@RequestParam("location") String location) {
        return weatherService.dailyForecastForFifteenDays(location, key);
    }

}
