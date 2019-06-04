package com.nalla.pdfstore.mypdfstore.merger;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Component;

/**
 * author: Abhilash
 * 
 * 
 */
@Component
public class MyPdfMerger {
	
	
	public void mergeFiles(String...filePaths) throws IOException {
		PDFMergerUtility pdfMerger = new PDFMergerUtility();
		String homeDir = System.getProperty("user.home");
		 pdfMerger.setDestinationFileName(homeDir+"/my_test_pdf.pdf");
		for(String filePath:filePaths) {
			  pdfMerger.addSource(new File(filePath));		      
		}
		pdfMerger.mergeDocuments(null);
		System.out.println("Files merged to "+pdfMerger.getDestinationFileName());
		
	}
	

}
