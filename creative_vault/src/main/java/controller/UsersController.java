package controller;

import java.util.List;

import model.entities.UsersEntity;
import model.services.UsersService;

public class UsersController {
	private UsersService service;

	public UsersController() {
		this.service = new UsersService();
	}

	public UsersEntity createUser(UsersEntity entity) {
		return service.createUser(entity);
	}

	public UsersEntity updateUser(UsersEntity entity) {
		return service.updateUser(entity);
	}

	public void deleteUser(UsersEntity entity) {
		service.deleteUser(entity);
	}

	public UsersEntity findUserById(UsersEntity entity) {
		return (UsersEntity) service.findUserById(entity);
	}

	public UsersEntity findUserByEmail(String email) {
		return service.findUserByEmail(email);
	}

	public boolean verifyPassword(UsersEntity entity, String password) {
		return service.verifyPassword(entity, password);
		
	}

	public UsersEntity verifyEmail(String email) {
		return service.verifyEmail(email);
	}
	
	public UsersEntity registerUser(UsersEntity user) {
		return service.registerUser(user);
	}
	
	public boolean hasArtistRole(UsersEntity user) {
		return service.hasArtistRole(user);
	}
	
	public UsersEntity setArtistRole(UsersEntity user) {
		return service.setArtistRole(user);
	}

	public List<UsersEntity> findAllUsers() {
		return service.findAllUsers();
	}
	
	public UsersEntity updateEmail(UsersEntity entity, String email) {
		return service.updateEmail(entity, email);
	}
	
	public UsersEntity updatePassword(UsersEntity entity, String password) {
		return service.updatePassword(entity, password);
	}
	
	public boolean deleteUserProfile(UsersEntity user) {
		return service.deleteUserProfile(user);
	}

}
