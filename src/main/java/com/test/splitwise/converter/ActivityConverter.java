package com.test.splitwise.converter;

import com.test.splitwise.model.dto.ActivityDTO;
import com.test.splitwise.model.entity.Activity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityConverter {

  @Autowired
  private ModelMapper modelMapper;

  public ActivityDTO toDto(Activity activity) {
    return modelMapper.map(activity, ActivityDTO.class);
  }

  public Activity toEntity(ActivityDTO dto) {
    return modelMapper.map(dto, Activity.class);
  }

  public List<ActivityDTO> toDto(List<Activity> activities) {
    return activities.stream().map(this::toDto).collect(Collectors.toList());
  }
}
