package dao;

import model.Profile;
import java.sql.SQLException;

public interface ProfileDAO {
    boolean addProfile(Profile profile) throws SQLException;
    Profile getProfileByPatientId(int patientId) throws SQLException;
    boolean updateProfile(Profile profile) throws SQLException;
    boolean deleteProfile(int profileId) throws SQLException;
}
