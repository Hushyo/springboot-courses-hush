package org.example.experiment1.repository;

import org.example.experiment1.dox.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    @Query("select * from user")
    List<User> findAll();

    @Query("select * from user u where id = :id ")
    User findByUserId(String id);

    @Modifying
    @Query("update user set name = :name where id = :id")
    void updateUserNameById(String name,String id);

    @Modifying
    @Query("delete from user where id = :id")
    void deleteUserById(String id);
}
