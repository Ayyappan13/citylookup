package com.citylookup.repository;

import com.citylookup.model.CityLookUpDetails;
import com.citylookup.model.CityLookUpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityLookUpRepository extends JpaRepository<CityLookUpDetails, Integer> {

    CityLookUpDetails findByOriginAndDestination(String origin, String destination);

}
