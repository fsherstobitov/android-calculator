package com.byndyusoft.calculator;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.byndyusoft.calculator.domain.parser.SimpleExpressionValidator;
import com.byndyusoft.calculator.domain.parser.SimpleParser;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    private TextView screen;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView) findViewById(R.id.screen);

        presenter = new CalculatorPresenter(this, new SimpleParser(), new SimpleExpressionValidator());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void onButtonClick(View button) {
        presenter.onButtonClick(((Button) button).getText().toString());
    }

    public void onEqualsClick(View equalsButton) {
        presenter.onEquals();
    }

    @Override
    public void updateScreen(String content) {
        screen.setText(content);
    }

    @Override
    public void setValid(boolean isValid) {
        if (isValid) {
            screen.setBackgroundColor(ContextCompat.getColor(this, R.color.colorValidationPassed));
        } else {
            screen.setBackgroundColor(ContextCompat.getColor(this, R.color.colorValidationFailed));
        }
    }
}
