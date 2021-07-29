package org.ids.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data  @AllArgsConstructor @NoArgsConstructor @ToString 
public class Medecin extends Personne {

	@Column(unique = true,nullable = false)
	private long idMedecin;
	private String medecinId;
	public Medecin() {
	
	}

}
