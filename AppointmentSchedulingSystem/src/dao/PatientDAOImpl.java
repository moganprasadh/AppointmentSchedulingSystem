// File: PatientDAOImpl.java
package dao;

import model.Patient;
import utility.DBConnection;
import exception.PatientNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private Connection connection;

    public PatientDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addPatient(Patient patient) {
        String query = "INSERT INTO Patient (first_name, last_name, date_of_birth, gender, contact_number, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getContactNumber());
            stmt.setString(6, patient.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM Patient WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("gender"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                );
            } else {
                throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
            }
        } catch (SQLException | PatientNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePatient(Patient patient) {
        String query = "UPDATE Patient SET first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, contact_number = ?, email = ? WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getContactNumber());
            stmt.setString(6, patient.getEmail());
            stmt.setInt(7, patient.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePatient(int patientId) {
        String query = "DELETE FROM Patient WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("gender"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

	@Override
	public Patient getPatientByContactOrEmail(String contactNumber, String email) {
		String query = "SELECT * FROM Patient WHERE contact_number = ? OR email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, contactNumber);
            preparedStatement.setString(2, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Patient(
                        resultSet.getInt("patient_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getString("gender"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public boolean isPatientRegistered(int patientId) {
		// TODO Auto-generated method stub
		return false;
	}
}
