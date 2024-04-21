package edu.nuist.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "address-service", url = "${QWeatherAPI.addressUrl}")
public interface AddressClient {

    @GetMapping(value = "v2/city/lookup")
    ResponseEntity<byte[]> getWSGeocoderByAddress(@RequestParam("location") String location,
                                                  @RequestParam("key") String key);

}
