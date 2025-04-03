package vn.phucnghia.course_management_systems.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private  String username;
    private String gender;
    private Date birthday;
    private String phone;
    private String email;
}
