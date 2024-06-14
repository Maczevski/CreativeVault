package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.RolesEntity;
import model.entities.UsersEntity;
import model.repositories.UsersRepository;

public class UsersService {

	private RolesService rolesService = new RolesService();
	private UsersRepository repository;

	public UsersService() {
		this.repository = new UsersRepository();
	}

	public UsersEntity createUser(UsersEntity entity) {

		return (UsersEntity) repository.create(entity);
	}

	public UsersEntity updateUser(UsersEntity entity) {
		return (UsersEntity) repository.update(entity);
	}

	public void deleteUser(UsersEntity entity) {
		repository.delete(entity);
	}

	public UsersEntity findUserById(UsersEntity entity) {
		return (UsersEntity) repository.findById(entity.getId());
	}

	public UsersEntity findUserByEmail(String email) {
		return (UsersEntity) repository.findByEmail(email);
	}

	public List<UsersEntity> findAllUsers() {
		return repository.findAll();
	}

	public UsersEntity verifyEmail(String email) {
		UsersEntity userLogin = findUserByEmail(email);
		if (userLogin != null) {
			return userLogin;
		}
		return null;
	}

	public boolean verifyPassword(UsersEntity entity, String password) {
		UsersEntity userInDB = findUserById(entity);
		
		if (userInDB != null && userInDB.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public UsersEntity registerUser(UsersEntity user) {
		List<RolesEntity> userRoles = new ArrayList<>();
		RolesEntity defaultRole = rolesService.findRoleByName("default");
		if (defaultRole != null) {
			userRoles.add(defaultRole);
			user.setRoles(userRoles);
			return createUser(user);
		}
		return null;
	}

	public boolean hasArtistRole(UsersEntity user) {
		if (user.getRoles().stream().anyMatch(role -> role.getRole().equals("artist"))) {
			return true;
		} else {
			return false;
		}

	}
	
	public UsersEntity setArtistRole(UsersEntity user) {
		List<RolesEntity> userRoles = user.getRoles();
		RolesEntity artistRole = rolesService.findRoleByName("artist");
		if(artistRole != null) {
			userRoles.add(artistRole);
			user.setRoles(userRoles);
			UsersEntity artistUser = updateUser(user);
			return artistUser;
		}else {
			return null;
		}
		
	}

	public void removeArtistRole(UsersEntity userInDB) {

		RolesEntity artistRole = null;
		for(RolesEntity role : userInDB.getRoles()) {
			if(role.getRole().equals("artist")) {
				artistRole = role;
	            break;
			}
			
		}
		if(artistRole != null) {
			userInDB.getRoles().remove(artistRole);
		}
		updateUser(userInDB);
	}
	
	public UsersEntity updateEmail(UsersEntity entity, String email) {
		
		UsersEntity userInDB = findUserById(entity);
		userInDB.setEmail(email);
		UsersEntity updatedUser = updateUser(userInDB);
		
		return updatedUser;
	}
	
	public UsersEntity updatePassword(UsersEntity entity, String password) {
		
		UsersEntity userInDB = findUserById(entity);
		userInDB.setPassword(password);
		UsersEntity updatedUser = updateUser(userInDB);
		
		return updatedUser;
	}
	
	public boolean deleteUserProfile(UsersEntity user) {
		
		UsersEntity userInDB = findUserById(user);
		if(userInDB != null) {
			deleteUser(userInDB);
			return true;
		}
		return false;
		
		
	}
	

}
