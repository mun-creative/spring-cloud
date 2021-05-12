package com.apps.developerblog.photoapp.app.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PhotoAppApiConfigServerApplication {

	@Value("${spring.cloud.config.server.native.search-locations}")
	static String url;

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiConfigServerApplication.class, args);
		System.out.println("Locations " + url);
	}

}
