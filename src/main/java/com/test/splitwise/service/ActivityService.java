package com.test.splitwise.service;

import com.test.splitwise.converter.ActivityConverter;
import com.test.splitwise.model.dto.ActivityDTO;
import com.test.splitwise.model.entity.Activity;
import com.test.splitwise.repos.ActivityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;

  @Autowired
  private ActivityConverter activityConverter;

  public ActivityDTO addActivity(ActivityDTO activityDTO) {
    // Convert DTO to entity
    Activity activity = activityConverter.toEntity(activityDTO);

    // Save the activity in the database
    Activity savedActivity = activityRepository.save(activity);

    // Convert the saved activity back to DTO and return
    return activityConverter.toDto(savedActivity);
  }

  public List<ActivityDTO> getAllActivities() {
    List<Activity> activities = activityRepository.findAll();
    return activityConverter.toDto(activities);
  }
}

