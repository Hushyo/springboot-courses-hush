package org.example.jdbcexamples.imp;

import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;
import org.example.jdbcexamples.dto.UserAddressDTO3;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserAddressResultSetExtractor implements ResultSetExtractor<UserAddressDTO3> {
    @Override
    public UserAddressDTO3 extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = null;
        List<Address> addresses = new ArrayList<>();
        while(rs.next()){
            if(user==null){
                user = User.builder()
                        .id(rs.getString("u.id"))
                        .name(rs.getString("u.name"))
                        .createTime(rs.getObject("create_time", LocalDateTime.class))
                        .updateTime(rs.getObject("update_time", LocalDateTime.class))
                        .build();
            }
            Address a = Address.builder()
                    .id(rs.getString("a.id"))
                    .userId(rs.getString("user_id"))
                    .detail(rs.getString("detail"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("update_time", LocalDateTime.class))
                    .build();
            addresses.add(a);
        }
    return UserAddressDTO3.builder()
            .addresses(addresses)
            .user(user).build();
    }


}
