package com.test.splitwise.model.enums;

import lombok.Getter;

@Getter
public enum UserType {
  ADMIN("Admin User"), USER("Regular User");

  private final String displayName;

  UserType(String displayName) {
    this.displayName = displayName;
  }

}

