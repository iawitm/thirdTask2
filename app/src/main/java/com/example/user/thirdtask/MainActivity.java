package com.example.user.thirdtask;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.user.thirdtask.database.AppDatabase;
import com.example.user.thirdtask.model.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    Button edit;
    Button show;
    Button add;
    String[] fio = {"Лаврентьева Ирина Сергеевна", "Сталин Иосиф Виссарионович", "Цветаева Марина Ивановна","Ленин Владимир Ильич", "Романова Елизавета Петровна"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "studentDB")
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .allowMainThreadQueries()
                .build();

        add = findViewById(R.id.add);
        show = findViewById(R.id.show);
        edit = findViewById(R.id.edit);

        db.studentDao().clearTable();
        for (int i = 0; i< 5; i++ ) {
            String[] separatedFio = fio[new Random().nextInt(5)].split(" ");
            db.studentDao().add(new Student(separatedFio[0], separatedFio[1], separatedFio[2], generateDate()));
        }

        edit.setOnClickListener(view -> {
            Student last = db.studentDao().getLastStudent();
            last.lastname = "Иванов";
            last.firstname = "Иван";
            last.patronymic = "Иванович";
            db.studentDao().update(last);
        });

        show.setOnClickListener(view -> startActivity(new Intent(this, DataActivity.class)));

        add.setOnClickListener(view -> {
            String[] separatedFio = fio[new Random().nextInt(5)].split(" ");
            db.studentDao().add(new Student(separatedFio[0], separatedFio[1], separatedFio[2], generateDate()));
        });
    }

    String generateDate(){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm", Locale.getDefault());
        format.setLenient(false);
        Date curDate = new Date();
        return format.format(curDate);
    }
}
