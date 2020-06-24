package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {


    //Main Layout Components
    PieChartView chart;
    Button btn_popup;
    MaterialSpinner spinner_main, spinner_sub;
    ArrayList<String> list_main, list_sub;
    View view_main;


    //Dialog Layout Components
    View view_dialog;
    Button btn_submit;
    TextView duration_txt;
    Timer t;
    int seconds = 0;
    int minutes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initializing Dialog Layout
        initializeDialogLayout();


        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Spinner main
        spinner_main = findViewById(R.id.spinner_main);
        setMainList();
        spinner_main.setItems(list_main);


        //Spinner sub category
        spinner_sub = findViewById(R.id.spinner_sub);
        setSubList();
        spinner_sub.setItems(list_sub);


        //Chart For Data
        chart = findViewById(R.id.chart);
        setChart();


        //Layout main
        view_main = findViewById(R.id.main_layout);


        //Showing dialog on popup button click
        btn_popup = findViewById(R.id.btn_popup);
        btn_popup.setOnClickListener(view -> {
            startTimer();
            view_dialog.setVisibility(View.VISIBLE);
        });

    }


    //Initializing dialog layout
    private void initializeDialogLayout() {

        view_dialog = findViewById(R.id.dialog_layout);
        btn_submit = findViewById(R.id.btn_submit);
        duration_txt = findViewById(R.id.duration_txt);

        //Hiding Dialog on Submit
        btn_submit.setOnClickListener(view -> {
            t.cancel();
            view_dialog.setVisibility(View.INVISIBLE);
        });
    }


    //Setting timer on Duration of Dialog Layout
    public void startTimer() {

        minutes = 0;
        seconds = 0;

        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(() -> {
                    duration_txt.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                    seconds++;
                    if (seconds == 60) {
                        minutes++;
                        seconds = 0;
                    }
                });
            }
        }, 0, 1000);
    }


    //Setting up sub list
    private void setSubList() {

        list_sub = new ArrayList<>();

        list_sub.add("None");
        list_sub.add("Set-up time");
        list_sub.add("HK (house Keeping)");
        list_sub.add("Product, Base Oil flushing");
        list_sub.add("Tank Changeover");
        list_sub.add("Tank Dry run");
        list_sub.add("Labelling Set up time");
        list_sub.add("Meeting");
        list_sub.add("Training");
        list_sub.add("Strap roll C/O Time");
        list_sub.add("Shrink W. C/O Time");
        list_sub.add("BOPP Tape C/O Time");
        list_sub.add("Shrink waste removal");
        list_sub.add("Label Roll C/O Time");
        list_sub.add("Labellinng D/ Time");
        list_sub.add("Manpower not planned");
        list_sub.add("No cartons, cans caps");
        list_sub.add("No Oil");
        list_sub.add("Work Order Delay");
        list_sub.add("Taping machine");
        list_sub.add("Strapping machine");
        list_sub.add("Robotiser pallet");
        list_sub.add("Shrink wrap");
        list_sub.add("Stretch Wrap");
        list_sub.add("Pick & Place");
        list_sub.add("Case Former");
        list_sub.add("Printer");
        list_sub.add("Filling Machine");
        list_sub.add("Capping Machine");
        list_sub.add("Conveyor");
        list_sub.add("Check Weigher");
        list_sub.add("Induction Sealer");
        list_sub.add("Forklift N/A at line");
        list_sub.add("Packaging material Off-loading delays");
        list_sub.add("Space Constraints in Logisitics Area");
        list_sub.add("Prestige Manpower Shortage");
        list_sub.add("Delay in Packaging Material");
        list_sub.add("Other Breakdown");
        list_sub.add("Total Downtime");


    }


    //Setting up Main List
    private void setMainList() {

        list_main = new ArrayList<>();

        list_main.add("None");
        list_main.add("Startup/Housekeeping");
        list_main.add("Product Changeover");
        list_main.add("Meeting/Training");
        list_main.add("Strap/BOPP/Shrinkwrap");
        list_main.add("Labelling");
        list_main.add("Production Critical");
        list_main.add("Maintenance Critical");
        list_main.add("Logistics Critical");
        list_main.add("Supplier Critical");
        list_main.add("Other Breakdown");
        list_main.add("Total Downtime");

    }


    //Setting up Chart Data
    private void setChart() {

        //Making partitions how many partitions required in pie chart
        ArrayList<SliceValue> pieData = new ArrayList<>();
        pieData.add(new SliceValue(100 - 39.9f, Color.parseColor("#3498DB")));
        pieData.add(new SliceValue(39.9f, Color.parseColor("#03DAC5")));


        //Loading the data in pie chart
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);

        //Setting Circle in center of pie-chart, and heading in center.
        pieChartData.setHasCenterCircle(true).setCenterText1("OEE 39.98%")
                .setCenterText1FontSize(25).setCenterText1Color(Color.parseColor("#000000"));

        chart.setPieChartData(pieChartData);
        chart.setChartRotationEnabled(false);
    }
}