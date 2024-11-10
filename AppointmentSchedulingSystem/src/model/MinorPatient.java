package model;

public class MinorPatient extends Patient{
	private String guardianName;
    private String schoolName;
	public MinorPatient(int id, String name, int age, String contactNumber, String address,String guardianName,String schoolName) {
		super(id, name, age, contactNumber, address);
		// TODO Auto-generated constructor stub
		this.guardianName=guardianName;
		this.schoolName=schoolName;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	@Override
	public String toString() {
		return "MinorPatient [guardianName=" + guardianName + ", schoolName=" + schoolName + "]";
	}
	
}
