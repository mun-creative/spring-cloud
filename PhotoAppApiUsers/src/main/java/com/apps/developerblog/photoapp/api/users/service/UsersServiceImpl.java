package com.apps.developerblog.photoapp.api.users.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apps.developerblog.photoapp.api.users.data.AlbumsServiceClient;
import com.apps.developerblog.photoapp.api.users.data.UserEntity;
import com.apps.developerblog.photoapp.api.users.data.UserRepository;
import com.apps.developerblog.photoapp.api.users.shared.UserDto;
import com.apps.developerblog.photoapp.api.users.ui.model.AlbumResponseModel;

import feign.FeignException;

@Service
public class UsersServiceImpl implements UsersService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPwdEncoder;
	// RestTemplate restTemplate;
	Environment env;
	AlbumsServiceClient albumClient;

	@Autowired
	public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPwdEncoder,
			AlbumsServiceClient albumClient, Environment env) {
		super();
		this.userRepository = userRepository;
		this.bCryptPwdEncoder = bCryptPwdEncoder;
		// this.restTemplate = restTemplate;
		this.albumClient = albumClient;
		this.env = env;
	}

	@Override
	public UserDto CreateUser(UserDto userDetails) {

		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPwdEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		userRepository.save(userEntity);
		UserDto savedDto = modelMapper.map(userEntity, UserDto.class);

		return savedDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// UserEntity userEntity = userRepository.findByUserId(userId);

		/*
		 * if (userEntity == null) return null;
		 */
		// UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		String albumsUrl = String.format(env.getProperty("albums.url"), userId);
		/*
		 * ResponseEntity<List<AlbumResponseModel>> albumListResponse =
		 * restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new
		 * ParameterizedTypeReference<List<AlbumResponseModel>>() { });
		 * List<AlbumResponseModel> albumsList = albumListResponse.getBody();
		 */
		logger.info("Before Calling albums ");
		List<AlbumResponseModel> albumsList = null;
		try {
			albumsList = albumClient.getAlbums(userId);
		} catch (FeignException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		logger.info("After  albums response received ");
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setAlbums(albumsList);

		return userDto;
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { UserEntity user =
	 * userRepository.findByEmail(username); if (user == null) throw new
	 * UsernameNotFoundException("User name " + username + " not found"); return new
	 * User(user.getEmail(), user.getEncryptedPassword(), true, true, true, true,
	 * new ArrayList<>()); }
	 */

	/*
	 * @Override public UserDto getUserDetailsbyEmail(String email) { UserEntity
	 * userEntity = userRepository.findByEmail(email); if (userEntity == null) throw
	 * new UsernameNotFoundException("User email " + email + " not found"); return
	 * new ModelMapper().map(userEntity, UserDto.class);
	 * 
	 * }
	 */

}
