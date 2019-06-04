package com.nalla.pdfstore.mypdfstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nalla.pdfstore.mypdfstore.merger.MyPdfMerger;

/**
 * author Abhilash
 * 
 *
 */

@SpringBootApplication
public class MypdfstoreApplication implements CommandLineRunner{
	
	@Autowired
	MyPdfMerger myPdfMerger;

	public static void main(String[] args) {
		SpringApplication.run(MypdfstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	myPdfMerger.mergeFiles(args);
	}

}
