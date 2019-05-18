package com.example.user.thirdtask.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.example.user.thirdtask.model.Student;

@Database(entities = {Student.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN lastname TEXT");
            database.execSQL("ALTER TABLE student ADD COLUMN firstname TEXT");
            database.execSQL("ALTER TABLE student ADD COLUMN patronymic TEXT");
        }
    };
}