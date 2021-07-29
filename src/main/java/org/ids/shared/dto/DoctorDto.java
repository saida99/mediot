package org.ids.shared.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	public long getIdDoctor() {
		return idDoctor;
	}
	public void setIdDoctor(long idDoctor) {
		this.idDoctor = idDoctor;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmilVerificationToken() {
		return emilVerificationToken;
	}
	public void setEmilVerificationToken(String emilVerificationToken) {
		this.emilVerificationToken = emilVerificationToken;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DoctorDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorDto(long idDoctor, String doctorId, String firstName, String lastName, String email, String province,
			String region, String center, String phone, String deviceNumber, String patientNumber, String createAt,
			String specialty, String password, String encryptedPassword, String emilVerificationToken,
			Boolean emailVerificationStatus) {
		
		this.idDoctor = idDoctor;
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.province = province;
		this.region = region;
		this.center = center;
		this.phone = phone;
		this.deviceNumber = deviceNumber;
		this.patientNumber = patientNumber;
		this.createAt = createAt;
		this.specialty = specialty;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.emilVerificationToken = emilVerificationToken;
		this.emailVerificationStatus = emailVerificationStatus;
	}
	@Override
	public String toString() {
		return "DoctorDto [idDoctor=" + idDoctor + ", doctorId=" + doctorId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", province=" + province + ", region=" + region + ", center=" + center
				+ ", phone=" + phone + ", deviceNumber=" + deviceNumber + ", patientNumber=" + patientNumber
				+ ", createAt=" + createAt + ", specialty=" + specialty + ", password=" + password
				+ ", encryptedPassword=" + encryptedPassword + ", emilVerificationToken=" + emilVerificationToken
				+ ", emailVerificationStatus=" + emailVerificationStatus + "]";
	}

}
