package org.ids.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.ids.entity.Infirmier;
import org.ids.entity.Medecin;
import org.ids.entity.Patient;

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
public class FicheRequest {

	@NotNull(message="le champ courbe  ne doit pas être null !")
	private String courbe;
	
	@NotNull(message="le champ date creation  ne doit pas être null !")
	private Date dateCreation;
	@NotNull(message="le champ medecin  ne doit pas être null !")
	private Medecin medecin;
	
	@NotNull(message="le champ infirmier  ne doit pas être null !")
	private Infirmier infirmier;
	
	@NotNull(message="le champ patient  ne doit pas être null !")
	private Patient patient;
}
