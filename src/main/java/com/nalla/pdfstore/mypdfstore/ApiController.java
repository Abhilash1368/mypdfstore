package com.nalla.pdfstore.mypdfstore;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nalla.pdfstore.mypdfstore.merger.MyPdfMerger;

/**
 * author Abhilash
 * 
 *
 */
@RestController
@CrossOrigin
public class ApiController {
	
	@Autowired
	MyPdfMerger pdfMerger;


	
	@PostMapping(path="/merger", consumes = { "multipart/form-data" })
	private ResponseEntity<?> pdfMerger(@RequestParam("file") MultipartFile[] uploadingFiles) {
		
		if(uploadingFiles==null || uploadingFiles.length==0) {
			throw new IllegalArgumentException("Atleast one pdf file should be uploaded");		
		}
		for(MultipartFile uploadedFile : uploadingFiles) {
           
            try {
				pdfMerger.mergeFiles(uploadedFile.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return new ResponseEntity<>("Files merged to home folder",HttpStatus.OK);
		
	}
}
