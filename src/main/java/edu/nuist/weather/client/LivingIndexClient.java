package edu.nuist.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "living-index-service", url = "${QWeatherAPI.weatherUrl}")
public interface LivingIndexClient {

    @GetMapping(value = "/v7/indices/1d")
    ResponseEntity<byte[]> livingIndex(@RequestParam("key") String key,
                                       @RequestParam("location") String location,
                                       @RequestParam("type") String type);

}
