package com.example.lab;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Contact.class},version=1)
public abstract class app extends RoomDatabase {
    public abstract ContactDao contactDao();
}
