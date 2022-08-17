package com.fisproject.springlbd.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Client")
@Setter @Getter
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "country_code") private String countryCode;
    @Column(name = "city") private String city;



}
