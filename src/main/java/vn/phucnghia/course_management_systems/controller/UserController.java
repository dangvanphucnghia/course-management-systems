package vn.phucnghia.course_management_systems.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.phucnghia.course_management_systems.common.Gender;
import vn.phucnghia.course_management_systems.controller.request.UserChangPasswordRequest;
import vn.phucnghia.course_management_systems.controller.request.UserCreationRequest;
import vn.phucnghia.course_management_systems.controller.request.UserUpdateRequest;
import vn.phucnghia.course_management_systems.controller.response.UserPageResponse;
import vn.phucnghia.course_management_systems.controller.response.UserResponse;
import vn.phucnghia.course_management_systems.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/user")
@Tag(name=" User Controller")
@RequiredArgsConstructor
@Slf4j(topic = "USER-CONTROLLER")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get user list", description = "API retrieve user from db")
    @GetMapping("/list")
    public Map<String, Object> getList(@RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) String sort,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size){
        log.info("Get list user");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "user list");
        result.put("data", userService.findAll(keyword,sort ,page ,size ));

        return result;

    }

    @Operation(summary = "Get user detail", description = "API retrieve user by userId")
    @GetMapping("/{userId}")
    public Map<String, Object> getUserDetail(@PathVariable Long userId){

        log.info("Get user detail by id: {}", userId);

        UserResponse userResponse = userService.findById(userId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "User Detail Id");
        result.put("data",userResponse);
        return result;
    }

    @Operation(summary="Created User", description = "API add new User to do")
    @PostMapping("/add")
    public ResponseEntity<Object> createdUser(@RequestBody UserCreationRequest request){
        log.info("Create user: {}", request);
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "Update user successfully!!!");
        result.put("data", userService.save(request));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Update User", description = "API update User")
    @PutMapping("/update")
    public Map<String, Object> updateUser(@RequestBody UserUpdateRequest request){
        log.info("Updating user: {}", request);

        userService.update(request);
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "Update user successfully!!!");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Chang Password User", description = "API Chang Password User")
    @PatchMapping("/changpasswod")
    public Map<String, Object> changPasswordUser(@RequestBody UserChangPasswordRequest request){
        log.info("Changing password for user: {}", request);

        userService.changePassword(request);
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.NO_CONTENT.value());
        result.put("message", "Update password successfully!!!");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Disable User", description = "API delete User")
    @DeleteMapping("/delete/{userId}")
    public Map<String, Object> deleteUser(@PathVariable Long userId){
        log.info("Deleting user: {}", userId);
        userService.delete(userId);
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.RESET_CONTENT.value());
        result.put("message", "Delete user successfully!!!");
        result.put("data", "");

        return result;
    }
}
