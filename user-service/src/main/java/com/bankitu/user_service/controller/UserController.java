package com.bankitu.user_service.controller;

import com.bankitu.user_service.dto.CreateUser;
import com.bankitu.user_service.dto.ResponseCreateUser;
import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestParam String email) {
        return ResponseEntity.ok(this.userService.findUserByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody() CreateUser payload) {
        return ResponseEntity.ok(this.userService.createUser(payload));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody() CreateUser payload, @RequestParam String id) {
        return ResponseEntity.ok(this.userService.updateUser(payload, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String id) {
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }
}
