package com.example.project5_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Advice based on BMI");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        float bmi = MainActivity.returnBMI();

        final ImageView img = findViewById(R.id.adviceImg);
        final Button button = findViewById(R.id.backButton);
        final TextView advice = findViewById((R.id.adviceText));

        final double normal_min = 18.5;
        final double overweight_min = 25;
        final double obese_min = 30;


        if(bmi < normal_min){
            img.setImageResource(R.drawable.underweight);
            advice.setText("Underweight");
        } else if(bmi >= normal_min && bmi < overweight_min){
            img.setImageResource(R.drawable.normal);
            advice.setText("Normal");
        } else if(bmi >= overweight_min && bmi < obese_min){
            img.setImageResource(R.drawable.overweight);
            advice.setText("Overweight");
        } else{
            img.setImageResource(R.drawable.obese);
            advice.setText("Obese");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.resetBmiFinal();
                launchActivity();
            }
        });

    }


    private void launchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
