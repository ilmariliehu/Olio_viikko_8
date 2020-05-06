package com.example.week8_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    Context context = null;
    BottleDispenser bottledispenser = BottleDispenser.getInstance();
    TextView text;
    TextView text2;
    SeekBar seekBar;
    Spinner spinner;
    double value;
    String[] chs;
    int po;
    AdapterView<?> p;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = MainActivity.this;

        text = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> buylist = new ArrayList<>();
        buylist.add("Choose a bottle");
        buylist.add("Pepsi Max;0.50");
        buylist.add("Pepsi Max;1.50");
        buylist.add("Coca-Cola Zero;0.50");
        buylist.add("Coca-Cola Zero;1.50");
        buylist.add("Fanta Zero;0.50");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, buylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p = parent;
                po = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = ((float)progress / 10.0);
                text2.setText(""+value+"€");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void Add(View v){
        String add = bottledispenser.addMoney(value);
        text.setText(add);
        seekBar.setProgress(0);
    }
    public void Return (View v){
        String print =  bottledispenser.returnMoney();
        text.setText(print);
    }
    public void buylol(View v){
        if (p.getItemAtPosition(po).equals("Choose a bottle")) {

        } else {
            String ch = p.getItemAtPosition(po).toString();
            chs = ch.split(";");
            chs[0].trim();
            chs[1].trim();

            int buy = 0;
            buy = bottledispenser.buyBottle(chs[0], chs[1]);

            if (buy == -1) {
                text.setText("Add money first");
            } else if (buy == -2) {
                text.setText("There is no bottle left");
            } else if (buy == 0) {
                text.setText("KACHUNK! " + chs[0] + ";" + chs[1] + " came out of the dispenser!");
            }
        }


    }
    public void receipt(View v){
        bottledispenser.receipt(chs[0], chs[1], context);
    }


}

