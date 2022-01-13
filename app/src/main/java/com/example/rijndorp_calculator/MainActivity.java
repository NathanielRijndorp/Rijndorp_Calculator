package com.example.rijndorp_calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.*;
import org.mariuszgromada.math.mxparser.Expression;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText inputDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDisplay = findViewById(R.id.editText);
        inputDisplay.setShowSoftInputOnFocus(false);

        inputDisplay.setOnClickListener(view -> {
            if (getString(R.string.insert_operation).equals(inputDisplay.getText().toString())) {
                inputDisplay.setText("");
            }
        });



    }
    public void updateDisplay (String stringToAdd) {

        int cursorPos = inputDisplay.getSelectionStart();
        String oldStr = inputDisplay.getText().toString();

        String leftString = oldStr.substring(0, cursorPos);
        String rightString = oldStr.substring(cursorPos);

        if (getString(R.string.insert_operation).equals(inputDisplay.getText().toString())) {
            inputDisplay.setText(stringToAdd);
            inputDisplay.setSelection(cursorPos + 1);
        }
        else {
            inputDisplay.setText(String.format("%s%s%s", leftString, stringToAdd, rightString));
            inputDisplay.setSelection(cursorPos + 1);
        }
    }
    //Buttons for the numbers.
    public void zeroBtn(View view) {
        updateDisplay("0");
    }
    public void oneBtn(View view) {
        updateDisplay("1");
    }
    public void twoBtn(View view) {
        updateDisplay("2");
    }
    public void threeBtn(View view) {
        updateDisplay("3");
    }
    public void fourBtn(View view) {
        updateDisplay("4");
    }
    public void fiveBtn(View view) {
        updateDisplay("5");
    }
    public void sixBtn(View view) {
        updateDisplay("6");
    }
    public void sevenBtn(View view) {
        updateDisplay("7");
    }
    public void eightBtn(View view) {
        updateDisplay("8");
    }
    public void nineBtn(View view) {
        updateDisplay("9");
    }
    //Buttons for the functions.
    public void clearBtn(View view) {
        inputDisplay.setText(" ");
    }
    public void plusBtn(View view) {
        updateDisplay("+");
    }
    public void minusBtn(View view) {
        updateDisplay("-");
    }
    public void multiplyBtn(View view) {
        updateDisplay("×");
    }
    public void divideBtn(View view) {
        updateDisplay("÷");
    }
    public void parenthesesBtn(View view) {
        int cursorPos = inputDisplay.getSelectionStart();
        int openParenthesis = 0;
        int closedParenthesis = 0;
        int textLen = inputDisplay.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (inputDisplay.getText().toString().substring(i, i + 1).equals("(")) {
                openParenthesis += 1;
            }
            if (inputDisplay.getText().toString().substring(i, i + 1).equals(")")) {
                closedParenthesis += 1;
            }
        }
        if (openParenthesis == closedParenthesis || inputDisplay.getText().toString().substring(textLen- 1, textLen).equals("(")) {
            updateDisplay("(");
        }
        else if (closedParenthesis < openParenthesis && !inputDisplay.getText().toString().substring(textLen- 1, textLen).equals(")")) {
            updateDisplay(")");
        }
        inputDisplay.setSelection(cursorPos + 1);
    }
    public void decimalBtn(View view) {
        updateDisplay(".");
    }
    public void equalsBtn(View view) {
        String equation = inputDisplay.getText().toString();

        equation = equation.replaceAll("×", "*");
        equation = equation.replaceAll("÷", "/");

        Expression equals = new Expression(equation);
        String answer = String.valueOf(equals.calculate());

        inputDisplay.setText(answer);
        inputDisplay.setSelection(answer.length());

        if (getString(R.string.nan).equals(inputDisplay.getText().toString())) {
            inputDisplay.setText("");
        }

    }
    public void backspaceBtn(View view) {
        int cursorPos = inputDisplay.getSelectionStart();
        int textLen = inputDisplay.getText().length();

        if (cursorPos != 0 && textLen !=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) inputDisplay.getText();
            selection.replace(cursorPos - 1, cursorPos, " ");
            inputDisplay.setText(selection);
            inputDisplay.setSelection(cursorPos - 1);
        }
    }
    public void exponentBtn(View view) {
        updateDisplay("^");
    }

}