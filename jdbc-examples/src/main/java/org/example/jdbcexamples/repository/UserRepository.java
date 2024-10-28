package org.example.jdbcexamples.repository;

import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String> {


    @Query("select * from user u where u.id=:id")
    User findUserById(String id);


    @Query("select * from user u where u.name=:name")
    User findByName(String name);

    @Modifying
    @Query("update user u set name=:name where u.id=:id ")
    void updateUserById(String id, String name);


    @Query("select * from address a where a.user_id=:userId")
    List<Address> findByUserId(String userId);

    @Modifying
    @Query("update user u set name=:name where id=:id")
    void updateUser(String name,String id);

    @Modifying
    @Query("delete from user u where u.id=:id")
    void deleteUserById(String id);

    @Query("select * from user;")
    List<User> findAll();

    @Query("""
    select * from user u order by u.id desc
    limit :#{#pageable.offset},:#{#pageable.pageSize};
    """)
    List<User> findByPage(Pageable pageable);





}
