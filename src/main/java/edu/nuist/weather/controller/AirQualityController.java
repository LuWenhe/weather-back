package edu.nuist.weather.controller;

import edu.nuist.weather.annotation.LogAnnotation;
import edu.nuist.weather.service.AirQualityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/air")
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    @Value("${QWeatherAPI.key}")
    private String key;

    @LogAnnotation(operator = "实时空气质量")
    @GetMapping("now")
    public Object nowAirQuality(@RequestParam("location") String location) {
        return airQualityService.airQualityNow(location, key, true);
    }

}
