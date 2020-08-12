package com.citylookup.service;

import com.citylookup.configuration.CityLookUpConfiguration;
import com.citylookup.constants.CityLookUpConstants;
import com.citylookup.model.CityLookUpDetails;
import com.citylookup.repository.CityLookUpRepository;
import com.citylookup.util.CityLookUpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.citylookup.constants.CityLookUpConstants.*;

@Service
public class CityLookUpService {

    private static final Logger log = LoggerFactory.getLogger(CityLookUpService.class);

    @Autowired
    private CityLookUpRepository cityLookUpRepository;

    @Autowired
    private CityLookUpConfiguration cityLookUpConfiguration;

    @Autowired
    private CityLookUpUtil cityLookUpUtil;

    public String isCityConnectedFromDB(String origin, String destination){

        log.info("Origin is : {}",origin);
        log.info("Destination is : {}",destination);

        CityLookUpDetails cityLookUpDetails = cityLookUpRepository.findByOriginAndDestination(origin, destination);

        if(cityLookUpDetails != null){
            return cityLookUpDetails.getConnected();
        }else{
            return NO;
        }
    }

    public String isCityConnectedFromFile(String origin, String destination) throws IOException {
        log.info("Origin is : {}",origin);
        log.info("Destination is : {}",destination);

        String isConnected = cityLookUpUtil.searchCity(cityLookUpConfiguration.getFilePath(), origin, destination);
        log.info("Is connected : {}",isConnected);

        return isConnected;
    }

}
