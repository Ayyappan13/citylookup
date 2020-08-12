package com.citylookup.util

import com.citylookup.filter.CityLookUpFilter
import org.mockito.Mock
import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class CityLookupUtilTest extends Specification{


    CityLookUpUtil sut

    def setup(){
        sut = new CityLookUpUtil()

    }

    def "test file"() {
        given:
        def origin = "Boston"
        def destination = "Newark"
        def path = "config/city.txt"
        sut.combinedOriginDestination() >> "Boston, Newark"
        when:
        def response = sut.searchCity(path, origin, destination)

        then:
        noExceptionThrown()
    }

    def "test file - exception"() {
        given:
        def origin = "Boston"
        def destination = "Newark"
        def path = "config/city.txt"
        sut.combinedOriginDestination() >> "Boston, Newark"
        Files.lines(Paths.get(String.valueOf(DataUtil.getFile()))).count() >> 1
        when:
        def response = sut.searchCity(path, origin, destination)

        then:
        noExceptionThrown()
    }

}
