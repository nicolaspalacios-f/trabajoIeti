package co.edu.escuelaing.IetiLab1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.escuelaing.IetiLab1.dto.UserDto;
import co.edu.escuelaing.IetiLab1.entities.User;
import co.edu.escuelaing.IetiLab1.service.UserService;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        ArrayList<UserDto> users = new ArrayList<>();
        userService.getAll().forEach(user -> users.add(
                new UserDto(user.getId(), user.getName(), user.getEmail(), user.getLastName(), user.getCreatedAt())));
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        // TODO implement this method using UserService
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new UserDto(user.getId(), user.getName(), user.getEmail(), user.getLastName(),
                    user.getCreatedAt()));
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        // TODO implement this method using UserService
        User user = userService.create(new User(userDto.getId(), userDto.getName(), userDto.getEmail(),
                userDto.getLastName(), userDto.getCreatedAt()));
        return ResponseEntity.ok(new UserDto(user.getId(), user.getName(), user.getEmail(), user.getLastName(),
                user.getCreatedAt()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user, @PathVariable String id) {
        // TODO implement this method using UserService
        User userUpdate = userService.update(new User(user.getId(), user.getName(), user.getEmail(), user.getLastName(),
                user.getCreatedAt()), id);
        if (userUpdate == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new UserDto(userUpdate.getId(), userUpdate.getName(), userUpdate.getEmail(),
                    userUpdate.getLastName(), userUpdate.getCreatedAt()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        // TODO implement this method using UserService
        System.out.println(id);
        userService.deleteById(id);

        return ResponseEntity.ok(true);
    }
}
