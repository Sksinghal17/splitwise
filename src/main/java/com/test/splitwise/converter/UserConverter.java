package com.test.splitwise.converter;

import com.test.splitwise.model.dto.UserDTO;
import com.test.splitwise.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  @Autowired
  private ModelMapper modelMapper;

  public UserDTO toDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public User toEntity(UserDTO dto) {
    return modelMapper.map(dto, User.class);
  }
}

