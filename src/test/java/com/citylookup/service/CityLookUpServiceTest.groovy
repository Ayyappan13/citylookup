package com.citylookup.service

import com.citylookup.configuration.CityLookUpConfiguration
import com.citylookup.constants.CityLookUpConstants
import com.citylookup.repository.CityLookUpRepository
import com.citylookup.util.CityLookUpUtil
import com.citylookup.util.DataUtil
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class CityLookUpServiceTest extends Specification {

    CityLookUpService cityLookUpService
    CityLookUpUtil cityLookUpUtil
    CityLookUpRepository cityLookUpRepository
    CityLookUpConfiguration cityLookUpConfiguration
    MockMvc mockMvc


    def setup(){
        cityLookUpUtil = Mock()
        cityLookUpRepository = Mock()
        cityLookUpConfiguration = Mock()
        cityLookUpService = new CityLookUpService(cityLookUpUtil: cityLookUpUtil,
                cityLookUpRepository: cityLookUpRepository, cityLookUpConfiguration: cityLookUpConfiguration)
        mockMvc = standaloneSetup(cityLookUpService).build()
    }

    def "check File if city connected - yes"(){
        given:
        def origin = "Boston"
        def destination = "Newark"
        cityLookUpUtil.searchCity(_ as String, _ as String, _ as String) >> CityLookUpConstants.YES
        when:
        cityLookUpService.isCityConnectedFromFile(origin, destination)
        then:
        noExceptionThrown()
    }

    def "check File if city connected - no"(){
        given:
        def origin = "Boston"
        def destination = "Chicago"
        cityLookUpUtil.searchCity(_ as String, _ as String, _ as String) >> CityLookUpConstants.NO
        when:
        cityLookUpService.isCityConnectedFromFile(origin, destination)
        then:
        noExceptionThrown()
    }

    def "check File if city connected - exception"(){
        given:
        def origin = "Boston"
        def destination = "Chicago"
        cityLookUpUtil.searchCity(_ as String, _ as String, _ as String) >> { throw new Exception() }
        when:
        def response = cityLookUpService.isCityConnectedFromFile(origin, destination)
        then:
        response == null
    }

}
