package org.ids.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class Personne implements Serializable {

	
	private static final long serialVersionUID = -706804440768761254L;
	@Column(nullable = false)
	private String nom;
	@Column(nullable = false)
	private String prenom;
	@Column(nullable = false)
	private String telephone;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String province;
	@Column(nullable = false)
	private String region;
	@Column(nullable = false)
	private String centre;
	@Column(nullable = false)
	private Date dateCreation;

}
