package com.citylookup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EXPLORE_CITY_DETAILS")
public class CityLookUpDetails {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer Id;

    @NonNull
    private String origin;

    @NonNull
    private String destination;

    private String connected;

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    public CityLookUpDetails(){

    }

    public CityLookUpDetails(String origin, String destination, String connected) {
        this.origin = origin;
        this.destination = destination;
        this.connected = connected;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "CityLookUpDetails{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", connected='" + connected + '\'' +
                '}';
    }

}
