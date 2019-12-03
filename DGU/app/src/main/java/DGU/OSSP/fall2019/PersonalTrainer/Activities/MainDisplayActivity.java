package DGU.OSSP.fall2019.PersonalTrainer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import DGU.OSSP.fall2019.DGU.R;

/*
    메인 화면
*/

public class MainDisplayActivity extends AppCompatActivity {


    ImageButton btn_setting, btn_graph, btn_workout, btn_journal, btn_meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn_workout = findViewById(R.id.btn_workout);

        btn_meals = findViewById(R.id.btn_meals);

        btn_setting = findViewById(R.id.btn_setting);
        btn_graph = findViewById(R.id.btn_graph);
        btn_journal = findViewById(R.id.btn_journal);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        String strColor = "#004489";

        // tv_pass.setText(userPass);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, SettingActivity.class);

                startActivity(intent);
            }
        });

        btn_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, BarChart_Activity.class);

                startActivity(intent);
            }
        });

        btn_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, WorkoutActivity.class);

                startActivity(intent);
            }
        });

        btn_meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });


        btn_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, WorkoutJournalActivity.class);

                startActivity(intent);
            }
        });

    }
}

