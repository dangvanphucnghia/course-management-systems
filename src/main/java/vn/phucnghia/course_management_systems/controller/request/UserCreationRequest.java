package vn.phucnghia.course_management_systems.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.phucnghia.course_management_systems.common.Gender;
import vn.phucnghia.course_management_systems.common.UserType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserCreationRequest implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private  String username;
    private Gender gender;
    private Date birthday;
    private String phone;
    private String email;
    private UserType type;
    private List<AddressRequest> addresses;
}
