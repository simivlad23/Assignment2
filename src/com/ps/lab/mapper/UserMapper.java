package com.ps.lab.mapper;

import com.ps.lab.dto.NameIdDTO;
import com.ps.lab.model.User;

public class UserMapper {
//
//    public User toEntity(UserDTO userDTO);
//
//    public UserDTO toDTO(User user);

    public NameIdDTO toNameIdDTO(User user) {
        NameIdDTO nameIdDTO = new NameIdDTO();
        nameIdDTO.setName(user.getUsername());
        nameIdDTO.setId(user.getId());

        return nameIdDTO;
    }
}
