package com.bitsbids.bitsbids.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.bitsbids.bitsbids.service.BidService;
@Configuration
@EnableScheduling
public class BiddingSchedulerConfig {
    @Autowired
    private BidService bidService;

    // Schedule the task to run every minute
    @Scheduled(cron = "0 * * * * ?") //runs every minute 
    public void scheduleBiddingTransactions() {
        bidService.performBidTransactions();
    }
}
