package vn.phucnghia.course_management_systems.controller.response;

import lombok.*;
import vn.phucnghia.course_management_systems.common.Gender;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private  String username;
    private Gender gender;
    private Date birthday;
    private String phone;
    private String email;
}
