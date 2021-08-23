package org.ids.respense;

import java.time.LocalTime;
import java.util.Date;

import org.ids.entity.Infirmier;
import org.ids.entity.Medecin;
import org.ids.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Getter
public class RendezVousRespense {
	private Long idRendezVouse;
	private Date dateConsultation;
	private String statut;
	private String description;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	
	private Medecin medecin;
	
	private Message message  ;
	
	private Infirmier infirmier;
}
