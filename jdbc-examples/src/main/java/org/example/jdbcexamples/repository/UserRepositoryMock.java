package org.example.jdbcexamples.repository;

import org.example.jdbcexamples.dox.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMock {

    User save(User user);

    User findById(String id);

}
