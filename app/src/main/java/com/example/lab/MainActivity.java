    package com.example.lab;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import androidx.appcompat.widget.SearchView;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.lifecycle.Observer;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import androidx.room.Room;
    import java.util.List;
    public class MainActivity extends AppCompatActivity {
        RecyclerView rv;
        CA ca;
        public  static app db;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = Room.databaseBuilder(this, app.class, "contact")
                    .allowMainThreadQueries()
                    .build();
            rv = findViewById(R.id.recy);
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            db.contactDao().getAllContact().observe(this, new Observer<List<Contact>>() {

                public void onChanged(List<Contact> contacts) {
                    ca = new CA(MainActivity.this, contacts);
                    rv.setAdapter(ca);
                }
            });
        }
        public boolean onCreateOptionsMenu(Menu m){
            getMenuInflater().inflate(R.menu.menu,m);
                MenuItem si=m.findItem(R.id.seacrh);
                SearchView sv=(SearchView)si.getActionView();
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
                    public boolean onQueryTextSubmit(String s) {
                        searchUser(s);
                        return false;
                    }
                    public boolean onQueryTextChange(String s) {
                        searchUser(s);
                        return false;
                    }
                private void searchUser(String n) {

                    if (n == null || n.trim().isEmpty()) {
                        // Reset list
                        db.contactDao().getAllContact().observe(MainActivity.this, contacts -> {
                            ca.setContacts(contacts);
                        });
                        return;
                    }
                    db.contactDao().filter(n).observe(MainActivity.this, contacts -> {
                        ca.setContacts(contacts);
                    });
                }

            });
            return super.onCreateOptionsMenu(m);
        }
        public void navi(View v){
            startActivity(new Intent(this,MainActivity2.class));
        }
    }