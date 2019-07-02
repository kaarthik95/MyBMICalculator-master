package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText weight, height;
    RadioGroup rgGender;
    TextView date, BMI, review;
    Button cal, Reset;
    double w, h, bmi;
    String result, day, month, year, hour, minute, overview, dt;
    int BMi;

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("date",dt);
        prefEdit.putString("bmi",result);
        prefEdit.putString("overview",overview);
        prefEdit.putInt("gender", rgGender.getCheckedRadioButtonId());
        prefEdit.commit();


    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String dates = prefs.getString("date", "Last Calculated Date:");
        String bmi = prefs.getString("bmi","Last Calculated BMI:");
        String overview = prefs.getString("overview","Please wait for the review of your result");
        Integer gender = prefs.getInt("gender",0);
        rgGender.check(gender);

        date.setText(dates);
        BMI.setText("Last Calculated BMI: " + bmi);
        review.setText(overview);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.editText13);
        height = findViewById(R.id.editText14);
        rgGender = findViewById(R.id.gender);
        date = findViewById(R.id.textView);
        BMI = findViewById(R.id.textView3);
        review = findViewById(R.id.textView4);
        final Calendar now = Calendar.getInstance();

        cal = findViewById(R.id.button);
        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               w = Double.valueOf(weight.getText().toString());
               h = Double.valueOf(height.getText().toString());
               BMi = (int) (w / (h * h));
               bmi = (w / (h * h));
               result = String.format("%.3f",bmi);

               overview = "";
               if (BMi < 18)
               {
                   overview += "You are underweight";
               }
               else if (BMi < 25)
               {
                   overview += "Your BMI is normal";
               }
               else if (BMi < 30)
               {
                   overview += "You are overweight";
               }
               else
               {
                   overview += "You are obese";
               }

               review.setText(overview);
               BMI.setText("Last Calculated BMI: " + result);
               day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
               month = String.valueOf(now.get(Calendar.MONTH) + 1);
               year = String.valueOf(now.get(Calendar.YEAR));
               hour = String.valueOf(now.get(Calendar.HOUR));
               minute = String.valueOf(now.get(Calendar.MINUTE));
               dt = "Last Calculated Date: " + day + "/ " + month + "/ " + year + " " + hour + ":" + minute;
               date.setText(dt);
            }
        });

        Reset = findViewById(R.id.button2);
        Reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                weight.setText("");
                height.setText("");
                date.setText("Last Calculated Date:");
                BMI.setText("Last Calculated BMI:");
                review.setText("");

            }
        });

    }
}
