package model;

public class AdultPatient extends Patient{
	private String occupation;
    private String emergencyContact;

	public AdultPatient(int id, String name, int age, String contactNumber, String address,String occupation,String emergencyContact) {
		super(id, name, age, contactNumber, address);
		this.occupation=occupation;
		this.emergencyContact=emergencyContact;
	}

	@Override
	public String toString() {
		return "AdultPatient [occupation=" + occupation + ", emergencyContact=" + emergencyContact + "]";
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
}
