package org.ids.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data  @AllArgsConstructor @NoArgsConstructor @ToString 
public class Medecin extends Personne {

	private static final long serialVersionUID = 5339605626082722198L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true,nullable = false)
	private long idMedecin;
	
	private String medecinId;
	

	@OneToMany(mappedBy = "medecin")
	private List <Fiche> fiches ;
	
	@OneToMany(mappedBy = "medecin")
	private List <RendezVous> rendezVous ;
	
	@ManyToOne
	private Specialite specialite;
	
//@ManyToMany(mappedBy = "medecin", fetch=FetchType.EAGER)
	//private List <Patient> patients ;
	
	@ManyToMany
	@JoinTable(
			  name = "consultation", 
			  joinColumns =@JoinColumn(name = "medecin_id"), 
			  inverseJoinColumns=@JoinColumn(name = "patient_id")
			)
	private List <Patient> patients ;
	
	@OneToMany(mappedBy = "medecin")
	private List <Infirmier> infirmiers ;

}
