package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.domain.Gender;
import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public List<UserResponseDto> getAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> postUser(
            @RequestParam("name") String name,
            @RequestParam("gender") Gender gender,
            @RequestParam("address") String address,
            @RequestParam("age") Integer age,
            @RequestParam("phone") String phone,
            @RequestParam("image") MultipartFile image
            ) throws Exception {
            UserDto userDto = UserDto.builder()
                    .name(name)
                    .gender(gender)
                    .address(address)
                    .age(age)
                    .phone(phone)
                    .image(image)
                    .build();
            UserResponseDto userResponseDto = userService.saveUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}
