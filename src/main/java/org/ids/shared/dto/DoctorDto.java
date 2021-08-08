package org.ids.shared.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data 
public class DoctorDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1677883997897642148L;
	
	private long idDoctor;
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
	
	private String password;
	private String encryptedPassword;
	private String emilVerificationToken;
	private Boolean emailVerificationStatus;
	
}
