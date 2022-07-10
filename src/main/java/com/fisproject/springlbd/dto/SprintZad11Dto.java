package com.fisproject.springlbd.dto;

import com.fisproject.springlbd.entity.Sprint;

import java.sql.Timestamp;
import java.util.List;


public class SprintZad11Dto {

    private Long id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private Sprint.StatusType status;

    public SprintZad11Dto(Long id, String name, Timestamp startDate, Timestamp endDate, Sprint.StatusType status) {
        this.id=id;
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Sprint.StatusType getStatus() { return status; }
    public void setStatus(Sprint.StatusType status) { this.status = status; }

    public Timestamp getStartDate() { return startDate; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }

    public Timestamp getEndDate() { return endDate; }
    public void setEndDate(Timestamp endDate) { this.endDate = endDate; }
}
