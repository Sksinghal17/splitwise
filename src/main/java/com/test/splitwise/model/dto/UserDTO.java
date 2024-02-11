package com.test.splitwise.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.splitwise.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private UserType userType;

}

