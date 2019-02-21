package edu.qc.seclass.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ET1, ET2, ET3, ET4, ET5, ET6, ET7, ET8;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET1 = findViewById(R.id.checkAmountValue);
        ET2 = findViewById(R.id.partySizeValue);
        ET3 = findViewById(R.id.fifteenPercentTipValue);
        ET4 = findViewById(R.id.twentyPercentTipValue);
        ET5 = findViewById(R.id.twentyfivePercentTipValue);
        ET6 = findViewById(R.id.fifteenPercentTotalValue);
        ET7 = findViewById(R.id.twentyPercentTotalValue);
        ET8 = findViewById(R.id.twentyfivePercentTotalValue);
        btn = findViewById(R.id.buttonCompute);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String amountEntered = ET1.getText().toString();
        String partySize = ET2.getText().toString();

        if (!(amountEntered.isEmpty())) {

            double ae = Double.parseDouble(amountEntered);
            int ps = Integer.parseInt(partySize);

            int tipFifteen = (int) Math.round((ae / ps) * .15);
            int tipTwenty = (int) Math.round((ae / ps) * .20);
            int tipTwentyFive = (int) Math.round((ae / ps) * .25);

            int totalFifteen = (int) Math.round((ae / ps) + tipFifteen);
            int totalTwenty = (int) Math.round((ae / ps) + tipTwenty);
            int totalTwentyFive = (int) Math.round((ae / ps) + tipTwentyFive);

            ET3.setText("" + tipFifteen);
            ET4.setText("" + tipTwenty);
            ET5.setText("" + tipTwentyFive);
            ET6.setText("" + totalFifteen);
            ET7.setText("" + totalTwenty);
            ET8.setText("" + totalTwentyFive);
        } else {
            Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        }
    }
}