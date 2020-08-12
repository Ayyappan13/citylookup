package com.citylookup.util;

import com.citylookup.CityLookUpApplication;
import com.citylookup.constants.CityLookUpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.citylookup.constants.CityLookUpConstants.*;

@Service
public class CityLookUpUtil {

    private static final Logger log = LoggerFactory.getLogger(CityLookUpUtil.class);

    public String combinedOriginDestination(String origin, String destination){
        StringBuilder sb = new StringBuilder();
        sb.append(origin);
        sb.append(SPACE);
        sb.append(destination);
        log.info("Combined CityName : {}",sb.toString());
        return sb.toString();
    }

    public String searchCity(String filePath, String origin, String destination ) throws IOException {

        long cityCount = 0;
        String combinedCity = this.combinedOriginDestination(origin, destination);

        try {
            log.info("City.txt path {}",filePath);
            ClassLoader classLoader = new CityLookUpApplication().getClass().getClassLoader();
            File file = new File(classLoader.getResource(filePath).getFile());

            cityCount = Files.lines(Paths.get(String.valueOf(file)))
                    .filter(line -> line.equalsIgnoreCase(combinedCity)).count();
        }catch(IOException ioException){
            throw new IOException("IOException :: Issue while lookup City File");
        }

        log.info("Origin and Destination city count after lookup City-file : {}",cityCount);
        if(cityCount > 0){
            return YES;
        }else{
            return NO;
        }

    }

}
