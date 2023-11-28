package com.bitsbids.bitsbids.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsbids.bitsbids.entity.WalletEntity;
import com.bitsbids.bitsbids.repository.WalletRepository;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public Optional<WalletEntity> findWalletById(UUID walletId){
        return walletRepository.findById(walletId);
    }
    public WalletEntity updateWallet(WalletEntity userWallet){
        return walletRepository.save(userWallet);
    }
}
