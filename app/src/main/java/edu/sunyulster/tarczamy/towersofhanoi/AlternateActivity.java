package edu.sunyulster.tarczamy.towersofhanoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class AlternateActivity extends AppCompatActivity {

    TextView amount;
    SeekBar seeker;
    Button startButton;
    int rings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate);

        amount = (TextView) findViewById(R.id.sliderValue);
        seeker = (SeekBar) findViewById(R.id.seekBar);
        startButton = (Button) findViewById(R.id.changeButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchActivity();
            }
        });

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                amount.setText(progress + " Rings");
                rings = progress;

            }

        });
    }

    protected void launchActivity() {
        Intent i = new Intent(this, MainActivity.class);

        //Create the bundle
        Bundle bundle = new Bundle();

        //Add your data to bundle
        bundle.putInt("RingAmount", rings);

        //Add the bundle to the intent
        i.putExtras(bundle);

        startActivity(i);
    }

}
