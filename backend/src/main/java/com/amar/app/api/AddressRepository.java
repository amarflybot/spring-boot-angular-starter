package com.amar.app.api;

import com.amar.app.model.Address;
import com.amar.app.projection.AddressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amarendra on 04/12/17.
 */
@RepositoryRestResource(excerptProjection = AddressProjection.class)
public interface AddressRepository extends JpaRepository<Address, Long>{

    Address findByCountry(@Param("country") String country);
}
