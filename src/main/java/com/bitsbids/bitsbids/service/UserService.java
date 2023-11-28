package com.bitsbids.bitsbids.service;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsbids.bitsbids.entity.UserEntity;
import com.bitsbids.bitsbids.entity.WalletEntity;
import com.bitsbids.bitsbids.repository.DataEntryUtil;
import com.bitsbids.bitsbids.repository.UserRepository;
import com.bitsbids.bitsbids.repository.WalletRepository;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;


    // public UserServiceImpl(UserRepository userRepository){
    //     this.userRepository = userRepository;
    // }
    public UserEntity createUser(UserEntity userEntity){
        WalletEntity walletEntity = DataEntryUtil.createWalletEntity(); 
        // should wallet id be initialized at all or initialized to null
        userEntity.setWalletId(walletRepository.save(walletEntity));
        return userRepository.save(userEntity);
    }
    public UserEntity updateUser(UUID userId, UserEntity userEntity){
        userEntity.setUserId(userId);
        return userRepository.save(userEntity);
    }
    public Optional<UserEntity> findUserById(UUID uuid){
        return userRepository.findById(uuid);
    }


}
