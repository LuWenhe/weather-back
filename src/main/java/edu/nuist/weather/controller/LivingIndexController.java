package edu.nuist.weather.controller;

import edu.nuist.weather.annotation.LogAnnotation;
import edu.nuist.weather.service.LivingIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/living")
public class LivingIndexController {

    @Autowired
    private LivingIndexService livingIndexService;

    @Value("${QWeatherAPI.key}")
    private String key;

    @LogAnnotation(operator = "天气指数")
    @GetMapping("today")
    public Object livingIndex(@RequestParam("location") String location,
                              @RequestParam(value = "type", defaultValue = "0") String type) {
        return livingIndexService.livingIndex(key, location, type);
    }

}
