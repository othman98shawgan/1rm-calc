package com.app.a1RMcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText weight,reps;
    Button calc;
    TextView one_rep_max;
    TextView res90,res80,res70,res60,res50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight = findViewById(R.id.weight);
        reps = findViewById(R.id.reps);
        calc = findViewById(R.id.cacl);
        one_rep_max = findViewById(R.id.oneRepMax);
        res90 =  findViewById(R.id.res90);
        res80 =  findViewById(R.id.res80);
        res70 =  findViewById(R.id.res70);
        res60 =  findViewById(R.id.res60);
        res50 =  findViewById(R.id.res50);

        calc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                hideKeyboard();
                String weight_str = weight.getText().toString().trim();
                String reps_str = reps.getText().toString().trim();
                if(weight_str.equals("") || reps_str.equals("")){
                    //Toast.makeText(MainActivity.this, "Calculation Error", Toast.LENGTH_LONG).show();
                    printToScreen("Calculation Error");
                    return;
                }
                int weight_int = Integer.parseInt(weight_str);
                int reps_int = Integer.parseInt(reps_str);
                if(reps_int > 6){
                    printToScreen("Calculations are better when reps are in 1-6 range");
                }
                double max = weight_int/(1.0278-(0.0278*reps_int));
                String max_str = String.format("%.1f", max);
                one_rep_max.setText(max_str);
                res90.setText(String.format("%.1f", max*0.9));
                res80.setText(String.format("%.1f", max*0.8));
                res70.setText(String.format("%.1f", max*0.7));
                res60.setText(String.format("%.1f", max*0.6));
                res50.setText(String.format("%.1f", max*0.5));

            }
        });

    }
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public void printToScreen(String str){
        Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
        TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v1 != null) v1.setGravity(Gravity.CENTER);
        toast.show();

    }



}
