package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class ArtistsEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "artist_name")
	private String artistName;
	@Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;
	

	@OneToOne
	@JoinColumn(name = "user_id_fk")
	private UsersEntity user;
	
	@OneToOne(mappedBy = "artist", cascade = CascadeType.ALL)
	private PortfoliosEntity portfolio;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	List<ArtistRatingsEntity> artistRatings;
	
	public ArtistsEntity() {
		super();
	}

	public ArtistsEntity(String name, UsersEntity user) {
		this.artistName = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtistName() {
		return this.artistName;
	}

	public void setArtistName(String name) {
		this.artistName = name;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}
	
	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public UsersEntity getUser() {
		return this.user;
	}

	public void setUser(UsersEntity user) {
		this.user = user;
	}

	public PortfoliosEntity getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(PortfoliosEntity portfolio) {
		this.portfolio = portfolio;
	}

	public List<ArtistRatingsEntity> getArtistRatings() {
		return artistRatings;
	}

	public void setArtistRatings(List<ArtistRatingsEntity> artistRatings) {
		this.artistRatings = artistRatings;
	}

}
