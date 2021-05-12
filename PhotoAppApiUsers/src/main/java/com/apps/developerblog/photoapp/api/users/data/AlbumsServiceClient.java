package com.apps.developerblog.photoapp.api.users.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apps.developerblog.photoapp.api.users.ui.model.AlbumResponseModel;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

//@FeignClient(name = "albums-ws", fallback = AlbumsFallBack.class)
@FeignClient(name = "albums-ws", fallbackFactory = AlbumsFallBackFactory.class)
public interface AlbumsServiceClient {
	@GetMapping("/users/{id}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable("id") String id);

}

/*
 * @Component class AlbumsFallBack implements AlbumsServiceClient {
 * 
 * @Override public List<AlbumResponseModel> getAlbums(String id) { // TODO
 * Auto-generated method stub return new ArrayList<>(); }
 * 
 * }
 */
@Component
class AlbumsFallBackFactory implements FallbackFactory<AlbumsServiceClient> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public AlbumsServiceClient create(Throwable cause) {

		return new AlbumsServiceClientFallback(cause);
	}

	class AlbumsServiceClientFallback implements AlbumsServiceClient {

		private final Throwable cause;

		public AlbumsServiceClientFallback(Throwable cause) {
			super();
			this.cause = cause;
		}

		@Override
		public List<AlbumResponseModel> getAlbums(String id) {
			if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
				logger.error("404 error took place when getAlbums was called with user Id:" + id + " Error Message :"
						+ cause.getLocalizedMessage());
			} else {
				logger.error("Other error took place: " + cause.getLocalizedMessage());
			}
			return new ArrayList<>();

		}

	}

}