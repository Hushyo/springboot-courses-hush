package org.example.jdbcexamples.imp;

import org.example.jdbcexamples.dto.UserAddressDTO4;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressResultSetExtractor2 implements ResultSetExtractor<List<UserAddressDTO4>> {
    @Override
    public List<UserAddressDTO4> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<UserAddressDTO4> userAddressDTO4List = new ArrayList<>();
        while(rs.next()) {
            userAddressDTO4List.add(UserAddressDTO4.builder()
                            .name(rs.getString("name"))
                            .addressNum(rs.getInt("address_num"))
                            .build());
        }
        return userAddressDTO4List;
    }
}
