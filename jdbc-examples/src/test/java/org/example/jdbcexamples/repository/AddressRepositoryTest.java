package org.example.jdbcexamples.repository;

import lombok.extern.slf4j.Slf4j;

import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;
import org.example.jdbcexamples.dto.UserAddressDTO2;
import org.example.jdbcexamples.dto.UserAddressDTO3;
import org.example.jdbcexamples.dto.UserAddressDTO4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void findByUserId() {
        for(Address address : addressRepository.findByUserId("1294552833194438656")){
            log.debug(" debug: {}",address);
        }
    }

    @Test
    void findByAddressId() {
        log.debug("{}",addressRepository.findByAddressId("7"));
    }

    @Test
    void findAllAddressByUid() {
        UserAddressDTO3 userAddressDTO3 = addressRepository.findAllAddressByUid("1294552833194438656");
        System.out.println(userAddressDTO3.getUser().toString());
        for(Address address : userAddressDTO3.getAddresses()){
            System.out.println(address.toString());
        }
    }

    @Test
    void findAllUserAddresses() {
        for(UserAddressDTO4 userAddressDTO4 : addressRepository.findAllUserAddresses()){
            System.out.println(userAddressDTO4.toString());
        }
    }
}