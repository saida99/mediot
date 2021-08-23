package org.ids.shared.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.ids.entity.Infirmier;
import org.ids.entity.Medecin;
import org.ids.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RendezVousDto implements Serializable{

	private static final long serialVersionUID = 1677883997897642148L;
	private Long idRendezVous;
	private Date dateConsultation;
	private String statut;
	private String description;

	private LocalTime heureDebut;
	private LocalTime heureFin;
	private Medecin medecin;
	
	private Message message  ;
	
	private Infirmier infirmier;
}
