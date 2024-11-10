package service;

import dao.PatientDAO;
import dao.PatientDAOImpl;
import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import dao.AddressDAO;
import dao.AddressDAOImpl;
import exception.PatientAlreadyRegisteredException;
import exception.PatientNotFoundException;
import model.Patient;
import model.Profile;
import model.Address;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PatientService {
    private final PatientDAO patientDAO;
    private final ProfileDAO profileDAO;
    private final AddressDAO addressDAO;

    public PatientService() throws SQLException {
        this.patientDAO = new PatientDAOImpl();
        this.profileDAO = new ProfileDAOImpl();
        this.addressDAO = new AddressDAOImpl();
    }

    public void registerPatient(Patient patient, Profile profile, Address address) throws PatientAlreadyRegisteredException, SQLException {
        Patient existingPatient = patientDAO.getPatientByContactOrEmail(patient.getContactNumber(), patient.getEmail());
        if (existingPatient != null) {
            throw new PatientAlreadyRegisteredException("A patient with the same contact number or email already exists.");
        }

        boolean success = patientDAO.addPatient(patient);
        if (!success) {
            throw new RuntimeException("Error while registering the patient.");
        }

        int addressId = addressDAO.addAddress(address);
        profile.setAddressId(addressId); 
        profile.setPatientId(patient.getId()); 

        profileDAO.addProfile(profile); 
    }

    public void updateProfile(int patientId, String firstName, String lastName, LocalDate dateOfBirth, String gender, String contactNumber, String email) throws PatientNotFoundException {
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDateOfBirth(dateOfBirth);
        patient.setGender(gender);
        patient.setContactNumber(contactNumber);
        patient.setEmail(email);

        boolean success = patientDAO.updatePatient(patient);
        if (!success) {
            throw new RuntimeException("Error while updating the patient profile.");
        }
    }

    public void updateProfile(int patientId, Profile updatedProfile) throws PatientNotFoundException, SQLException {
        if (!patientDAO.isPatientRegistered(patientId)) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }

        updatedProfile.setPatientId(patientId);
        profileDAO.updateProfile(updatedProfile);
    }

    public Patient viewProfile(int patientId) throws PatientNotFoundException {
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }
        return patient;
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public void deletePatient(int patientId) throws PatientNotFoundException {
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }

        boolean success = patientDAO.deletePatient(patientId);
        if (!success) {
            throw new RuntimeException("Error while deleting the patient.");
        }
    }
}
