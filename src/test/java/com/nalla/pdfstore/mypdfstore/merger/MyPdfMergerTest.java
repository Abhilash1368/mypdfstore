package com.nalla.pdfstore.mypdfstore.merger;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.Before;
import org.junit.Test;

/**
 * @author santh
 * Test myp pdf merger class.
 */
public class MyPdfMergerTest {
    private MyPdfMerger uut = new MyPdfMerger();
    
    @Before
    public void before() {
    	final File destinationFolder = new File("target/generated-sources/");
    	final File[] listFiles = destinationFolder.listFiles();
    	for (File file : listFiles) {
    		file.delete();
		}
    }

    @Test
    public void shouldmergeFiles() throws IOException {
        final String[] filesPath = new String[]{"target/generated-test-sources/FirstPdf.pdf", "target/generated-test-sources/secondPdf.pdf"};
        final PDPage page = new PDPage();
        final PDDocument document = new PDDocument();
        document.addPage(page);
        int totalPages = 0;
        for (final String file : filesPath) {
            document.save(file);
            totalPages += document.getNumberOfPages();
        }
        document.close();

        uut.mergeFiles(filesPath);
        
        final PDDocument destinationDocument = PDDocument.load(new File("target/generated-sources/my_test_pdf.pdf"));
        assertThat(destinationDocument.getNumberOfPages()).isEqualTo(totalPages);
    }

    @Test
    public void shouldNotmergeFiles() throws IOException {
        uut.mergeFiles("target/generated-test-sources/onePdf.pdf");
        
        final File destinationFile = new File("target/generated-sources/");
        assertThat(destinationFile.listFiles().length).isZero();
    }

}
