package com.example.project5_v2;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    EditText enterWeight = findViewById(R.id.enterWeight);
    EditText enterHeight = findViewById(R.id.enterHeight);
    RadioGroup rgroup = findViewById(R.id.radioGroup);
    RadioButton checked;
    RadioButton kgBtn = findViewById(R.id.kgBtn);
    RadioButton lbsBtn = findViewById(R.id.lbsBtn);
    Button calcBMI = findViewById(R.id.calcBMI);
    TextView bmiOut = findViewById(R.id.bmiOut);
    final int lbsConst = 703;
    final String entH = "Please enter height.";
    final String entW = "Please enter weight.";
    final String unitCheck = "Kg/m";
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View.OnClickListener optionOnClickListener
                = new View.OnClickListener() {

            public void onClick(View v) {

                // to go further with check state you can manually check each radiobutton and find which one is checked.
                if(kgBtn.isChecked()) {
                    enterWeight.setHint((CharSequence)"enter weight, kgs");
                    enterHeight.setHint((CharSequence)"enter height, m");
                }
                if(lbsBtn.isChecked()) {
                    enterWeight.setHint((CharSequence)"enter weight, lbs");
                    enterHeight.setHint((CharSequence)"enter height, in");
                }
            }
        };

        kgBtn.setOnClickListener(optionOnClickListener);
        lbsBtn.setOnClickListener(optionOnClickListener);

        calcBMI.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                int selectedID = rgroup.getCheckedRadioButtonId();
                checked = findViewById(selectedID);
                String units = checked.getText().toString();
                String weight = enterWeight.getText().toString();
                String height = enterHeight.getText().toString();
                if(weight.equals("")){
                    Toast message = Toast.makeText(getApplicationContext(),entH,Toast.LENGTH_LONG);
                    message.show();
                }else if(height.equals("")){
                    Toast message = Toast.makeText(getApplicationContext(),entW,Toast.LENGTH_SHORT);
                    message.show();
                }else{
                    float res;
                    float denom = Float.parseFloat(height)*Float.parseFloat(height);
                    if(units.equals("Kg/m")){
                        res = Float.parseFloat(weight)/denom;
                    }else{
                        res = Float.parseFloat(weight)*lbsConst/denom;
                    }
                    String bmi = df.format(res);
                    bmiOut.setText((CharSequence) bmi);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
