package com.amar.app;

import com.amar.app.api.AddressRepository;
import com.amar.app.api.PersonRepository;
import com.amar.app.model.Address;
import com.amar.app.model.Person;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    DataFactory dataFactory() {
        return new DataFactory();
    }
    @Bean
    CommandLineRunner commandLineRunner(final AddressRepository addressRepository,
                                        final PersonRepository personRepository,
                                        final DataFactory dataFactory) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(final String... strings) throws Exception {
                addressRepository.save(new Address("asdasd","india"));
                final Address address = addressRepository.findOne(1l);
                Arrays.asList("spain","hungary","brazil","srilanka")
                        .forEach( name -> {
                            addressRepository.save(new Address(name+"123", name));
                        });
                final Person person = new Person("Amar", "Amar");
                person.setAddress(address);
                personRepository.save(person);

                Arrays.asList("Vicky","Alka","123","456")
                        .forEach(name -> {
                            Person person1 = new Person(name,name);
                            person1.setAddress(addressRepository.findOne(3l));
                            personRepository.save(person1);
                        });

                for (int i = 0; i < 100; i++) {
                    final Address address1 = addressRepository
                            .save(new Address(dataFactory.getStreetName(), dataFactory.getCity()));
                    Person person1 = new Person(dataFactory.getFirstName(), dataFactory.getLastName());
                    person1.setAddress(address1);
                    personRepository.save(person1);


                }

                personRepository.findAll().forEach(System.out::println);
            }
        };
    }
}
