package com.citylookup.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CityLookUpConfiguration {

    @Value("${cityfilepath}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "CityLookUpConfiguration{" +
                "filePath='" + filePath + '\'' +
                '}';
    }


}
