package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_password")
	private String password;

	@ManyToMany()
	@JoinTable(name = "users_roles",
	joinColumns = @JoinColumn(name = "user_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "role_id_fk"))
	private List<RolesEntity> roles;
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private ArtistsEntity artist;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ArtRatingsEntity> artRatings;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ArtistRatingsEntity> artistRatings;
	
	
	public UsersEntity() {

	}

	public UsersEntity(String email, String password) {
		this.id = null;
		this.email = email;
		this.password = password;
	}

	// getters and setters

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesEntity> roles) {
		this.roles = roles;
	}

	public ArtistsEntity getArtist() {
		return artist;
	}

	public void setArtist(ArtistsEntity artist) {
		this.artist = artist;
	}

	public List<ArtRatingsEntity> getArtRatings() {
		return artRatings;
	}

	public void setArtRatings(List<ArtRatingsEntity> artRatings) {
		this.artRatings = artRatings;
	}

	public List<ArtistRatingsEntity> getArtistRatings() {
		return artistRatings;
	}

	public void setArtistRatings(List<ArtistRatingsEntity> artistRatings) {
		this.artistRatings = artistRatings;
	}

}
