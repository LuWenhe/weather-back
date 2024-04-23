package edu.nuist.weather.service;

import edu.nuist.weather.client.LivingIndexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LivingIndexService {

    @Autowired
    private LivingIndexClient livingIndexClient;

    public ResponseEntity<byte[]> livingIndex(String key, String location, String type) {
        return livingIndexClient.livingIndex(key, location, type);
    }

}
