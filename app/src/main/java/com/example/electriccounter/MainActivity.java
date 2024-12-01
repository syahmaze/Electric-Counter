package com.example.electriccounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCalc, btnClear;
    private EditText etUnits, etRebate;
    private TextView ViewResult, finCostOutput,TotalRebate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        btnCalc = findViewById(R.id.btnCalc);
        btnClear = findViewById(R.id.btnClear);  // Initialize this button
        etUnits = findViewById(R.id.etConsumptions);
        etRebate = findViewById(R.id.etRebate);
        ViewResult = findViewById(R.id.ViewResult);
        finCostOutput = findViewById(R.id.finCostOutput);
        TotalRebate = findViewById(R.id.TotalRebate);

        // Set listeners for the buttons
        btnCalc.setOnClickListener(this);
        btnClear.setOnClickListener(this);


        // The Toolbar defined in the layout has the id "my_toolbar".
        Toolbar myToolbar =(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selected = item.getItemId();
        if (selected == R.id.menuAbout) {
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


    @Override
    public void onClick(View v) {
        if (v == btnCalc) {
            try {
                // Parse the input values
                int units = Integer.parseInt(etUnits.getText().toString());
                double rebate = Double.parseDouble(etRebate.getText().toString());

                // Check if rebate percentage is within the specified range (0% - 5%)
                if (rebate < 0 || rebate > 5) {
                    Toast.makeText(this, "Please make sure rebate percentage between 0% and 5%", Toast.LENGTH_SHORT).show();
                    return;
                }

                rebate /= 100; // Convert percentage to decimal

                double totalCharges = 0;
                double total = 0;

                // Calculate total charges based on unit usage
                if (units <= 200) {
                    totalCharges = units * 0.218;
                } else if (units <= 300) {
                    totalCharges = (200 * 0.218) + ((units - 200) * 0.334);
                } else if (units <= 600) {
                    totalCharges = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
                } else if (units <= 900) {
                    totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
                }else {
                    totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + (300 * 0.546) + ((units - 900) * 0.571);
                }

                // Calculate the total cost after applying the rebate
                total = totalCharges * (1 - rebate);
                double totalRebate = totalCharges * rebate;
                // Set the output text
                ViewResult.setText(String.format("Total Charge: RM %.2f", totalCharges));
                TotalRebate.setText(String.format("Total Rebate: RM RM %.2f" , totalRebate));
                finCostOutput.setText(String.format("Final Cost: RM %.2f", total));
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "Please enter numbers in the input field", Toast.LENGTH_SHORT).show();
            } catch (Exception exception) {
                Toast.makeText(this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
            }

        } else if (v == btnClear) {
            // Clear all input and output fields
            etUnits.setText("");
            etRebate.setText("");
            ViewResult.setText("");
            finCostOutput.setText("");
            TotalRebate.setText("");
        }

    }

}