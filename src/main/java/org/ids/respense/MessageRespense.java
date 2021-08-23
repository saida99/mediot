package org.ids.respense;

import java.util.Date;

import org.ids.entity.Patient;
import org.ids.entity.RendezVous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
@Getter
@Data
public class MessageRespense {
	private Long idMessage;
	private Date dateEnvoi;
	private String contenu;
	private Patient  patient;
	private RendezVous  rendezVous;
}
