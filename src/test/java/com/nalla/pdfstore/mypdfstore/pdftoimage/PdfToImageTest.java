package com.nalla.pdfstore.mypdfstore.pdftoimage;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Before;
import org.junit.Test;

public class PdfToImageTest {
	private PdfToImage uut = new PdfToImage();

	@Before
	public void before() {
		final File destinationFolder = new File("target/generated-sources/");
		final File[] listFiles = destinationFolder.listFiles();
		for (File file : listFiles) {
			file.delete();
		}
	}

	@Test
	public void saveAsPngImage() throws IOException {
		final String filesPath = "target/generated-test-sources/pdfToImage.pdf";
		final String sampleText = "This is the sample document";
		final PDDocument document = new PDDocument();
		final PDPage page = new PDPage();

		final PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText(); 
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.newLineAtOffset(25, 500);
		contentStream.showText(sampleText);      
		contentStream.endText();
		contentStream.close();

		document.addPage(page);
		document.save(filesPath);
		document.close();

		final File file = new File(uut.saveAsImage(filesPath,"PNG"));
		assertThat(file.exists()).isTrue();
	}

	@Test
	public void saveAsJpegImage() throws IOException {
		final String filesPath = "target/generated-test-sources/pdfToImage.pdf";
		final String sampleText = "This is the sample document";
		final PDDocument document = new PDDocument();
		final PDPage page = new PDPage();

		final PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText(); 
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.newLineAtOffset(25, 500);
		contentStream.showText(sampleText);      
		contentStream.endText();
		contentStream.close();

		document.addPage(page);
		document.save(filesPath);
		document.close();

		final File file = new File(uut.saveAsImage(filesPath,"jpeg"));
		assertThat(file.exists()).isTrue();
	}
}
