package com.example.socialmediaapp.model;

public class MemberDetails {

    private String fullname;
    private String registeredDate;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;
    private String state;
    private String city;
    private String country;
    private String email;
    private String mobile;
    private String gender;
    private String birthDate;
    private String mStatus;
    private String NRIC;

    public MemberDetails() {
    }

    public MemberDetails(String fullname, String registeredDate, String address1, String address2, String address3, String postcode, String state, String city, String country, String email, String mobile, String gender, String birthDate, String mStatus) {
        this.fullname = fullname;
        this.registeredDate = registeredDate;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postcode = postcode;
        this.state = state;
        this.city = city;
        this.country = country;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.birthDate = birthDate;
        this.mStatus = mStatus;
    }

    public MemberDetails(String fullname, String registeredDate, String address1, String address2, String address3, String postcode, String state, String city, String country, String email, String mobile, String gender, String birthDate, String mStatus, String NRIC) {
        this.fullname = fullname;
        this.registeredDate = registeredDate;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postcode = postcode;
        this.state = state;
        this.city = city;
        this.country = country;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.birthDate = birthDate;
        this.mStatus = mStatus;
        this.NRIC = NRIC;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }
}