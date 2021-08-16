package org.ids.shared.dto;

import java.io.Serializable;
import java.util.Date;

import org.ids.entity.Infirmier;
import org.ids.entity.Medecin;
import org.ids.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class FicheDto implements Serializable{

	private static final long serialVersionUID = 1677883997897642148L;
	private int idFiche;
	private String courbe;
	private Date dateCreation;
	private Medecin medecin;
	private Infirmier infirmier;
	private Patient patient;
}
