package User;

public class User {
    private String surname;
    private String name;
    private String patronymic;
    private String dateOfBurn;//рождения
    private String passport;
    private String passportOut;
    private String dateOfDate;//выдача
    private String mestoOfBurn="NULL";
    private String city;
    private String adress;
    private String phone;

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBurn() {
        return dateOfBurn;
    }

    public void setDateOfBurn(String dateOfBurn) {
        this.dateOfBurn = dateOfBurn;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassportOut() {
        return passportOut;
    }

    public void setPassportOut(String passportOut) {
        this.passportOut = passportOut;
    }

    public String getDateOfDate() {
        return dateOfDate;
    }

    public void setDateOfDate(String dateOfDate) {
        this.dateOfDate = dateOfDate;
    }

    public String getMestoOfBurn() {
        return mestoOfBurn;
    }

    public void setMestoOfBurn(String mestoOfBurn) {
        this.mestoOfBurn = mestoOfBurn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
