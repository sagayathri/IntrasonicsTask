package com.gayathriarumugam.intrasonics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText etNumberList;
    TextView tvSortList, tvMean, tvMedian;
    Button btnCalculate, btnClear;
    String inputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing UI components
        etNumberList = findViewById(R.id.etNumberList);
        tvSortList = findViewById(R.id.tvSortedList);
        tvMean = findViewById(R.id.tvMean);
        tvMedian = findViewById(R.id.tvMedian);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputString = String.valueOf(etNumberList.getText());
                //Checks the input is not empty
                if (!inputString.equals("")) {
                    calculateMeanMedian();
                }
                else {
                    Toast.makeText(MainActivity.this, "Nothing to calculate. Please enter numbers.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Clears all the text field
    void clearAll() {
        etNumberList.setText("");
        tvSortList.setText("");
        tvMean.setText("");
        tvMedian.setText("");
    }

    //Calculates Mean and Median
    void calculateMeanMedian() {

        hideSoftKeyboard(MainActivity.this);

        Integer[] sortedList = numberSort();
        int mean, median, sum = 0;

        //Finds the number of items in the list
        int listCount = sortedList.length;

        //Calculates Mean
        for(int i = 0; i < listCount; i++) {
            sum += sortedList[i];
        }
        mean = sum/listCount;

        //Checks if the listCount is even or odd
        if (listCount%2 == 0) {
            //Even
            median = (sortedList[(listCount/2) - 1] + sortedList[listCount/2] ) / 2;
        }
        else {
            //Odd
            median = sortedList[listCount/2];
        }

        //Convert back sortedList to string
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sortedList.length; i++) {
            Integer intValue = sortedList[i];
            builder.append(intValue);
            if (i < sortedList.length - 1) {
                builder.append(", ");
            }
        }
        etNumberList.setText("");
        tvSortList.setText(builder);
        tvMean.setText(String.valueOf(mean));
        tvMedian.setText(String.valueOf(median));
    }

    //Returns sorted array list
    Integer[] numberSort() {
        //Split given numbers separated by comma
        String stringNumbers = inputString;
        String[] numbers = stringNumbers.split("[ ,]+");

        //Convert string numbers into an array of Integer
        Integer[] intNumbers= new Integer[numbers.length];
        for (int i = 0; i < intNumbers.length; i++) {
            if(!numbers[i].equals("")) {
                intNumbers[i] = Integer.parseInt(numbers[i]);
            }
        }

        //Sort numbers in ascending order
        Collections.sort(Arrays.asList(intNumbers));

        return intNumbers;
    }

    //Hides the keyboard from showing
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
