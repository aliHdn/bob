package com.example.lab;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllContact();
    @Insert
    void insertContact(Contact... contact);
    @Query("DELETE FROM contact WHERE phoneNumber=:number")
    void deleteContact(int number);
    @Query("SELECT * FROM contact WHERE phoneNumber=:number")
    Contact getContact(int number);
    @Query("SELECT * FROM contact WHERE LOWER(first_name) LIKE '%' || LOWER(:name) || '%'")
    LiveData<List<Contact>> filter(String name);
    @Update
    void updateContact(Contact contact);
}
