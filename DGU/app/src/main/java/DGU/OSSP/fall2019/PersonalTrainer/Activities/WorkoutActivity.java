package DGU.OSSP.fall2019.PersonalTrainer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import DGU.OSSP.fall2019.DGU.R;

public class WorkoutActivity extends AppCompatActivity {

    Button btn_chest, btn_back, btn_legs, btn_shoulders, btn_arms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        btn_chest = findViewById(R.id.btn_chest);

        btn_back = findViewById(R.id.btn_back);

        btn_legs = findViewById(R.id.btn_legs);

        btn_shoulders = findViewById(R.id.btn_shoulders);

        btn_arms = findViewById(R.id.btn_arms);

        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkoutActivity.this, ChestActivity.class);

                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkoutActivity.this, BackActivity.class);

                startActivity(intent);
            }
        });






    }

}

