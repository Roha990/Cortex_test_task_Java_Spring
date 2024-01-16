package com.guide.first_site_spring.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName, secondName, middleName;
    private LocalDateTime job_date;


    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getJob_date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return job_date.format(formatter);
    }

    public void setJob_date(LocalDateTime job_date) {
        this.job_date = job_date;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Employee() {
    }

    public Employee(String firstName, String secondName, String middleName, LocalDateTime job_date, Position position) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.job_date = job_date;
        this.position = position;
    }
}
