package edu.kvcc.cis298.cis298assignment2;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Set;

public class TemperatureConverter extends AppCompatActivity {

    private static final String TAG = "TemperatureConverter";

    private String mFromType;
    private String mToType;
    private Button mConvertButton;
    private double mUserInput;
    ConversionMath conversionMath = new ConversionMath();
    private String mConverted;
    public TextView result;
    public TextView formula;
    public EditText user_input;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);
        result = (TextView) findViewById(R.id.coversion_answer);
        formula = (TextView) findViewById(R.id.conversion_formula);
        Log.d(TAG, "onCreate(Bundle) called");

        mConvertButton = (Button) findViewById(R.id.convert_button);
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_input = (EditText)findViewById(R.id.user_input);
                if (user_input != null){
                    ParseUserInput(savedInstanceState);
                    mConverted = conversionMath.CalculateConversion(mFromType, mToType, mUserInput);
                    PrintResult();
                    PrintFormula();
                } else {
                    Toast.makeText(TemperatureConverter.this, "You must enter a value to continue",
                            Toast.LENGTH_SHORT).show();
                    onCreate(savedInstanceState);
                }
            }
        });
    }

    public void ParseUserInput(Bundle savedInstanceState){
        try {
            mUserInput = Double.parseDouble(user_input.getText().toString());
        }
        catch (Error error){
            Toast.makeText(this, "You must enter a number to continue", Toast.LENGTH_SHORT).show();
            onCreate(savedInstanceState);
        }
    }

    public void fromSelection(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.celsius_frombutton:
                if (checked)
                    mFromType = "Celsius";
                break;
            case R.id.farenheit_frombutton:
                if (checked)
                    mFromType = "Farenheit";
                break;
            case R.id.kelvin_frombutton:
                if (checked)
                    mFromType = "Kelvin";
                break;
            case R.id.rankin_frombutton:
                if (checked)
                    mFromType = "Rankin";
                break;
            default:
                Toast.makeText(this, "You must select a unit to convert from", Toast.LENGTH_SHORT).show();
        }
    }

    public void toSelection(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.celsius_tobutton:
                if (checked)
                    mToType = "ToCelsius";
                break;
            case R.id.farenheit_tobutton:
                if (checked)
                    mToType = "ToFarenheit";
                break;
            case R.id.kelvin_tobutton:
                if (checked)
                    mToType = "ToKelvin";
                break;
            case R.id.rankin_tobutton:
                if (checked)
                    mToType = "ToRankin";
                break;
            default:
                Toast.makeText(this, "You must select a unit to convert to", Toast.LENGTH_SHORT).show();
        }
    }

    public void PrintResult() {
        String fromType = "";
        String toType = "";
        switch (mFromType) {
            case "Celsius":
                fromType = "°C";
                break;
            case "Farenheit":
                fromType = "°F";
                break;
            case "Kelvin":
                fromType = "K";
                break;
            case "Rankin":
                fromType = "°R";
                break;
        }
        switch (mToType) {
            case "ToCelsius":
                toType = "°C";
                break;
            case "ToFarenheit":
                toType = "°F";
                break;
            case "ToKelvin":
                toType = "K";
                break;
            case "ToRankin":
                toType = "°R";
                break;
        }
        result.setText((Double.toString(mUserInput)) + fromType + " = " + mConverted + toType);
    }

    private void PrintFormula() {
        String mformula = "";
        switch (mFromType) {
            case "Celsius":
                switch (mToType) {
                    case "ToCelsius":
                        mformula = "°C = °C";
                        break;
                    case "ToFarenheit":
                        mformula = "(°C * 9/5) + 32";
                        break;
                    case "ToKelvin":
                        mformula = "°C + 273.15";
                        break;
                    case "ToRankin":
                        mformula = "(°C + 273.15) × 9/5";
                        break;
                }
                break;
            case "Farenheit":
                switch (mToType) {
                    case "ToCelsius":
                        mformula = "(°F - 32) * 5/9";
                        break;
                    case "ToFarenheit":
                        mformula = "°F = °F";
                        break;
                    case "ToKelvin":
                        mformula = "(°F - 32) * 5/9 + 273.15";
                        break;
                    case "ToRankin":
                        mformula = "°F + 459.67";
                        break;
                }
                break;
            case "Kelvin":
                switch (mToType) {
                    case "ToCelsius":
                        mformula = "K - 273.15";
                        break;
                    case "ToFarenheit":
                        mformula = "(K - 273.15) * 9/5 + 32";
                        break;
                    case "ToKelvin":
                        mformula = "K = K";
                        break;
                    case "ToRankin":
                        mformula = "K × 9/5";
                        break;
                }
                break;
            case "Rankin":
                switch (mToType) {
                    case "ToCelsius":
                        mformula = "(°R − 491.67) × 5/9";
                        break;
                    case "ToFarenheit":
                        mformula = "°R − 459.67";
                        break;
                    case "ToKelvin":
                        mformula = "°R × 5/9";
                        break;
                    case "ToRankin":
                        mformula = "°R = °R";
                        break;
                }
                break;
        }
        formula.setText(mformula);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temperature_converter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}