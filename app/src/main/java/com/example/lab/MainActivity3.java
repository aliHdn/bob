package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity3 extends AppCompatActivity {
    EditText tvfn,tvln,tvpn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvfn=findViewById(R.id.fne);
        tvln=findViewById(R.id.lne);
        tvpn=findViewById(R.id.pne);
        Intent i=getIntent();
        String fn=i.getStringExtra("fn");
        String ln=i.getStringExtra("ln");
        String pn=i.getStringExtra("pn");
        tvfn.setText(fn);
        tvln.setText(ln);
        tvpn.setText(pn);

    }
    public void editbtn(View v){
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(() -> {


            Contact c = new Contact();
            c.phoneNumber = Integer.parseInt(tvpn.getText().toString());
            c.firstName = tvfn.getText().toString();
            c.lastName = tvln.getText().toString();

            MainActivity.db.contactDao().updateContact(c);
            finish();
        });

    }
}