package headu.mpp.secretpartyreservationsystem.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser (@RequestBody UserCreateRequest userCreateRequest) {
        //userService.createUser(userCreateRequest);
        //return ResponseEntity.ok().build();
       //return ResponseEntity.status(201).build();
        return new ResponseEntity<>(userService.createUser(userCreateRequest), HttpStatus.CREATED);
    }

}
