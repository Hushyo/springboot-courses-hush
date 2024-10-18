package org.example.jdbcexamples.repository;

import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dto.UserAddressDTO2;
import org.example.jdbcexamples.dto.UserAddressDTO3;
import org.example.jdbcexamples.dto.UserAddressDTO4;
import org.example.jdbcexamples.imp.UserAddressResultSetExtractor;
import org.example.jdbcexamples.imp.UserAddressResultSetExtractor2;
import org.example.jdbcexamples.imp.UserAddressRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {


    @Query("select * from address a where a.user_id =:userId")
    List<Address> findByUserId(String userId);

    @Query(value="select * from address a join user u on a.user_id=u.id where a.id=:addressId;",
    rowMapperClass = UserAddressRowMapper.class)
    UserAddressDTO2 findByAddressId(String addressId);

    @Query(value="select * from address a join user u on a.user_id = u.id where u.id = :uid",
    resultSetExtractorClass = UserAddressResultSetExtractor.class)
    UserAddressDTO3 findAllAddressByUid(String uid);

    @Query(value="""
            select name,count(name) as address_num from user u join address a
            on u.id = a.user_id group by name order by address_num
                                       """,
    resultSetExtractorClass = UserAddressResultSetExtractor2.class)
    List<UserAddressDTO4> findAllUserAddresses();


}
