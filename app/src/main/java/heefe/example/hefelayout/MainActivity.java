package heefe.example.hefelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private EditText numberField;
    private Spinner factor1Spinner;
    private Spinner factor2Spinner;
    private Button calculateButton;
    private TextView resultLabel;
    private TextView resultLabel2;
    private TextView resultLabel3;
    private TextView timeLabel;

    private final double[] factors1 = {1.0, 0.365879815, 0.2027698, 0.134085414, 0.097024952, 0.074429221, 0.059711247, 0.049173422, 0.04144638, 0.035636923, 0.030993894, 0.027349724, 0.024339169, 0.021894413, 0.019767059, 0.018013725, 0.016483191, 0.015159699, 0.014031973, 0.013020109, 0.012144003, 0.011348144, 0.01064883, 0.009993244, 0.009434513, 0.008907134, 0.008437188, 0.008005973, 0.007595661, 0.007236616, 0.006897003, 0.006616203, 0.006311144, 0.006032975, 0.005801507, 0.005574596, 0.005341867, 0.005139704, 0.001579651, 0.00152574, 0.001469236, 0.001416208, 0.001370893, 0.001320436, 0.001289377, 0.001244072, 0.001200791, 0.001164028};
    private final double[] factors2 = {1.0, 0.912192212, 0.830990347, 0.758394573, 0.689012389, 0.628813261, 0.573402939, 0.521967277, 0.476312263, 0.434139793, 0.395369899, 0.360818056, 0.327681864, 0.299185404, 0.272612637, 0.24859754, 0.226133139, 0.205924465, 0.188269642, 0.171653247, 0.156487332, 0.142710597, 0.129825675, 0.11850148, 0.107475086, 0.098373391, 0.089220989, 0.081603197, 0.074252923, 0.067835355, 0.061677373, 0.05633561, 0.051062706, 0.04661405, 0.04251113, 0.038774914, 0.035341713, 0.032219439, 0.029353226, 0.026772624, 0.02430423, 0.022163245, 0.020210435, 0.018435179, 0.016803243, 0.015333842, 0.013953225, 0.012729601, 0.011589325, 0.010567514, 0.009624157, 0.008764573, 0.007989183, 0.007286214, 0.006631535, 0.006053903, 0.005510875, 0.005021727, 0.00458452, 0.004158676, 0.003804925};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberField = findViewById(R.id.editText);
        factor1Spinner = findViewById(R.id.spinner);
        factor2Spinner = findViewById(R.id.spinner2);
        calculateButton = findViewById(R.id.calculateButton);
        resultLabel = findViewById(R.id.resultlabel);
        resultLabel2 = findViewById(R.id.resultlabel2);
        resultLabel3 = findViewById(R.id.resultlabel3);
        timeLabel = findViewById(R.id.resultlabel4);

        ArrayAdapter<CharSequence> factor1Adapter = ArrayAdapter.createFromResource(this, R.array.factor1_values, android.R.layout.simple_spinner_item);
        factor1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        factor1Spinner.setAdapter(factor1Adapter);

        ArrayAdapter<CharSequence> factor2Adapter = ArrayAdapter.createFromResource(this, R.array.factor2_values, android.R.layout.simple_spinner_item);
        factor2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        factor2Spinner.setAdapter(factor2Adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
                updateTime();
            }
        });
    }

    private void calculateResult() throws NumberFormatException {
        String input = numberField.getText().toString();
        if (input.isEmpty()) {
            // Wenn das Feld leer ist, zeige eine Fehlermeldung oder handle es entsprechend
            Toast.makeText(this, "Bitte geben Sie eine Zahl ein", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double number = Double.parseDouble(input);
            double factor1 = factors1[factor1Spinner.getSelectedItemPosition()];
            double factor2 = factors2[factor2Spinner.getSelectedItemPosition()];

            double result = (number * 0.4603 + 15.34) * (factor1 * factor2);
            double dividedResult = result / 3.0;
            double result2 = result * 7;

            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            String formattedResult = decimalFormat.format(result);
            String formattedDividedResult = decimalFormat.format(dividedResult);
            String formattedResult2 = decimalFormat.format(result2);

            resultLabel.setText(formattedResult);
            resultLabel2.setText(formattedDividedResult);
            resultLabel3.setText(formattedResult2);
        } catch (NumberFormatException e) {
            // Fehler beim Parsen der Zahl, zeige eine Fehlermeldung oder handle es entsprechend
            Toast.makeText(this, "Ungültige Eingabe", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTime() {
        String selectedFactor = factor1Spinner.getSelectedItem().toString();
        Log.d("selected", selectedFactor);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        Log.d("Timezone", ""+calendar.getTimeZone());
        // Überprüfe den ausgewählten Faktor und aktualisiere die Uhrzeit entsprechen
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(selectedFactor));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String currentTime = String.format("%02d:%02d", hour, minute);
        showTime(currentTime);
    }

    private void showTime(String time) {
        timeLabel.setText("Uhrzeit: " + time);
    }

}
