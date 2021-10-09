package com.yash.spacexcrew.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity
public class CrewMember {

    @NonNull
    @PrimaryKey
    public String imageUrl;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "agency")
    public String agency;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "wikipediaUrl")
    public String wikipediaUrl;

    public CrewMember(String imageUrl, String name, String agency, String status, String wikipediaUrl) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.agency = agency;
        this.status = status;
        this.wikipediaUrl = wikipediaUrl;
    }
}
