package com.apps.developerblog.photoapp.api.users.service;

import com.apps.developerblog.photoapp.api.users.shared.UserDto;

//public interface UsersService extends UserDetailsService {
public interface UsersService {
	UserDto CreateUser(UserDto userDetails);

	// UserDto getUserDetailsbyEmail(String email);

	UserDto getUserByUserId(String userId);
}
