package com.nalla.pdfstore.mypdfstore.splitter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Before;
import org.junit.Test;

import com.nalla.pdfstore.mypdfstore.merger.MyPdfMerger;

/**
 * @author santh
 *
 */
public class PdfSplitterTest {
	private PdfSplitter uut = new PdfSplitter();

	@Before
	public void before() {
		final File destinationFolder = new File("target/generated-sources/");
		final File[] listFiles = destinationFolder.listFiles();
		for (File file : listFiles) {
			file.delete();
		}
	}

	@Test
	public void shouldSplitPdfFiles() throws Exception {
		final String filesPath = "target/generated-test-sources/hulk.pdf";
		final File file = new File(filesPath);
		final PDPage page = new PDPage();

		final PDDocument document = new PDDocument();
		document.addPage(page);
		document.addPage(page);
		document.addPage(page);
		document.addPage(page);
		document.save(file);
		document.close();

		final List<String> split = uut.split(filesPath, 1, 3);
		assertThat(split.size()).isOne();
	}

}
