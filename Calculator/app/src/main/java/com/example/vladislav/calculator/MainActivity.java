package com.example.vladislav.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_out;

    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_0;
    Button btn_comma;
    Button btn_two_zero;
    Button btn_cancel;
    Button btn_delete;
    Button btn_degree;
    Button btn_div;
    Button btn_mult;
    Button btn_sub;
    Button btn_add;
    Button btn_left;
    Button btn_right;
    Button btn_equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_out = (TextView) findViewById(R.id.tv_out);

        btn_1 = (Button) findViewById(R.id.one);
        btn_2 = (Button) findViewById(R.id.two);
        btn_3 = (Button) findViewById(R.id.three);
        btn_4 = (Button) findViewById(R.id.four);
        btn_5 = (Button) findViewById(R.id.five);
        btn_6 = (Button) findViewById(R.id.six);
        btn_7 = (Button) findViewById(R.id.seven);
        btn_8 = (Button) findViewById(R.id.eight);
        btn_9 = (Button) findViewById(R.id.nine);
        btn_0 = (Button) findViewById(R.id.zero);
        btn_comma = (Button) findViewById(R.id.comma);
        btn_two_zero = (Button) findViewById(R.id.two_zero);
        btn_cancel = (Button) findViewById(R.id.cancel);
        btn_delete = (Button) findViewById(R.id.delete);
        btn_degree = (Button) findViewById(R.id.degree);
        btn_div = (Button) findViewById(R.id.div);
        btn_mult = (Button) findViewById(R.id.mult);
        btn_sub = (Button) findViewById(R.id.sub);
        btn_add = (Button) findViewById(R.id.add);
        btn_left = (Button) findViewById(R.id.left);
        btn_right = (Button) findViewById(R.id.right);
        btn_equals = (Button) findViewById(R.id.equals);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_comma.setOnClickListener(this);
        btn_two_zero.setOnClickListener(this);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_out.setText("");
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_out.getText() != null && !(tv_out.getText().equals(""))){
                    String oldText = tv_out.getText().toString();
                    String updateString = oldText.substring(0, oldText.length()-1);
                    tv_out.setText(updateString);
                }
            }
        });
        btn_degree.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_mult.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = tv_out.getText().toString();
                tv_out.setText("" + compute(input));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String oldText = tv_out.getText().toString();
        String newText = oldText + btn.getText();
        tv_out.setText(newText);
    }

    /*private String opz(String exp){
        StringBuffer output = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        while (!exp.equals("")){

        }
    }*/

    static boolean isNothing(char c) {
        return c == ' ';
    }
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    static int priority(char elmnt) {
        switch (elmnt) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
    static void processOperator(LinkedList<Double> st, char op) {
        double r = st.removeLast();
        double l = st.removeLast();
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
        }
    }
    public double compute(String s) {
        LinkedList<Double> st = new LinkedList<>();
        LinkedList<Character> sign = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNothing(c))
                continue;
            if (c == '(')
                sign.add('(');
            else if (c == ')') {
                while (sign.getLast() != '(')
                    processOperator(st,sign.removeLast());
                sign.removeLast();
            } else if (isOperator(c)) {
                while (!sign.isEmpty() && priority(sign.getLast()) >= priority(c))
                    processOperator(st, sign.removeLast());
                sign.add(c);
            } else {
                String operand = "";
                while (i < s.length() && (Character.isDigit(s.charAt(i))||s.charAt(i) == '.'))
                    operand += s.charAt(i++);
                --i;
                st.add(Double.parseDouble(operand));
            }
        }
        while (!sign.isEmpty())
            processOperator(st, sign.removeLast());
        return st.get(0);
    }
}