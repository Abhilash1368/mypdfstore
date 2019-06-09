package com.nalla.pdfstore.mypdfstore.pdftoimage;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;


/**
 * @author santh
 *
 */
public class PdfToImage {

	/**
	 * @param filePath the file path
	 * @return the path of the file
	 * @throws IOException
	 */
	public String saveAsImage(final String filePath, final String imageType) throws IOException {
		final File file = new File(filePath);
		String fileName = new String(); 
		if(file.exists()) {
			final PDDocument document = PDDocument.load(file);
			final PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (PDPage page : document.getPages())
			{
				BufferedImage dim = pdfRenderer.renderImageWithDPI(page.getStructParents(), 300, ImageType.RGB);
				fileName = "target/generated-sources/" + "image-" + page + "." + imageType;
				ImageIO.write(dim, imageType, new File(fileName));
			}
			document.close();
		}
		return fileName;
	}
}
