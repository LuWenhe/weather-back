package edu.nuist.weather.service;

import edu.nuist.weather.client.AddressClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressClient addressClient;

    public ResponseEntity<byte[]> getGeocoderByAddress(String address, String key) {
        return addressClient.getWSGeocoderByAddress(address, key);
    }

}
