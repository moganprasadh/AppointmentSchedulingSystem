package model;

import java.util.Date;

public class MedicalHistory {
	private int patientId;
    private String diagnosis;
    private String treatment;
    private Date recordDate;
    private String doctorNotes;
	public MedicalHistory(int patientId, String diagnosis, String treatment, Date recordDate, String doctorNotes) {
		super();
		this.patientId = patientId;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.recordDate = recordDate;
		this.doctorNotes = doctorNotes;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getDoctorNotes() {
		return doctorNotes;
	}
	public void setDoctorNotes(String doctorNotes) {
		this.doctorNotes = doctorNotes;
	}
    
}
