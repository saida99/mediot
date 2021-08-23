package org.ids.respense;

import java.util.Date;

import org.ids.entity.Infirmier;
import org.ids.entity.Medecin;
import org.ids.entity.Patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Getter
public class FicheRespense {
	private Long idFiche;
	private String courbe;
	private Date dateCreation;
	private Medecin medecin;
	private Patient patient;
	private Infirmier infirmier;

}
