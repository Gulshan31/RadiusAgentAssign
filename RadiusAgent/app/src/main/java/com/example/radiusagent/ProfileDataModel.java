package com.example.radiusagent;

public class ProfileDataModel {
    public  ProfileDataModel(){}
    private String noOfRides;
    private String noOfFreeRides;
    private String creditValue;
    private String firstName;   // store user name
    private String lastName;
    private String profilePic; // store user profile pic link
    private String city; // Store city
    private String country;// store user country
    public ProfileDataModel(String firstName,String lastName,String profilePic,String city,String country,String noOfRides,String noOfFreeRides,String creditValue){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePic = profilePic;
        this.city = city;
        this.country  = country;
        this.noOfRides = noOfRides;
        this.noOfFreeRides = noOfFreeRides;
        this.creditValue = creditValue;
    }


    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String name) {
        this.firstName = name;
    }

    public String getlastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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


    public String getNoOfRides() {
        return noOfRides;
    }

    public void setNoOfRides(String noOfRides) {
        this.noOfRides = noOfRides;
    }

    public String getNoOfFreeRides() {
        return noOfFreeRides;
    }

    public void setNoOfFreeRides(String noOfFreeRides) {
        this.noOfFreeRides = noOfFreeRides;
    }

    public String getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(String creditValue) {
        this.creditValue = creditValue;
    }
}
