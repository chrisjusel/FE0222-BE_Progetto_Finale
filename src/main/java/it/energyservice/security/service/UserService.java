package it.energyservice.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.energyservice.model.User;
import it.energyservice.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * In questo metodo vengono convertiti i dati inseriti dal json
	 * in un utente per poi essere salvato nel db
	 * @param userDto
	 * @return
	 */
	public User save(User user) {
		return userRepository.save(user);
	}
}
