package com.apps.developerblog.photoapp.api.users.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 400:
			break;
		case 404: {
			if (methodKey.contains("getAlbums")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()),
						"User  albums are not found ");
			}
			break;
		}
		default: {
			return new Exception(response.reason());
		}
		}
		return null;
	}

}
