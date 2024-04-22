package edu.nuist.weather.service;

import edu.nuist.weather.client.AirQualityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AirQualityService {

    @Autowired
    private AirQualityClient airQualityClient;

    public ResponseEntity<byte[]> airQualityNow(String location, String key, Boolean pollutant) {
        return airQualityClient.airQualityNow(location, key, pollutant);
    }

}
