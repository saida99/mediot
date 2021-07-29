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
@DiscriminatorValue("Patient")
@Data  @AllArgsConstructor @NoArgsConstructor @ToString 
public class Patient extends Personne {
	
	@Column(unique = true,nullable = false)
	private long idPatient;
	public Patient() {
		
	}

}
