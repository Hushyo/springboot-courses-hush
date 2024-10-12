package org.example.jdbcexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 封装方式二 , 想根据 address查出对应的user
public class UserAddressDTO2 {
    private User user;
    private Address address;
}
