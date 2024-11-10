package model;

import java.sql.Timestamp;

public class Profile {
    private int profileId;
    private int patientId;
    private int addressId;
    private String insuranceNumber;
    private String bloodType;
    private String allergies;
    private String emergencyContact;
    private Timestamp lastUpdate;

    public Profile(int profileId, int patientId, int addressId, String insuranceNumber, String bloodType, String allergies, String emergencyContact, Timestamp lastUpdate) {
        this.profileId = profileId;
        this.patientId = patientId;
        this.addressId = addressId;
        this.insuranceNumber = insuranceNumber;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.lastUpdate = lastUpdate;
    }

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    // Getters and Setters...
}
