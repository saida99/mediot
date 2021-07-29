package org.ids.request;




import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DoctorRequest {
	
// 	private long doctorID;
	@NotNull(message="le champ prénom ne doit pas être null !")
	@Size(min=2)
	private String firstName;
	
	@NotNull(message="le champ nom ne doit pas être null !")
	@Size(min=2, message="le champ doit avoir au moins 3 Caracteres !")
	private String lastName;
	
	@NotNull(message="le champ email ne doit pas être null !")
	@Email
	private String email;
	
	@NotNull(message="le champ mot de passe ne doit pas être null !")
	@Size(min=6, max= 14)
	private String password;
	
	private String province;
	private String region;
	private String center;
	private String phone;
	private String deviceNumber;
	private String patientNumber;
	private String createAt;
	
	@NotNull(message="le champ spécialité ne doit pas être null !")
	private String specialty;

}
