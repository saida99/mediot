package org.ids.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RendezVous implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6419102273606030960L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int IdRendezVous;
	@Column(nullable = false)
	private Date dateConsultation;
	@Column(nullable = false)
	private String statut;
	@Column(nullable = false)
	private String description;

	private LocalTime heureDebut;
	private LocalTime heureFin;

	@ManyToOne
	private Medecin medecin;
	
	@OneToOne
	private Message message  ;
	
	@ManyToOne
	private Infirmier infirmier;
}
