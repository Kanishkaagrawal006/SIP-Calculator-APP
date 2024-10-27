package com.kanishka.myapplication;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import android.widget.SeekBar;

import androidx.activity.ComponentActivity;

public class Main_act extends AppCompatActivity {
    private EditText monthlyInvestmentInput, expectedReturnInput, timePeriodInput;
    private TextView Investedamount, Returns, Totalvalue;
    private Button btn;
    private PieChart pieChart;
    private SeekBar monthly,ret, timeperiod ;


    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fir);
        monthlyInvestmentInput= findViewById(R.id.editTextNumber16);
        expectedReturnInput= findViewById(R.id.editTextNumber15);
        timePeriodInput=findViewById(R.id.editTextNumber);
        Investedamount = findViewById(R.id.textView16);
        Returns = findViewById(R.id.textView8);
        Totalvalue=findViewById(R.id.textView20);
        btn = findViewById(R.id.button);
        pieChart = findViewById(R.id.pieChart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSIP();
            }
        });
    }

            private void calculateSIP() {
                double monthlyInvestment = Double.parseDouble(monthlyInvestmentInput.getText().toString());
                double expectedReturnRate = Double.parseDouble(expectedReturnInput.getText().toString());
                int duration = Integer.parseInt(timePeriodInput.getText().toString());

                int totalMonths = duration * 12;
                double monthlyRate = expectedReturnRate / 100 / 12;

                // Future Value of SIP calculation
                double futureValue = monthlyInvestment * ((Math.pow(1 + monthlyRate, totalMonths) - 1) / monthlyRate) * (1 + monthlyRate);

                // Calculate total invested amount and total interest earned
                double totalInvested = monthlyInvestment * totalMonths;
                double totalInterest = futureValue - totalInvested;
                Investedamount.setText(String.format("Total Invested: ₹%.2f", totalInvested));
                Returns.setText(String.format("Total Interest Earned: ₹%.2f", totalInterest));
                Totalvalue.setText(String.format("Final Amount:₹%.2f", futureValue));
            setupPiechart(totalInvested,totalInterest);
    }

    private void setupPiechart(double totalInvested, double totalInterest) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float)totalInvested,"Invested amount"));
        entries.add(new PieEntry((float)totalInterest,"Returns gained"));
        PieDataSet dataset= new PieDataSet(entries,"Investment Overview");
        dataset.setValueTextColor(Color.BLACK);
        ArrayList<Integer>colors = new ArrayList<>();
       colors.add(getResources().getColor(R.color.Investedamount));
        colors.add(getResources().getColor(R.color.Interest));
        dataset.setColors(colors);
        PieData pieData = new PieData(dataset);
        PieDataSet data = new PieDataSet(entries, "Invested amount");
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(14f);
        PieDataSet data2 = new PieDataSet(entries, "Returns gained");
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(14f);


        pieChart.setData(pieData);
        pieChart.invalidate();
    }


}


