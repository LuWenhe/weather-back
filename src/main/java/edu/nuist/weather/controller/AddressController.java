package edu.nuist.weather.controller;

import edu.nuist.weather.annotation.LogAnnotation;
import edu.nuist.weather.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Value("${QWeatherAPI.key}")
    private String key;

    @Autowired
    private AddressService addressService;

    @LogAnnotation(operator = "地址转坐标和地区编码")
    @GetMapping("addressToCoordinate")
    public ResponseEntity<byte[]> hourlyForecastForTwentyFourHours(@RequestParam("address") String address) {
        return addressService.getGeocoderByAddress(address, key);
    }

}
