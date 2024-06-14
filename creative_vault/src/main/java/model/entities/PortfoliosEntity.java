package model.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "portfolios")
public class PortfoliosEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "portfolio_name")
	private String portfolioName;
	@Column(name = "portfolio_description")
	private String description;
	
	@OneToOne
	@JoinColumn(name = "artist_id_fk")
	private ArtistsEntity artist;
	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
	private List<ArtWorksEntity> artWorks = new ArrayList<ArtWorksEntity>();
	
	public PortfoliosEntity() {
		
	}
	
	public PortfoliosEntity(String name, String description, ArtistsEntity artist) {
		this.portfolioName = name;
		this.description = description;
		this.artist = artist;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPortfolioName() {
		return this.portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArtistsEntity getArtist() {
		return this.artist;
	}

	public void setArtist(ArtistsEntity artist) {
		this.artist = artist;
	}

	public List<ArtWorksEntity> getArtWorks() {
		return this.artWorks;
	}

	public void setArtWorks(List<ArtWorksEntity> artWorks) {
		this.artWorks = artWorks;
	}
	
	
}
