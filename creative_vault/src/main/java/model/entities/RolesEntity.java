package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RolesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "role_type")
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private List<UsersEntity> users;
	
	
	public RolesEntity() {
		
	}
	
public RolesEntity(Long id, String role) {
		this.id = id;
		this.role = role;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
