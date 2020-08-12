package com.citylookup.filter

import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification

class CityLookUpFilterTest extends Specification {

    CityLookUpFilter sut

    MockHttpServletRequest request
    MockHttpServletResponse response
    MockFilterChain filterChain

    def setup(){
        sut = new CityLookUpFilter()

        request = new MockHttpServletRequest()
        response = new MockHttpServletResponse()
        filterChain = new MockFilterChain()
    }

    def 'doFilter'(){
        given:
        request.method = "GET"
        request.requestURI = "/testUrl"

        when:
        sut.doFilter(request, response, filterChain)

        then:
        noExceptionThrown()

    }

}
