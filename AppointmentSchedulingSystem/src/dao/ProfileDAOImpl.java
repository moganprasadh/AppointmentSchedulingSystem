package dao;

import model.Profile;
import utility.DBConnection;

import java.sql.*;

public class ProfileDAOImpl implements ProfileDAO {

    private Connection connection;

    public ProfileDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addProfile(Profile profile) throws SQLException {
        String query = "INSERT INTO Profile (patient_id, address_id, insurance_number, blood_type, allergies, emergency_contact) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, profile.getPatientId());
            stmt.setInt(2, profile.getAddressId());
            stmt.setString(3, profile.getInsuranceNumber());
            stmt.setString(4, profile.getBloodType());
            stmt.setString(5, profile.getAllergies());
            stmt.setString(6, profile.getEmergencyContact());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public Profile getProfileByPatientId(int patientId) throws SQLException {
        String query = "SELECT * FROM Profile WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Profile(
                    rs.getInt("profile_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("address_id"),
                    rs.getString("insurance_number"),
                    rs.getString("blood_type"),
                    rs.getString("allergies"),
                    rs.getString("emergency_contact"),
                    rs.getTimestamp("last_update")
                );
            }
        }
        return null;
    }

    @Override
    public boolean updateProfile(Profile profile) throws SQLException {
        String query = "UPDATE Profile SET address_id = ?, insurance_number = ?, blood_type = ?, allergies = ?, emergency_contact = ? WHERE profile_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, profile.getAddressId());
            stmt.setString(2, profile.getInsuranceNumber());
            stmt.setString(3, profile.getBloodType());
            stmt.setString(4, profile.getAllergies());
            stmt.setString(5, profile.getEmergencyContact());
            stmt.setInt(6, profile.getProfileId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean deleteProfile(int profileId) throws SQLException {
        String query = "DELETE FROM Profile WHERE profile_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, profileId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
