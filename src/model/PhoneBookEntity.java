package model;

public class PhoneBookEntity {
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String phoneNumber;

    public PhoneBookEntity(String firstname, String lastname, String address, String city, String phone_number) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.phoneNumber = phone_number;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "{" + '\n' +
                            '\t' + "First name: " + firstname + '\n' +
                            '\t' + "Last name: " + lastname + '\n' +
                            '\t' + "Address: " + city + ' ' + address + '\n' +
                            '\t' + "Phone number: " + phoneNumber + '\n' + "}";
    }
}

