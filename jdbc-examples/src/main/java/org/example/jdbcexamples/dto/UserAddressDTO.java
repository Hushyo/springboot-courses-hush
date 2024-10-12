package org.example.jdbcexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 封装方式一: User 和 Address 一对一查询，封装到一起
public class UserAddressDTO {
        private String id;
        private String userId;
        private String name;
        private String detail;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
}
