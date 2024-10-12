package org.example.jdbcexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jdbcexamples.dox.Address;
import org.example.jdbcexamples.dox.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 封装方式三 想根据 user查出它的所有address
public class UserAddressDTO3 {
    private User user;
    private List<Address> addresses;
}
