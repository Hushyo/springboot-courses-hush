package org.example.experiment2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.experiment2.dox.Address;
import org.example.experiment2.dox.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    private User user;
    private Address address;
}
