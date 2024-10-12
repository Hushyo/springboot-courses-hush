package org.example.jdbcexamples.repository;

import org.example.jdbcexamples.dox.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    @Query("select * from user;")
    List<User> findAll();

    @Query("""
    select * from user u order by u.id desc
    limit :#{#pageable.offset},:#{#pageable.pageSize};
    """)
    List<User> findByPage(Pageable pageable);



}
