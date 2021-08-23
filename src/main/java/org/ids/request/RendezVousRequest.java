package org.ids.request;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.ids.entity.Infirmier;
import org.ids.entity.Medecin;
import org.ids.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class RendezVousRequest {

	@NotNull(message="le champ dateConsultation  ne doit pas être null !")
	private Date dateConsultation;

	@NotNull(message="le champ courbe  ne doit pas être null !")
	private String statut;

	@NotNull(message="le champ statut  ne doit pas être null !")
	private String description;

	@NotNull(message="le champ heure Debut  ne doit pas être null !")
	private LocalTime heureDebut;

	@NotNull(message="le champ heure Fin  ne doit pas être null !")
	private LocalTime heureFin;

	@NotNull(message="le champ medecin  ne doit pas être null !")
	private Medecin medecin;

	@NotNull(message="le champ message  ne doit pas être null !")
	private Message message  ;

	@NotNull(message="le champ infirmier  ne doit pas être null !")
	private Infirmier infirmier;
}
