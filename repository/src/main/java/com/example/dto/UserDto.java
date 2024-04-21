package com.example.dto;

import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private Long profileId;
    private Boolean isNeedNotify;
    private User.State state;
}
