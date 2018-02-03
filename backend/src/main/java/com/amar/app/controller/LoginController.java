package com.amar.app.controller;

import com.amar.app.api.PersonRepository;
import com.amar.app.service.ExternalAPIService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by amarendra on 03/02/18.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class LoginController {

    private final PersonRepository personRepository;
    private MessageDigest messageDigest;
    private final ExternalAPIService externalAPIService;

    public LoginController(final PersonRepository personRepository, final ExternalAPIService externalAPIService) {
        this.personRepository = personRepository;
        this.externalAPIService = externalAPIService;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Hash> login(@RequestBody Person person){

        final com.amar.app.model.Person byName = personRepository.findByName(person.getUsername());
        if (byName != null) {
            final String password = byName.getPassword();
            if (password.equals(person.getPassword())){
                // Login Successful
                byte[] digest = messageDigest.digest((byName.getName()+byName.getPassword()).getBytes());
                String b64url = Base64.encodeBase64URLSafeString(digest);
                Hash hash = new Hash(b64url);
                externalAPIService.addInPersonMap(hash.getHash(), person);
                return ResponseEntity.ok().body(hash);

            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getAllHashes")
    public ResponseEntity getAllHashes(){
        return ResponseEntity.ok().body(externalAPIService.getAllHashes());
    }

    private class Hash {

        private String hash;

        public Hash(final String hash) {
            this.hash = hash;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(final String hash) {
            this.hash = hash;
        }
    }
}
