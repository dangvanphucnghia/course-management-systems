package vn.phucnghia.course_management_systems.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.phucnghia.course_management_systems.common.Gender;
import vn.phucnghia.course_management_systems.controller.request.UserChangPasswordRequest;
import vn.phucnghia.course_management_systems.controller.request.UserCreationRequest;
import vn.phucnghia.course_management_systems.controller.request.UserUpdateRequest;
import vn.phucnghia.course_management_systems.controller.response.UserResponse;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mockup/user")
@Tag(name="Mockup User Controller")
public class MockupUserController {
    @Operation(summary = "Get user list", description = "API retrieve user from db")
    @GetMapping("/list")
    public Map<String, Object> getList(@RequestParam(required = false) String keywword,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size){

        UserResponse userResponse1 = new UserResponse();

        userResponse1.setId(1l);
        userResponse1.setFirstName("admin1");
        userResponse1.setLastName("system1");
        userResponse1.setUsername("admin1");
        userResponse1.setGender(Gender.MALE);
        userResponse1.setBirthday(new Date());
        userResponse1.setPhone("0368285760");
        userResponse1.setEmail("admin1@gmail.com");

        UserResponse userResponse2 = new UserResponse();

        userResponse2.setId(2l);
        userResponse2.setFirstName("admin2");
        userResponse2.setLastName("system2");
        userResponse2.setUsername("admin2");
        userResponse2.setGender(Gender.MALE);
        userResponse2.setBirthday(new Date());
        userResponse2.setPhone("0368285761");
        userResponse2.setEmail("admin2@gmail.com");

        List<UserResponse> userlink = List.of(userResponse1, userResponse2);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "User Link");
        result.put("data",userlink);
        return result;

    }

    @Operation(summary = "Get user detail", description = "API retrieve user by userId")
    @GetMapping("/{userId}")
    public Map<String, Object> getUserDetail(@PathVariable Long userId){

        UserResponse userResponse1 = new UserResponse();
        userResponse1.setId(userId);
        userResponse1.setFirstName("admin1");
        userResponse1.setLastName("system1");
        userResponse1.setUsername("admin1");
        userResponse1.setGender(Gender.MALE);
        userResponse1.setBirthday(new Date());
        userResponse1.setPhone("0368285760");
        userResponse1.setEmail("admin1@gmail.com");

        List<UserResponse> userlink = List.of(userResponse1);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "User Detail Id");
        result.put("data",userlink);
        return result;
    }

    @Operation(summary="Created User", description = "API add new User to do")
    @PostMapping("/add")
    public Map<String, Object> CreatedUser(UserCreationRequest request){
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.CREATED.value());
        result.put("message", "Created User Successfully!!!");
        result.put("data", 3);

        return result;
    }

    @Operation(summary = "Update User", description = "API update User")
    @PutMapping("/update")
    public Map<String, Object> updateUser(@RequestBody UserUpdateRequest request){
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "Update user successfully!!!");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Chang Password User", description = "API Chang Password User")
    @PatchMapping("/changpasswod")
    public Map<String, Object> changPasswordUser(UserChangPasswordRequest request){
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.NO_CONTENT.value());
        result.put("message", "Update password successfully!!!");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Disable User", description = "API delete User")
    @DeleteMapping("/delete/{userId}")
    public Map<String, Object> deleteUser(@PathVariable Long userId){
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("status", HttpStatus.RESET_CONTENT.value());
        result.put("message", "Delete user successfully!!!");
        result.put("data", "");

        return result;
    }
}

