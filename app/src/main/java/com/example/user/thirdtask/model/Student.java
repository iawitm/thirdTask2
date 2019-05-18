package com.example.user.thirdtask.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String lastname;
    @ColumnInfo
    public String firstname;
    @ColumnInfo
    public String patronymic;
    @ColumnInfo
    public String date;
    public Student(String lastname, String firstname, String patronymic, String date) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.date = date;
    }
}