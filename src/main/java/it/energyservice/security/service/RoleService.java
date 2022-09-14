/**
 * Classe di servizio per il salvataggio ed il recupero di un ruolo
 */
package it.energyservice.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.energyservice.model.Role;
import it.energyservice.model.Roles;
import it.energyservice.security.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public Role findByRoleName(Roles roleName) {
		Optional<Role> role = roleRepository.findByRoleName(roleName);
		if(role.isPresent())
			return role.get();
		else
			return null;
	}
}
