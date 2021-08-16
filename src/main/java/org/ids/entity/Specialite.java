package org.ids.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Specialite implements Serializable {

	private static final long serialVersionUID = -1814602136747224775L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSpecialite;
	@Column(unique = true, nullable = false)
	private String nom;
	
	@OneToMany(mappedBy = "specialite")
	private List <Medecin> medecins ;

}
