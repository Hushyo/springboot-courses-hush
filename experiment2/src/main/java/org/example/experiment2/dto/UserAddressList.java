package org.example.experiment2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.experiment2.dox.Address;
import org.example.experiment2.dox.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressList {
    private User user;
    private List<Address> addressList;
}
