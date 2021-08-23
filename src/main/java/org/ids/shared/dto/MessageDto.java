package org.ids.shared.dto;

import java.util.Date;
import org.ids.entity.Patient;
import org.ids.entity.RendezVous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
	private Long idMessage;
	private Date dateEnvoi;
	private String contenu;
	private Patient  patient;
	private RendezVous  rendezVous;
}
