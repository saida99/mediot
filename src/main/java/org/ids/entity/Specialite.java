package org.ids.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private int idSpecialite;
	@Column(unique = true, nullable = false)
	private String nom;

}
