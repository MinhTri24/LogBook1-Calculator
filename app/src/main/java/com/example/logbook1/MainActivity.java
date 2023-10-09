package com.example.logbook1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText solutionTv;
    TextView resultTv;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnAdd, btnSub, btnMul, btnDiv, btnClear, btnEqual;

    private String firstNum = "";
    private String secondNum = "";
    private String operator = "";
    private Double result = 0.0;
    private boolean isOperatorClicked = false;
    private boolean isCalculated = false;

    private static final String EQUAL = "=";
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "x";
    private static final String DIV = "/";
    private static final String CLEAR = "C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solutionTv = findViewById(R.id.solution_tv);
        resultTv = findViewById(R.id.result_tv);

        assignId(btn0, R.id.btn0);
        assignId(btn1, R.id.btn1);
        assignId(btn2, R.id.btn2);
        assignId(btn3, R.id.btn3);
        assignId(btn4, R.id.btn4);
        assignId(btn5, R.id.btn5);
        assignId(btn6, R.id.btn6);
        assignId(btn7, R.id.btn7);
        assignId(btn8, R.id.btn8);
        assignId(btn9, R.id.btn9);
        assignId(btnAdd, R.id.btnPlus);
        assignId(btnSub, R.id.btnMinus);
        assignId(btnMul, R.id.btnMultiply);
        assignId(btnDiv, R.id.btnDivide);
        assignId(btnClear, R.id.btnClear);
        assignId(btnEqual, R.id.btnEqual);
    }

    protected void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String btnText = btn.getText().toString();

        if (btnText.matches("[0-9]")){
            if (isCalculated){
                clear();
            }
            if (isOperatorClicked){
                secondNum += btnText;
                solutionTv.setText(firstNum + " " + operator + " " + secondNum);
            } else {
                firstNum += btnText;
                solutionTv.setText(firstNum);
            }
        } else if (btnText.equals(ADD) || btnText.equals(SUB) || btnText.equals(MUL) || btnText.equals(DIV)){
            operator = btnText;
            isOperatorClicked = true;
            solutionTv.setText(firstNum + " " + operator);
        } else if (btnText.equals(EQUAL)){
            calculate();
            isCalculated = true;
        } else if (btnText.equals(CLEAR)){
            clear();
        }

        if (btnText.equals(EQUAL) && firstNum.equals("") && secondNum.equals("")){
            resultTv.setText(R.string.invalid_input);
        }
    }

    private void calculate(){
        if (operator.equals(ADD)) {
            result = Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
        } else if (operator.equals(SUB)) {
            result = Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
        } else if (operator.equals(MUL)) {
            result = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
        } else if (operator.equals(DIV)) {
            if (Double.parseDouble(secondNum) == 0){
                resultTv.setText("Error");
                return;
            }
            result = Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
        result = Math.round(result * 100.0) / 100.0;
        String finalResult = String.valueOf(result);
        if (finalResult.endsWith(".0")){
            finalResult = finalResult.substring(0, finalResult.length() - 2);
        }
        resultTv.setText(finalResult);
    }

    private void clear(){
        firstNum = "";
        secondNum = "";
        operator = "";
        result = 0.0;
        isOperatorClicked = false;
        isCalculated = false;
        solutionTv.setText("");
        resultTv.setText("0");
    }
}