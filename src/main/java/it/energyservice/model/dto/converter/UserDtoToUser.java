/**
 * Questa classe ha lo scopo di trovare ed eventualmente associare
 * i ruoli inseriti nella registrazione dell'utente, all'utente che
 * si sta registrando; convertendo un'array di stringhe proveniente
 * dall'esterno, in un oggetto utente completo (che prevede un Set di ruoli)
 */
package it.energyservice.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import it.energyservice.exception.RoleNotFoundException;
import it.energyservice.model.Role;
import it.energyservice.model.Roles;
import it.energyservice.model.User;
import it.energyservice.model.dto.user.UserDto;
import it.energyservice.security.service.RoleService;

@Component
public class UserDtoToUser implements Converter<UserDto, User>{

	@Autowired
	private RoleService roleService;

	@Override
	public User convert(UserDto userDto) {
		User userRes = new User();
		userRes.setUsername(userDto.getUsername());
		userRes.setEmail(userDto.getEmail());
		userRes.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		/**
		 * se la lista non è vuota e dall'esterno arriva il nome di un ruolo che esiste
		 * esso viene convertito in un'oggetto di tipo ruolo ed associato all'utente
		 * che verrà restituito
		 */
		if (!userDto.getRoles().isEmpty()) {
			for (String role : userDto.getRoles()) {
				try {
					Roles roleToSave = Roles.valueOf(role);
					Role newRole = roleService.findByRoleName(roleToSave);
					userRes.getRoles().add(newRole);

				} catch (Exception e) {
					throw new RoleNotFoundException("Role " + role + " not found");
				}
			}
		} else {
			/**
			 * Se la lista di ruoli è vuota, viene impostato il ruolo di default a null
			 */
			Role newRole = roleService.findByRoleName(Roles.ROLE_USER);
			userRes.getRoles().add(newRole);
		}
		return userRes;
	}
}
