package com.fisproject.springlbd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Invoice")
@Setter @Getter
public class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "description")  private String description;
    @Column(name = "payment_time") private Timestamp paymentTime;
    @Enumerated(EnumType.STRING) @Column(name = "status") private Status status;

    // --
    @ManyToOne
    @JoinColumn(name = "project_id1")
    private Project projectX;

    // --
    public enum Status {
        READY, SENT, PAID
    }
}
