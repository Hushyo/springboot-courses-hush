package org.example.experiment2.Imp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.experiment2.dox.Address;
import org.example.experiment2.dox.User;
import org.example.experiment2.dto.UserAddressList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserAddressListResultSetExtractor implements ResultSetExtractor<UserAddressList> {
    @Override
    public UserAddressList extractData(ResultSet rs) throws SQLException, DataAccessException {
        User u = null;
        List<Address> addressList = new ArrayList<>();
        while(rs.next()){
            if(u==null){
                u = User.builder()
                        .id(rs.getString("u.id"))
                        .name(rs.getString("u.name"))
                        .createTime(rs.getObject("create_time", LocalDateTime.class))
                        .updateTime(rs.getObject("update_time", LocalDateTime.class))
                        .build();
            }
            Address address = Address.builder()
                    .id(rs.getString("a.id"))
                    .userId(rs.getString("a.user_id"))
                    .detail(rs.getString("a.detail"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("update_time", LocalDateTime.class))
                    .build();

            addressList.add(address);
        }
        return UserAddressList.builder().user(u).addressList(addressList).build();
    }
}
