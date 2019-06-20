package com.nalla.pdfstore.mypdfstore.splitter;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import net.bytebuddy.utility.RandomString;

/**
 * @author santh
 * The pdf splitter.
 */
public class PdfSplitter {

	/**
	 * @param filePath the path to original file
	 * @param start the start page
	 * @param end the end page
	 * @return the list of split documents 
	 * @throws Exception
	 */
	public List<String> split(final String filePath, final Integer start, final Integer end) throws Exception {
		final File file = new File(filePath);
		final Splitter splitter = new Splitter();
		splitter.setStartPage(start);
		splitter.setEndPage(end);
		splitter.setSplitAtPage(end - start +1);
		final PDDocument doc = PDDocument.load(file);
		final List<PDDocument> pdfDocuments = splitter.split(doc);
		return pdfDocuments.stream().map(pdf -> {
			final RandomString random = new RandomString();
			final String nextString = random.nextString();
			final String path = "target/generated-sources/" + nextString + ".pdf";
			final File newFile = new File(path);
		    try {
				pdf.save(newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return filePath;
		}).collect(toList());
	}

}
