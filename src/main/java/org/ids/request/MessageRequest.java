package org.ids.request;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.ids.entity.Patient;
import org.ids.entity.RendezVous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Getter
public class MessageRequest {
	@NotNull(message="le champ dateEnvoi  ne doit pas être null !")
	private Date dateEnvoi;
	
	@NotNull(message="le champ contenu  ne doit pas être null !")
	private String contenu;
	
	@NotNull(message="le champ patient du spécialité ne doit pas être null !")
	private Patient  patient;
	
	@NotNull(message="le champ rendezVous ne doit pas être null !")
	private RendezVous  rendezVous;
}
