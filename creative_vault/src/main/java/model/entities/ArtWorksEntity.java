package model.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "art_works")
public class ArtWorksEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "picture")
	private String picture;
	@Column(name = "upload_date")
	private LocalDateTime uploadDate;
	@Column(name = "art_description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "portfolio_id_fk")
	private PortfoliosEntity portfolio;
	@ManyToOne
	@JoinColumn(name = "art_type_id_fk")
	private ArtTypesEntity artType;
	
	@OneToMany(mappedBy = "artWork", cascade = CascadeType.ALL)
	List<ArtRatingsEntity> artRatings;
	
	
	public ArtWorksEntity() {
		
	}
	
	public ArtWorksEntity(Long id, String title, String picture, LocalDateTime uploadDate) {
		this.id = id;
        this.title = title;
        this.picture = picture;
        this.uploadDate = uploadDate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public LocalDateTime getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public PortfoliosEntity getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(PortfoliosEntity portfolio) {
		this.portfolio = portfolio;
	}

	public ArtTypesEntity getArtType() {
		return this.artType;
	}

	public void setArtType(ArtTypesEntity artType) {
		this.artType = artType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ArtRatingsEntity> getArtRatings() {
		return artRatings;
	}

	public void setArtRatings(List<ArtRatingsEntity> artRatings) {
		this.artRatings = artRatings;
	}
	
	
}
