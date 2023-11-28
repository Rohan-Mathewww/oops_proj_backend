package com.bitsbids.bitsbids.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsbids.bitsbids.entity.BidEntity;
import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.entity.TransactionEntity;
import com.bitsbids.bitsbids.entity.WalletEntity;
import com.bitsbids.bitsbids.repository.BidRepository;
import com.bitsbids.bitsbids.repository.ProductRepository;
import com.bitsbids.bitsbids.repository.TransactionRepository;
import com.bitsbids.bitsbids.repository.WalletRepository;

@Service
public class BidService{
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    
    public BidEntity createProduct(BidEntity bidEntity) {
        return bidRepository.save(bidEntity);
    }
    public Optional<BidEntity> getBid(UUID bidId){
        return bidRepository.findById(bidId);
    }
    public void performBidTransactions(){
        List<ProductEntity> products = productRepository.findByTitleAndCategoryAndCondition(null, null, null);

        for (ProductEntity product : products) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (currentTime.after(product.getBidEndTime())) {
                BidEntity highestBid = product.getHighestBid();
                if (highestBid != null) {
                    // Create a transaction for the highest bidder
                    TransactionEntity transaction = new TransactionEntity();
                    transaction.setPaidFrom(highestBid.getBidder().getWalletId());
                    transaction.setPaidTo(product.getSeller().getWalletId());
                    transactionRepository.save(transaction);

                    // Deduct the bid amount from the bidder's wallet and current bid amount
                    int bidAmount = highestBid.getBidAmount();
                    WalletEntity bidderWallet = highestBid.getBidder().getWalletId();
                    bidderWallet.setBalance(bidderWallet.getBalance() - bidAmount);
                    bidderWallet.setCurrentBidTotal(bidderWallet.getCurrentBidTotal()-bidAmount);
                    walletRepository.save(bidderWallet);

                    // Update product with the transaction
                    product.setTransaction(transaction);
                    productRepository.save(product);
                } else {
                    // deletes the product
                    productRepository.deleteById(product.getProductId());;
                }
            }
        }
    }
}
