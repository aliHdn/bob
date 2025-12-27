package com.example.lab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName="contact")
public class Contact {
    @PrimaryKey(autoGenerate = false)
    public int phoneNumber;
    @ColumnInfo(name="first_name")
    public String firstName;
    @ ColumnInfo(name="last_name")
    public String lastName;



}
