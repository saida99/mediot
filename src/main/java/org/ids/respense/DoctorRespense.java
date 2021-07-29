package org.ids.respense;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DoctorRespense {
	
	private String doctorId;
	private String firstName;
	private String lastName;
	private String email;
	private String province;
	private String region;
	private String center;
	private String phone;
	private String deviceNumber;
	private String patientNumber;
	private String createAt;
	private String specialty;

}
