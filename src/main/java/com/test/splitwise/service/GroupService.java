package com.test.splitwise.service;

import com.test.splitwise.converter.GroupConverter;
import com.test.splitwise.exception.SplitwiseException;
import com.test.splitwise.model.dto.GroupDTO;
import com.test.splitwise.model.entity.Group;
import com.test.splitwise.repos.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private GroupConverter groupConverter;

  public GroupDTO createGroup(GroupDTO groupDTO) {
    Group group = groupConverter.toEntity(groupDTO);
    Group savedGroup = groupRepository.save(group);
    return groupConverter.toDto(savedGroup);
  }

  public GroupDTO getGroupById(Integer groupId) {
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new SplitwiseException("Group not found with id: " + groupId));
    return groupConverter.toDto(group);
  }

}

