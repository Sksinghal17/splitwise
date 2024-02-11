package com.test.splitwise.converter;

import com.test.splitwise.model.dto.GroupDTO;
import com.test.splitwise.model.entity.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter {

  @Autowired
  private ModelMapper modelMapper;

  public GroupDTO toDto(Group group) {
    return modelMapper.map(group, GroupDTO.class);
  }

  public Group toEntity(GroupDTO dto) {
    return modelMapper.map(dto, Group.class);
  }
}
