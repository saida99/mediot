package org.ids.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Fiche implements Serializable {

	private static final long serialVersionUID = -1814602136747224775L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idFiche;
	@Column(nullable = false)
	private String courbe;
	@Column(nullable = false)
	private Date dateCreation;
	
	@ManyToOne
	private Medecin medecin;
	
	@ManyToOne
	private Infirmier infirmier;
	
	@ManyToOne
	private Patient patient;

}
