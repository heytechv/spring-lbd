package com.fisproject.springlbd.entity.employee;

import com.fisproject.springlbd.entity.team.TeamType;

public class Employee {

    public Integer id;
    public String imie, nazwisko, pesel, numerDowodu, telefon;
    public TeamType currentTeam;

    public Employee(String imie, String nazwisko) {
        this.imie=imie;
        this.nazwisko=nazwisko;
    }





}
