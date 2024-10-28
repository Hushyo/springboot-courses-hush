package org.example.jdbcexamples.repository;

import org.example.jdbcexamples.dox.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryMock extends CrudRepository<User, Long> {

    @Query("select * from user u where u.id=:id")
    User findById(String id);

    @Query("select name,id from user")
    List<User> getUsers();



}
