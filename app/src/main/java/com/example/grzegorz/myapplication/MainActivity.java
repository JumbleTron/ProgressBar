package com.example.grzegorz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private int progress = 0;
    private ProgressBar progressBar2;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        this.progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        //Ustawianie maksymalnej pozycji paska
        this.progressBar2.setMax(100);
        //Wyzerowanie paska postepu przy starcie aplikacji
        this.progressBar2.setProgress(0);
        Button startButton = (Button) findViewById(R.id.button);
        // perform click event on button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call a function
                setProgressValue();

            }
        });
    }

    private void setProgressValue() {
        // przypisanie zwięksoznej wartości do zmienej
        this.progress += 10;
        // Ten warunek ustawian pasek na pozycję 0
        if(this.progress >= 110) {
            this.progress = 0;
        }
        // set the progress
        this.progressBar2.setProgress(this.progress);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
