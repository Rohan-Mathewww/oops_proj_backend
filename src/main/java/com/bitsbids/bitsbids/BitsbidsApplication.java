package com.bitsbids.bitsbids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// alias for several different alpplications
// has @ComponentScan (basically starts scanning for components from this point in the hierarchy down the hierarchy)
// has Configuration 
// has EnableAutoConfiguration 
@SpringBootApplication
public class BitsbidsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitsbidsApplication.class, args);
	}

}
