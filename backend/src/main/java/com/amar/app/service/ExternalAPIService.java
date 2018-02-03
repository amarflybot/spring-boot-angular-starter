package com.amar.app.service;

import com.amar.app.controller.Person;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarendra on 03/02/18.
 */
@Service
public class ExternalAPIService {

    private Map<String, Person> personMap;

    public ExternalAPIService() {
        personMap = new HashMap<>();
    }

    public void addInPersonMap(String hash, Person person){
        personMap.put(hash, person);
    }

    public Person getPersonByHash(String hash) {
        return personMap.get(hash);
    }

    public Map getAllHashes() {
        return personMap;
    }
}
