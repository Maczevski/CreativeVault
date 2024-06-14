package controller;

import java.util.List;

import model.entities.RolesEntity;
import model.services.RolesService;

public class RolesController {

	private RolesService rolesService;

    public RolesController() {
        rolesService = new RolesService();
    }

    public RolesEntity createRole(RolesEntity role) {
        return (RolesEntity) rolesService.createRole(role);
    }

    public RolesEntity updateRole(RolesEntity role) {
        return (RolesEntity) rolesService.updateRole(role);
    }

    public void deleteRole(RolesEntity role) {
        rolesService.deleteRole(role);
    }

    public RolesEntity findRoleById(Long id) {
        return (RolesEntity) rolesService.findRoleById(id);
    }

    public RolesEntity findRoleByName(String roleName) {
        return rolesService.findRoleByName(roleName);
    }

    public List<RolesEntity> findAllRoles() {
        return rolesService.findAllRoles();
    }
}
