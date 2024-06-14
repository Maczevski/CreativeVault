package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "artist_ratings")
public class ArtistRatingsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "score")
	private float score;
	@Column(name = "artist_comment")
	private String artistComment;
	
	@ManyToOne
	@JoinColumn(name = "artist_id_fk")
	private ArtistsEntity artist;
	
	@ManyToOne
	@JoinColumn(name = "user_id_fk")
	private UsersEntity user;

	public ArtistRatingsEntity() {
		
	}
	
	public ArtistRatingsEntity(Long id, float score, String artComment) {
		this.id = id;
		this.score = score;
		this.artistComment = artComment;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public UsersEntity getUser() {
		return this.user;
	}

	public void setUser(UsersEntity user) {
		this.user = user;
	}

	public String getArtistComment() {
		return artistComment;
	}

	public void setArtistComment(String artistComment) {
		this.artistComment = artistComment;
	}

	public ArtistsEntity getArtist() {
		return artist;
	}

	public void setArtist(ArtistsEntity artist) {
		this.artist = artist;
	}

}
