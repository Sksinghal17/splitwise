package com.test.splitwise.controller;

import com.test.splitwise.model.dto.GroupDTO;
import com.test.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

  @Autowired
  private GroupService groupService;

  // Endpoint to create a new group
  @PostMapping
  public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
    GroupDTO createdGroup = groupService.createGroup(groupDTO);
    return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
  }

  // Endpoint to get details of a group by ID
  @GetMapping("/{groupId}")
  public ResponseEntity<GroupDTO> getGroupById(@PathVariable Integer groupId) {
    GroupDTO groupDTO = groupService.getGroupById(groupId);
    return ResponseEntity.ok(groupDTO);
  }

}

