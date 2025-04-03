package vn.phucnghia.course_management_systems.controller.request;

import lombok.Getter;


@Getter
public class UserChangPasswordRequest {
    private Long id;
    private String oldpassword;
    private String newpassword;
}
