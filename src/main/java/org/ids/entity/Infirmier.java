package org.ids.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Infirmier extends Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = -854547182136097196L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long idInfirmier;
	
	@ManyToMany( mappedBy = "infirmiers")
	private List <Patient> patients ;
	
	@OneToMany(mappedBy = "infirmier")
	private List <Fiche> fiches ;
	
	@OneToMany(mappedBy = "infirmier")
	private List <RendezVous> rendezVous ;
	
	@ManyToOne
	private Medecin  medecin;


}
