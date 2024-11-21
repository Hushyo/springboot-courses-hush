package org.example.experiment2.repository;

import org.example.experiment2.Imp.UserAddressListResultSetExtractor;
import org.example.experiment2.Imp.UserAddressRowMapper;
import org.example.experiment2.dox.User;
import org.example.experiment2.dto.UserAddress;
import org.example.experiment2.dto.UserAddressList;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {


    @Query(value="select * from user u join address a on a.user_id=u.id where u.id=:id",
    rowMapperClass= UserAddressRowMapper.class)
    UserAddress findUserAddressById(String id);

    @Query(value="select * from user u join address a on a.user_id=u.id where u.id=:id",
           resultSetExtractorClass= UserAddressListResultSetExtractor.class)
    UserAddressList findUserAddressListById(String id);

}
