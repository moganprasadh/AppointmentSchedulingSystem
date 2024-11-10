package dao;

import model.Address;

public interface AddressDAO {
    int addAddress(Address address);
    Address getAddressById(int addressId);
    boolean updateAddress(Address address);
    boolean deleteAddress(int addressId);
}
