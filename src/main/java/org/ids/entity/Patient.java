package org.ids.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue("Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient extends Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = -250173034612508724L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long idPatient;

	@JsonIgnoreProperties
	@OneToOne(mappedBy = "patient")
	private Message message;
	
	@JsonIgnoreProperties
	@ManyToMany( mappedBy = "patients")
	private List <Medecin> medecins ;
	
	@JsonIgnoreProperties
	@ManyToMany
	@JoinTable(
			  name = "patient_inf", 
			  joinColumns = @JoinColumn(name = "infirmier_id"), 
			  inverseJoinColumns = @JoinColumn(name = "patients_id")
			)
	private List <Infirmier> infirmiers ;
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy = "patient")
	private List <Fiche> fiches ;


}
