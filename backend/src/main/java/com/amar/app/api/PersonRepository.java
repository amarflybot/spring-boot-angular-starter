package com.amar.app.api;

import com.amar.app.model.Person;
import com.amar.app.projection.AddressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by amarendra on 04/12/17.
 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/#projections-excerpts
 */
@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long>{

    Person findByName(@Param("name") String name);

    List<Person> findByAddress_Country(@Param("country") String country);

    @Override
    @Query("SELECT p FROM Person p")
    List<Person> findAll();
}
