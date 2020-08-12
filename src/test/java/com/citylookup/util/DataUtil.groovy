package com.citylookup.util

import com.citylookup.CityLookUpApplication
import spock.lang.Specification

class DataUtil extends Specification {

    public static File getFile(){

        ClassLoader classLoader = new CityLookUpApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource("config/city.txt").getFile());

        return file
    }


}
