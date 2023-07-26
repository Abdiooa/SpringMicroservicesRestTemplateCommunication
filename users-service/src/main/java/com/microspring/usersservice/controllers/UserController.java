package com.microspring.usersservice.controllers;

import com.microspring.usersservice.dto.ResponseDto;
import com.microspring.usersservice.dto.UserRequest;
import com.microspring.usersservice.dto.UserResponse;
import com.microspring.usersservice.exceptions.UserNotFoundException;
import com.microspring.usersservice.exceptions.UserWithEmailExistAlreadyException;
import com.microspring.usersservice.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api/v1/users",produces = "application/json")
public class UserController {
    private final UsersService usersService;
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){
        try{
            UserResponse userResponse = usersService.saveUser(userRequest);
            return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
        }catch (UserWithEmailExistAlreadyException userWithEmailExistAlreadyException){
            return new ResponseEntity<>("User with this email exist, we sorry", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        try {
            ResponseDto responseDto = usersService.getUserById(id);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }catch (UserNotFoundException userNotFoundException){
            return new ResponseEntity<>("User with that Id not Found",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>("An error has occured when processing the request",HttpStatus.BAD_REQUEST);
        }
    }
}
