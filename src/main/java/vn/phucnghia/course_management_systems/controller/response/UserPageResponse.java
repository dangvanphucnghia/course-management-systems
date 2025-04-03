package vn.phucnghia.course_management_systems.controller.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class UserPageResponse extends PageResponseAbsTract implements Serializable {
    private List<UserResponse> users;
}
