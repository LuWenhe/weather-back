package edu.nuist.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "air-quality-service", url = "${QWeatherAPI.weatherUrl}")
public interface AirQualityClient {

    @GetMapping(value = "/airquality/v1/now/{location}")
    ResponseEntity<byte[]> airQualityNow(@PathVariable("location") String location,
                                         @RequestParam(value = "key") String key,
                                         @RequestParam(value = "pollutant", defaultValue = "true") Boolean pollutant);

}
