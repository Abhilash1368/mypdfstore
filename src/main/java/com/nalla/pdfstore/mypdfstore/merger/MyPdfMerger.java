package com.nalla.pdfstore.mypdfstore.merger;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Component;

/**
 * author: Abhilash
 * 
 */
@Component
public class MyPdfMerger {

	/**
	 * @param filesPath the files path
	 * @throws IOException
	 */
	public void mergeFiles(final String...filesPath) throws IOException {
		PDFMergerUtility pdfMerger = new PDFMergerUtility();
		for(String filePath:filesPath) {
			final File source = new File(filePath);
			if(source.exists()) {
				pdfMerger.addSource(source);
			}
		}
		pdfMerger.setDestinationFileName("target/generated-sources/my_test_pdf.pdf");
		pdfMerger.mergeDocuments(null);
	}

}
