package com.MusicCatalog.MusicCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.MusicCatalog.MusicCatalog")
public class MusicCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicCatalogApplication.class, args);
	}

}
