package org.ids.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Fiche implements Serializable {

	private static final long serialVersionUID = -1814602136747224775L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long idFiche;
	@Column(nullable = false)
	private String courbe;
	@Column(nullable = false)
	private Date dateCreation;
	

	@JsonIgnoreProperties("fiches")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Medecin medecin;
	
	@JsonIgnoreProperties("fiches")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Infirmier infirmier;
	
	@JsonIgnoreProperties
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;

}
