package com.amar.app.api;

import com.amar.app.Application;
import com.amar.app.model.Address;
import com.amar.app.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by amarendra on 08/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    public void testFindAllAddresses(){
        final List<Person> personList = repository.findAll();
        System.out.println(personList);
    }

}