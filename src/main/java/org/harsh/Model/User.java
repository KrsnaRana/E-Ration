package org.harsh.Model;

public class User {
    private int user_id;
    private String name;
    private int age;
    private String email;
    private String password;
    private String sex;
    private String state;
    private String city;
    private String country;
    private String dob;
    private String ration_no;
    private String card_type;
    private String role;

    public User(int user_id, String name, int age, String email, String password, String sex, String state, String city, String country, String dob, String ration_no, String card_type, String role) {
        this.user_id = user_id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.state = state;
        this.city = city;
        this.country = country;
        this.dob = dob;
        this.ration_no = ration_no;
        this.card_type = card_type;
        this.role = role;
    }

    public User(int user_id, String email, String role) {
        this.user_id = user_id;
        this.email = email;
        this.role = role;
    }

    public User(String name, String password, String userType, int age, String email, String sex, String state, String city, String dob, String rNo, String card, String country) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.state = state;
        this.city = city;
        this.country = country;
        this.dob = dob;
        this.ration_no = rNo;
        this.card_type = card;
        this.role = userType;
    }

    public int getUserId() {
        return user_id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDob() {
        return dob;
    }

    public String getRationNo() {
        return ration_no;
    }

    public String getCardType() {
        return card_type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRationNo(String ration_no) {
        this.ration_no = ration_no;
    }

    public void setCardType(String card_type) {
        this.card_type = card_type;
    }
}