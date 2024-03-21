package com.example.socialmediaapp.data.user;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table",
        indices = {@Index(value = "email", unique = true)}
)
public class User {


    public User() {
    }

    public User(int id, String email, String password, int dobDay, int dobMonth, int dobYear, int age, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.dobDay = dobDay;
        this.dobMonth = dobMonth;
        this.dobYear = dobYear;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String email;
    private String password;
    private int dobDay;

    private int dobMonth;

    private int dobYear;

    private int age;
    private String firstName;
    private String lastName;

    private Long dobMillis;

    private String gender;

    private String bio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDobDay() {
        return dobDay;
    }

    public void setDobDay(int dobDay) {
        this.dobDay = dobDay;
    }

    public int getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(int dobMonth) {
        this.dobMonth = dobMonth;
    }

    public int getDobYear() {
        return dobYear;
    }

    public void setDobYear(int dobYear) {
        this.dobYear = dobYear;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDobMillis() {
        return dobMillis;
    }

    public void setDobMillis(Long dobMillis) {
        this.dobMillis = dobMillis;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User(int id, String email, String password, String firstName, String lastName, Long dobMillis) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dobMillis = dobMillis;
    }

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
