package com.bitsbids.bitsbids.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitsbids.bitsbids.entity.UserEntity;
import com.bitsbids.bitsbids.service.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) // find what ResponseEntity does
    {
        return new ResponseEntity<>(userService.createUser(userEntity),HttpStatus.CREATED);// created used to indicate successful creation
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") UUID userId, @RequestBody UserEntity userEntity) {   
        Optional<UserEntity> existingUserEntity = userService.findUserById(userId);
        if (existingUserEntity.isPresent()){
            UserEntity retrievedUserEntity = existingUserEntity.get();
            return new ResponseEntity<>(userService.updateUser(userId,retrievedUserEntity),HttpStatus.OK); // OK used to indicate successful update
            //return new ResponseEntity<>(retrievedUserEntity,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Id does not exist",HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") UUID userId){
        Optional<UserEntity> existingUserEntity = userService.findUserById(userId);
        if (existingUserEntity.isPresent()){
            UserEntity retrievedUserEntity = existingUserEntity.get();
            return new ResponseEntity<>(retrievedUserEntity,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Id does not exist",HttpStatus.NOT_FOUND);
        }
    }
}
