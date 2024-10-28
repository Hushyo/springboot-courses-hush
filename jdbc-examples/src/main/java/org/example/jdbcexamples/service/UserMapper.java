package org.example.jdbcexamples.service;


import org.example.jdbcexamples.dox.User;
import org.example.jdbcexamples.dto.UserAddressDTO;
import org.example.jdbcexamples.dto.UserAddressDTO2;
import org.example.jdbcexamples.dto.UserAddressDTO3;
import org.example.jdbcexamples.imp.UserAddressResultSetExtractor;
import org.example.jdbcexamples.imp.UserAddressRowMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper extends CrudRepository<UserAddressDTO,String> {

    //基于 address id 查询 user
    @Query("""
    select a.id as id, u.id as userId, u.name as name, a.detail as detail,
    a.create_time as createTime, a.update_time as updateTime
    from user u join address a on u.id = a.user_id
    where a.id = :aid;
    """)
    UserAddressDTO findUserByAddressId(String aid);


    //基于 address id 查询 user,不过这次分别把 user 和 address 封装起来后再次封装
    //此时需要修改行映射规则，自定义一个行映射规则，需要重写行映射方法
    @Query(value="select * from address a join user u on a.user_id = u.id where a.id=:aid",
    rowMapperClass = UserAddressRowMapper.class)
    UserAddressDTO2 findUserByAddressId2(String aid);

    //基于 user id 查询所有 address
    //此时是一对多，需要修改结果集查询方式
    @Query(value = "select * from address a join user u on a.user_id = u.id where u.id = :uid",
    resultSetExtractorClass = UserAddressResultSetExtractor.class)
    UserAddressDTO3 findAddressByUid(String uid);

    @Modifying
    @Query("update user u,address a set u.id = 11,a.user_id=11 where u.id =:id and a.user_id=:id")
    User updateUser(String id);

}
