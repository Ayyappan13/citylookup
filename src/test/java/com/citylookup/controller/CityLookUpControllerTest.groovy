package com.citylookup.controller

import com.citylookup.constants.CityLookUpConstants
import com.citylookup.contoller.CityLookUpController
import com.citylookup.service.CityLookUpService
import com.citylookup.service.CityLookUpServiceTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class CityLookUpControllerTest extends Specification {

    @Shared
    String HOST = System.getProperty("server.host") ? System.getProperty("server.host") : "http://localhost"
    @Shared
    String PORT = System.getProperty("server.port") ? System.getProperty("server.port") : "8080"
    @Shared
    String URL = HOST + ":" + PORT + "/"

    CityLookUpService cityLookUpService
    CityLookUpController cityLookUpController
    HttpServletRequest httpServletRequest
    MockMvc mockMvc

    def setup(){
        cityLookUpService = Mock()
        cityLookUpController = new CityLookUpController(cityLookUpService: cityLookUpService)
        httpServletRequest = Mock()
        mockMvc = standaloneSetup(cityLookUpController).build()

    }

    def"check FILE if city connected -yes"(){
        given:
        def localURL = URL+"/connected?origin=Boston&destination=Newark"
        cityLookUpService.isCityConnectedFromFile(_ as String, _ as String) >> CityLookUpConstants.YES

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString == CityLookUpConstants.YES

    }

    def"check FILE if city connected - no"(){
        given:
        //Chicago is not there in the destination list.
        def localURL = URL+"/connected?origin=Boston&destination=Chicago"
        cityLookUpService.isCityConnectedFromFile(_ as String, _ as String) >> CityLookUpConstants.NO

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString == CityLookUpConstants.NO

    }

    def"check FILE if city connected - exception"(){
        given:
        //Chicago is not there in the destination list.
        def localURL = URL+"/connected?origin=Boston&destination=Chicago"
        cityLookUpService.isCityConnectedFromFile(_ as String, _ as String) >> { throw new Exception() }

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        Throwable throwable = thrown(Exception)
        throwable.getMessage() != null

    }

    def"check DB if city connected -yes"(){
        given:
        def localURL = URL+"connectedFromDB?origin=Boston&destination=Newark"
        cityLookUpService.isCityConnectedFromDB(_ as String, _ as String) >> CityLookUpConstants.YES

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString == CityLookUpConstants.YES

    }

    def"check DB if city connected - no"(){
        given:
        //Chicago is not there in the destination DB.
        def localURL = URL+"connectedFromDB?origin=Boston&destination=Chicago"
        cityLookUpService.isCityConnectedFromDB(_ as String, _ as String) >> CityLookUpConstants.NO

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString == CityLookUpConstants.NO

    }

    def"check DB if city connected - exception"(){
        given:
        //contentType is missing
        def localURL = URL+"connectedFromDB?origin=Boston&destination=Chicago"
        cityLookUpService.isCityConnectedFromDB(_ as String, _ as String) >> { throw new Exception() }

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        Throwable throwable = thrown(Exception)
        throwable.getMessage() != null

    }

}
