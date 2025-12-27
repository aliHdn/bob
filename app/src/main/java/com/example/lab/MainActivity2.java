package com.example.lab;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    EditText fn, ln, pn;
    public static app db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fn = findViewById(R.id.fn);
        ln = findViewById(R.id.ln);
        pn = findViewById(R.id.pn);
    }


    public void ab(View v) {
        Contact c = new Contact();
        c.firstName = fn.getText().toString();
        c.lastName = ln.getText().toString();
        c.phoneNumber= Integer.parseInt(pn.getText().toString());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.db.contactDao().insertContact(c);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity2.this, "added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });

    }
}