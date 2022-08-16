package com.fisproject.springlbd.entity.employee;


public class Employee {

    private Integer id;
    private String imie, nazwisko, pesel, numerDowodu, telefon;

    public Employee(String imie, String nazwisko) {
        this.imie=imie;
        this.nazwisko=nazwisko;
    }

    public Employee(String imie, String nazwisko, String pesel, String numerDowodu, String telefon) {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.pesel=pesel;
        this.numerDowodu=numerDowodu;
        this.telefon=telefon;
    }

    public Integer getId() { return id; }

    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    public String getNumerDowodu() { return numerDowodu; }
    public void setNumerDowodu(String numerDowodu) { this.numerDowodu = numerDowodu; }

    public String getPesel() { return pesel; }
    public void setPesel(String pesel) { this.pesel = pesel; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    @Override public String toString() {
        return "\t|\t" +
                getImie()     + "\t|\t" +
                getNazwisko() + "\t|\t" +
                getPesel()    + "\t|\t" +
                getNumerDowodu() + "\t|\t" +
                getTelefon() + "\t|\t";
    }

}
