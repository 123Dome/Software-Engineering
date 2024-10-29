package org.hbrs.se1.ws24.exercises.uebung4;

import java.io.Serializable;

public class UserStory implements Serializable {
    private Integer ID;
    private String title;
    private String acceptanceCriteria;
    private String project;

    // Kennzahlen für Priorisierung
    private Integer value; // Mehrwert
    private Integer penalty; // Strafe
    private Integer effort; // Aufwand
    private Integer risk; // Risiko

    // Priorisierung nach Gloger
    private double priority;

    public UserStory(Integer ID, String title, String acceptanceCriteria, String project, Integer value, Integer penalty, Integer effort, Integer risk) {
        this.ID = ID;
        this.title = title;
        this.acceptanceCriteria = acceptanceCriteria;
        this.project = project;
        this.value = value;
        this.penalty = penalty;
        this.effort = effort;
        this.risk = risk;
        this.priority = (double) (value + penalty) / (risk + effort);
    }

    public Integer getID() {
        return this.ID;
    }

    public String getTitle() {
        return title;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public String getProject() {
        return project;
    }

    public double getPriority() {
        return this.priority;
    }
}
