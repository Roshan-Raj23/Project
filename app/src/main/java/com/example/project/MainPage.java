package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.HashMap;

public class MainPage extends AppCompatActivity {

    private LinearLayout weekLayout;
    private ScrollView mainLayout;
    private View dynamicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainLayout = findViewById(R.id.scrollView);
        addToday();

    }

    public void addToday(){

        Date currentDayDate = new Date();
        String currentDateString = currentDayDate.toString();

        String day = currentDateString.substring(0 , 3) , temp;
        String month = currentDateString.substring(4,7);
        String date = currentDateString.substring(8 , 10);

        String days[] = {"Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday" , "Sunday"};
        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for(int i = 0;i< 7;i++){
            temp = days[i].substring(0 , 3);
            if (temp.equals(day)){
                day = days[i];
                break;
            }
        }

        for(int i = 0;i< 12;i++){
            temp = months[i].substring(0 , 3);
            if (temp.equals(month)){
                month = months[i];
                break;
            }
        }

        if (date.charAt(1) == '1')
            date = date + "st";
        else if (date.charAt(1) == '2')
            date = date + "nd";
        else if (date.charAt(1) == '3')
            date = date + "rd";
        else
            date = date + "th";

        String finalText = date + " " + month + ", " + day;

        TextView currentDay = findViewById(R.id.currentDate);
        currentDay.setText(finalText);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View scrollContent;


        if (day == "Monday")
            scrollContent = inflater.inflate(R.layout.activity_monday, null);
        else if (day == "Tuesday")
            scrollContent = inflater.inflate(R.layout.activity_tuesday, null);
        else if (day == "Wednesday")
            scrollContent = inflater.inflate(R.layout.activity_wednesday, null);
        else if (day == "Thursday")
            scrollContent = inflater.inflate(R.layout.activity_thursday, null);
        else if (day == "Friday")
            scrollContent = inflater.inflate(R.layout.activity_friday, null);
        else if (day == "Saturday")
            scrollContent = inflater.inflate(R.layout.activity_saturday, null);
        else
            scrollContent = inflater.inflate(R.layout.activity_sunday, null);

        if (mainLayout != null) {
            mainLayout.addView(scrollContent);
        }
    }



    public void todayClick(View view) {

        Button btn = findViewById(R.id.todayButton);btn.setAlpha(1);
        btn = findViewById(R.id.weekButton);btn.setAlpha(0.65F);

        mainLayout.removeAllViews();

        addToday();

    }

    public void weekClick(View view) {

        Button btn = findViewById(R.id.weekButton);btn.setAlpha(1);
        btn = findViewById(R.id.todayButton);btn.setAlpha(0.65F);

        mainLayout.removeAllViews();

        weekLayout = findViewById(R.id.weekPage);

        dynamicView = LayoutInflater.from(MainPage.this)
                .inflate(R.layout.week_page, weekLayout, false);

        mainLayout.addView(dynamicView);
    }

    public void suggestionPage(View view) {

        Intent intent = new Intent(this , SuggestionPage.class);
        startActivity(intent);

    }

    public void ratingPage(View view) {

        Intent intent = new Intent(this , RatingActivity.class);
        startActivity(intent);

    }

    public void mondayCall(View view) {

        Intent intent = new Intent(this , ActivityMonday.class);
        startActivity(intent);

    }

    public void tuesdayCall(View view) {

        Intent intent = new Intent(this , ActivityTuesday.class);
        startActivity(intent);

    }

    public void wednesdayCall(View view) {

        Intent intent = new Intent(this , ActivityWednesday.class);
        startActivity(intent);

    }

    public void ThursdayCall(View view) {

        Intent intent = new Intent(this , ActivityThursday.class);
        startActivity(intent);

    }

    public void fridayCall(View view) {

        Intent intent = new Intent(this , ActivityFriday.class);
        startActivity(intent);

    }

    public void saturdayCall(View view) {

        Intent intent = new Intent(this , ActivitySaturday.class);
        startActivity(intent);

    }

    public void sundayCall(View view) {

        Intent intent = new Intent(this , ActivitySunday.class);
        startActivity(intent);

    }

}