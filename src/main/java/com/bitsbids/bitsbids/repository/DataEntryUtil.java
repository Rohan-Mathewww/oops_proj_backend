package com.bitsbids.bitsbids.repository;

import com.bitsbids.bitsbids.entity.WalletEntity;

public class DataEntryUtil {
    private DataEntryUtil() {
    }
    public static WalletEntity createWalletEntity(){
        return WalletEntity.builder()
            .walletId(null)    
            .balance(0)
            .currentBidTotal(0)
            .build();
    }

}
