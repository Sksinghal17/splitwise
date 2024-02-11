package com.test.splitwise.controller;

import com.test.splitwise.model.dto.ActivityDTO;
import com.test.splitwise.service.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

  @Autowired
  private ActivityService activityService;

  @PostMapping("/add")
  public ResponseEntity<ActivityDTO> addActivity(@RequestBody ActivityDTO activityDTO) {
    ActivityDTO savedActivity = activityService.addActivity(activityDTO);
    return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ActivityDTO>> getAllActivities() {
    List<ActivityDTO> activities = activityService.getAllActivities();
    return new ResponseEntity<>(activities, HttpStatus.OK);
  }
}

