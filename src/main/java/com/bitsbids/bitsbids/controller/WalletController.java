package com.bitsbids.bitsbids.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitsbids.bitsbids.entity.UserEntity;
import com.bitsbids.bitsbids.entity.WalletEntity;
import com.bitsbids.bitsbids.service.UserService;
import com.bitsbids.bitsbids.service.WalletService;

@Controller
@RequestMapping(path="/wallets")
@CrossOrigin
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> viewWallet(@PathVariable("userId") UUID userId){
        Optional<UserEntity> existingUserEntity = userService.findUserById(userId);
        if (existingUserEntity.isPresent()){
            UserEntity retrievedUserEntity = existingUserEntity.get();
            return new ResponseEntity<>(retrievedUserEntity.getWalletId(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No user with that id",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> viewWallet(@PathVariable("userId") UUID userId, @RequestBody int amountToBeAdded){
        Optional<UserEntity> existingUserEntity = userService.findUserById(userId);
        if (existingUserEntity.isPresent()){
            UserEntity retrievedUserEntity = existingUserEntity.get();
            WalletEntity userWallet = retrievedUserEntity.getWalletId();
            userWallet.setBalance(userWallet.getBalance()+amountToBeAdded);
            walletService.updateWallet(userWallet);
            return new ResponseEntity<>("Balance Updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Id does not exist",HttpStatus.NOT_FOUND);
        }
    }
    // should i have
}
