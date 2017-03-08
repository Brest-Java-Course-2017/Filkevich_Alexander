package com.epam.project.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Created by master on 5.3.17.
 */
public class Vagon {
    Integer id;
    String type;
    int depoId;
    int countOfSeat;
    LocalDate dateOfBuilder;

    public Vagon(Integer id, String type, int depoId, int countOfSeat, LocalDate dateOfBuilder) {
        this.id = id;
        this.type = type;
        this.depoId = depoId;
        this.countOfSeat = countOfSeat;
        this.dateOfBuilder = dateOfBuilder;
    }

    public Vagon() {
    }

    public int getDepoId() {
        return depoId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCountOfSeat() {
        return countOfSeat;
    }

    public void setCountOfSeat(int countOfSeat) {
        this.countOfSeat = countOfSeat;
    }

    public LocalDate getDateOfBuilder() {
        return dateOfBuilder;
    }

    public void setDateOfBuilder(LocalDate dateOfBuilder) {
        this.dateOfBuilder = dateOfBuilder;
    }

    @Override
    public String toString() {
        return "Vagon{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", countOfSeat=" + countOfSeat +
                ", dateOfBuilder=" + dateOfBuilder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vagon vagon = (Vagon) o;
        return countOfSeat == vagon.countOfSeat &&
                Objects.equals(id, vagon.id) &&
                Objects.equals(type, vagon.type) &&
                Objects.equals(dateOfBuilder, vagon.dateOfBuilder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, countOfSeat, dateOfBuilder);
    }
}
