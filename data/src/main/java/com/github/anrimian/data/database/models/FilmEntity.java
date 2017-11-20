package com.github.anrimian.data.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.github.anrimian.data.database.AppDatabase.FILMS;

/**
 * Created on 20.11.2017.
 */

@Entity(tableName = FILMS)
public class FilmEntity {

    @PrimaryKey
    private long id;

    private String name;
    private String description;

    private int year;
    private int length;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
