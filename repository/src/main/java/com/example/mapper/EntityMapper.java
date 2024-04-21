package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.User;

public class EntityMapper {
    public static UserDto userToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .profileId(user.getProfileId())
                .isNeedNotify(user.getIsNeedNotify())
                .state(user.getState())
                .build();
    }
}
