package vn.phucnghia.course_management_systems.service;



import vn.phucnghia.course_management_systems.controller.request.UserChangPasswordRequest;
import vn.phucnghia.course_management_systems.controller.request.UserCreationRequest;
import vn.phucnghia.course_management_systems.controller.request.UserUpdateRequest;
import vn.phucnghia.course_management_systems.controller.response.UserResponse;

import java.util.List;

public interface UserService {


    List<UserResponse> findAll(String keyword, String sort, int page, int size);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    long save(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void changePassword(UserChangPasswordRequest req);

    void delete(Long id);
}
