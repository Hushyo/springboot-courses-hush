package org.example.jdbcexamples.imp;

import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;
import org.example.jdbcexamples.dto.UserAddressDTO2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserAddressRowMapper implements RowMapper<UserAddressDTO2> {
    @Override
    public UserAddressDTO2 mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = User.builder()
                .id(rs.getString("u.id"))
                .name(rs.getString("u.name"))
                .createTime(rs.getObject("create_time", LocalDateTime.class))
                .updateTime(rs.getObject("update_time", LocalDateTime.class))
                .build();
        Address address = Address.builder()
                .id(rs.getString("a.id"))
                .detail(rs.getString("a.detail"))
                .createTime(rs.getObject("create_time", LocalDateTime.class))
                .updateTime(rs.getObject("update_time", LocalDateTime.class))
                .userId(rs.getString("user_id"))
                .build();
        return UserAddressDTO2.builder().user(user).address(address).build();
    }
}