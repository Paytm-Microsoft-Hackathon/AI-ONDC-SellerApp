package com.example.ONDC.sellerApp.ONDCSellerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OndcSellerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OndcSellerAppApplication.class, args);
		System.setProperty("javax.net.ssl.trustStore", "C:/.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}

}
