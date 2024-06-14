package model.services;

import java.util.List;

import model.entities.RolesEntity;
import model.repositories.RolesRepository;

public class RolesService {
	private RolesRepository rolesRepository;

    public RolesService() {
        rolesRepository = new RolesRepository();
    }

    public RolesEntity createRole(RolesEntity role) {
        return (RolesEntity) rolesRepository.create(role);
    }

    public RolesEntity updateRole(RolesEntity role) {
        return (RolesEntity) rolesRepository.update(role);
    }

    public void deleteRole(RolesEntity role) {
        rolesRepository.delete(role);
    }

    public RolesEntity findRoleById(Long id) {
        return (RolesEntity) rolesRepository.findById(id);
    }

    public RolesEntity findRoleByName(String roleName) {
        return rolesRepository.findByName(roleName);
    }

    public List<RolesEntity> findAllRoles() {
        return rolesRepository.findAll();
    }
}
