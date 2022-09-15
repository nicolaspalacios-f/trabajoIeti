package co.edu.escuelaing.IetiLab1.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<UserDto> users = new ArrayList<>();
        userService.getAll().forEach((user) -> users.add(user.toDTO()));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.toDTO());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        User user = userService.create(userDto.toEntity());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user.toDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user, @PathVariable String id) {
        User userMethod = userService.update(user.toEntity(), id);
        if (userMethod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMethod.toDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok(userService.findById(id) == null);
    }

    @GetMapping("/searchName/{queryText}")
    public ResponseEntity<List<UserDto>> findUsersWithNameOrLastNameLike(@PathVariable String queryText) {
        List<UserDto> users = new ArrayList<>();
        userService.findUsersWithNameOrLastNameLike(queryText).forEach((user) -> users.add(user.toDTO()));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/searchDate/{startDate}")
    public ResponseEntity<List<UserDto>> findUsersCreatedAfter(@PathVariable String startDate) throws ParseException {
        List<UserDto> users = new ArrayList<>();
        userService.findUsersCreatedAfter(
                new SimpleDateFormat("dd-MM-yyyy").parse(startDate)).forEach((user) -> users.add(user.toDTO()));
        return ResponseEntity.ok(users);
    }
}
