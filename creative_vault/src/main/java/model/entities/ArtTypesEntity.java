package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "art_types")
public class ArtTypesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "type_name")
	private String type;
	@OneToMany(mappedBy = "artType", cascade = CascadeType.ALL)
	private List<ArtWorksEntity> artWorks;
	
	public ArtTypesEntity() {
		
	}
	
	public ArtTypesEntity(Long id, String type) {
		this.setId(id);
		this.setType(type);
	}
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
