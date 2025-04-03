package vn.phucnghia.course_management_systems.controller.request;

import lombok.Getter;
import lombok.Setter;
import vn.phucnghia.course_management_systems.common.Gender;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserUpdateRequest implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private  String username;
    private Gender gender;
    private Date birthday;
    private String phone;
    private String email;
}
