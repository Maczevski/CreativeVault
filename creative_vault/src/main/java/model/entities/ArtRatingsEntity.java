package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "art_ratings")
public class ArtRatingsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "score")
	private float score;
	@Column(name = "art_comment")
	private String artComment;
	
	@ManyToOne
	@JoinColumn(name = "art_work_id_fk")
	private ArtWorksEntity artWork;
	
	@ManyToOne
	@JoinColumn(name = "user_id_fk")
	private UsersEntity user;
	
	public ArtRatingsEntity() {
		
	}
	
	public ArtRatingsEntity(Long id, float score, String artComment) {
		this.id = id;
		this.score = score;
		this.artComment = artComment;
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

	public String getArtComment() {
		return this.artComment;
	}

	public void setArtComment(String artComment) {
		this.artComment = artComment;
	}

	public ArtWorksEntity getArtWork() {
		return this.artWork;
	}

	public void setArtWork(ArtWorksEntity artWork) {
		this.artWork = artWork;
	}

	public UsersEntity getUser() {
		return this.user;
	}

	public void setUser(UsersEntity user) {
		this.user = user;
	}
	
}
