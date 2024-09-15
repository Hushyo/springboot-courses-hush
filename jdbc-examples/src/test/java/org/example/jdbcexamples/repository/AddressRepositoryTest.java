package org.example.jdbcexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbcexamples.dox.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void findByUserId() {
        log.debug("1111");
        for(Address address : addressRepository.findByUserId("1284873941642883072")){
            System.out.println("asd"+address.toString());
            log.debug("address: {}",address);
        }
    }

}