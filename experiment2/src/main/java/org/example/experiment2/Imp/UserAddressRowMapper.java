package org.example.experiment2.Imp;

import org.example.experiment2.dox.Address;
import org.example.experiment2.dox.User;
import org.example.experiment2.dto.UserAddress;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class UserAddressRowMapper implements RowMapper<UserAddress> {
    @Override
    public UserAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u=User.builder()
                .id(rs.getString("u.id"))
                .name(rs.getString("u.name"))
                .createTime(rs.getObject("create_time", LocalDateTime.class))
                .updateTime(rs.getObject("update_time",LocalDateTime.class))
                .build();
        Address a=Address.builder()
                .id(rs.getString("a.id"))
                .detail(rs.getString("a.detail"))
                .userId(rs.getString("user_id"))
                .createTime(rs.getObject("create_time", LocalDateTime.class))
                .updateTime(rs.getObject("update_time",LocalDateTime.class))
                .build();
        return UserAddress.builder().user(u).address(a).build();
    }
}
