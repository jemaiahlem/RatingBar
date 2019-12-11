package com.ahlem.ratingbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSubmit;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        btnSubmit = findViewById(R.id.btnSubmit);
        configurePreferences();
        //ratingBar.setRating(4);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rate = String.valueOf(ratingBar.getRating());
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                try {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("rateValue", rate);
                    editor.apply(); // editor.commit();
                    Toast.makeText(MainActivity.this,
                            getString(R.string.message_saving_value) + " : " + rate,
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configurePreferences() {
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        String rate = prefs.getString("rateValue", null);
        if (rate != null) {
            ratingBar.setRating(Float.parseFloat(rate));
            Toast.makeText(MainActivity.this,
                    getString(R.string.message_getting_value) + " : " + rate,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
