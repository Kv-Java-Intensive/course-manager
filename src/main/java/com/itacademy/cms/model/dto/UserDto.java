package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.UserToCourse;
import com.itacademy.cms.model.enums.Role;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private List<UserToCourse> userCourse;

  private List<Group> groups;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private double accountCard;

  private Role role;

  private String about;

  private boolean active;
}
