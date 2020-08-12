package com.citylookup.contoller;

import com.citylookup.service.CityLookUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.citylookup.constants.CityLookUpConstants.*;


@RestController
public class CityLookUpController {

    private static final Logger log = LoggerFactory.getLogger(CityLookUpController.class);

    @Autowired
    private CityLookUpService cityLookUpService;

    @GetMapping("/connectedFromDB")
    public String isCityConnectedFromDB(@Param("origin") String origin, @Param("destination") String destination){

        MDC.put("Resource", IS_CITY_CONNECTED);
        MDC.put("Cities",origin+" - "+destination);
        log.info("Origin is : {}",origin);
        log.info("Destination is : {}",destination);

        String isCityConnected = cityLookUpService.isCityConnectedFromDB(origin.trim(), destination.trim());

        return isCityConnected;
    }

    @GetMapping("/connected")
    public String isCityConnectedFile(@Param("origin") String origin, @Param("destination") String destination) throws IOException {

        MDC.put("Resource", IS_CITY_CONNECTED);
        MDC.put("Cities",origin+" - "+destination);

        log.info("Origin is : {}",origin);
        log.info("Destination is : {}",destination);

        String isCityConnected = cityLookUpService.isCityConnectedFromFile(origin.trim(),destination.trim());

        log.info("Are cities {} and {} connected ? {}",origin,destination,isCityConnected);
        return isCityConnected;
    }

}
