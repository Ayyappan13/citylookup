package com.citylookup.util

import com.citylookup.CityLookUpApplication
import com.citylookup.model.CityLookUpDetails
import spock.lang.Specification

class DataUtil extends Specification {

    public static File getFile(){

        ClassLoader classLoader = new CityLookUpApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource("config/city.txt").getFile());

        return file
    }


    static CityLookUpDetails buildPaymentTransactionsResponse(String requestType){
        CityLookUpDetails cityLookUpDetails = new CityLookUpDetails();

        switch (requestType){
            case "VALID":
                cityLookUpDetails.setOrigin("Boston")
                cityLookUpDetails.setDestination("Newark")
                cityLookUpDetails.setConnected("yes")
                return cityLookUpDetails
                break
            case "NO_DATA_FOUND":
            return cityLookUpDetails
                break
        }

    }


}
