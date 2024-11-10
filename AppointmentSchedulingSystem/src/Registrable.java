import model.MedicalHistory;
import model.Patient;
import model.Profile;

public interface Registrable {
	void registerPatient(Patient patient, Profile profile);
    void updateProfile(int patientId, Profile updatedProfile);
    Profile viewProfile(int patientId);
    MedicalHistory viewMedicalHistory(int patientId);
}
