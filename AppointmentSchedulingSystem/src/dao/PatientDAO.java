package dao;

import model.Patient;
import java.util.List;

public interface PatientDAO {
    // Method to add a new patient to the database
    boolean addPatient(Patient patient);

    // Method to retrieve a patient by ID
    Patient getPatientById(int patientId);

    // Method to update an existing patient's profile
    boolean updatePatient(Patient patient);

    // Method to delete a patient from the database
    boolean deletePatient(int patientId);

    // Method to retrieve all patients
    List<Patient> getAllPatients();

	Patient getPatientByContactOrEmail(String contactNumber, String email);

	boolean isPatientRegistered(int patientId);

}
