package dao;

import model.Address;
import utility.DBConnection;

import java.sql.*;

public class AddressDAOImpl implements AddressDAO {
    private final Connection connection;

    public AddressDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public AddressDAOImpl() throws SQLException {
		// TODO Auto-generated constructor stub
    	this.connection = DBConnection.getConnection();
	}

	@Override
    public int addAddress(Address address) {
        String query = "INSERT INTO Address (street, city, state, postal_code, country) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getState());
            stmt.setString(4, address.getPostalCode());
            stmt.setString(5, address.getCountry());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 in case of failure
    }

    @Override
    public Address getAddressById(int addressId) {
        String query = "SELECT * FROM Address WHERE address_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, addressId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Address(
                        rs.getInt("address_id"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("postal_code"),
                        rs.getString("country")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if not found
    }

    @Override
    public boolean updateAddress(Address address) {
        String query = "UPDATE Address SET street = ?, city = ?, state = ?, postal_code = ?, country = ? WHERE address_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getState());
            stmt.setString(4, address.getPostalCode());
            stmt.setString(5, address.getCountry());
            stmt.setInt(6, address.getAddressId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAddress(int addressId) {
        String query = "DELETE FROM Address WHERE address_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, addressId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
