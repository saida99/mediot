package org.ids.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy = "specialite",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Medecin> medecins ;

}
