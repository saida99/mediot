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

@Data  @AllArgsConstructor @NoArgsConstructor @ToString 
public class Doctor implements Serializable{
	
	
	private static final long serialVersionUID = -1814602136747224775L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDoctor;
	@Column(unique = true,nullable = false)
	private String doctorId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(unique = true,nullable = false)
	private String email;

//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

	private String province;
	private String region;
	private String center;
	private String phone;
	private String deviceNumber;
	private String patientNumber;
	@Column(nullable = false)
	private String createAt;
	@Column(nullable = false)
	private String specialty;
//	@ManyToMany
//	@JoinTable(
//			  name = "consultation", 
//			  joinColumns = @JoinColumn(name = "id"), 
//			  inverseJoinColumns = @JoinColumn(name = "id")
//			)
	
	
//	@OneToMany(mappedBy = "doctor")
//	private Set<PatientConsultation> patientConsultations;
	
	 
	private String encryptedPassword;
	private String emilVerificationToken;
//	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;
}
