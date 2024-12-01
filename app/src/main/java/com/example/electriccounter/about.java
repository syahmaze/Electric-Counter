package com.example.electriccounter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.activity.EdgeToEdge;

import com.example.electriccounter.instruction;

import java.util.Objects;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Apply system window insets to the main layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // The Toolbar defined in the layout has the id "my_toolbar".
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Corrected line

        // Find the Instagram ImageView by its ID
        ImageView ig = findViewById(R.id.ig);

        // Set click listener for the Instagram ImageView
        ig.setOnClickListener(v -> {
            // Open Instagram profile
            gotoURL("https://www.instagram.com/itsaisyah7_?igsh=ZXdxaW9ocWN4dTFt");
        });

        // Find the GitHub ImageView by its ID
        ImageView gh = findViewById(R.id.github);

        // Set click listener for the GitHub ImageView
        gh.setOnClickListener(v -> {
            // Open GitHub profile
            gotoURL("https://github.com/syahmaze");
        });
    }

    // Method to open a URL in a browser
    private void gotoURL(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();
        if (selected == android.R.id.home) { // Check if the home button (back button) is pressed
            finish(); // Finish the current activity and go back to the previous one
            return true;
        } else if (selected == R.id.menuAbout) {
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            Intent aboutIntent = new Intent(this, about.class);
            startActivity(aboutIntent);
            return true;
        } else if (selected == R.id.menuIns) {
            Toast.makeText(this, "Instruction clicked", Toast.LENGTH_SHORT).show();
            Intent instructionsIntent = new Intent(this, instruction.class);
            startActivity(instructionsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}