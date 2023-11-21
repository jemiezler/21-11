package test.weather.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import test.weather.Security.User;
import test.weather.Security.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}

