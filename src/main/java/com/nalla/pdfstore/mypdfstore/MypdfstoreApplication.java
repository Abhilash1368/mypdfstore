
package com.nalla.pdfstore.mypdfstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nalla.pdfstore.mypdfstore.merger.MyPdfMerger;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author Abhilash
 * 
 *
 */

@SpringBootApplication
@EnableSwagger2
public class MypdfstoreApplication implements CommandLineRunner {

	@Autowired
	MyPdfMerger myPdfMerger;

	public static void main(String[] args) {
		SpringApplication.run(MypdfstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//myPdfMerger.mergeFiles(args);

	}

	@SuppressWarnings("deprecation")
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.nalla.pdfstore.mypdfstore")).paths(PathSelectors.any())
				.build().apiInfo(new ApiInfo("My pdfstore RESTful Web Service documentation",
						"This pages documents my pddfstore app RESTful Web Service endpoints", "1.0", "", "", "", ""));
	}

}
