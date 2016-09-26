package edu.kvcc.cis298.cis298assignment2;

import java.util.FormatFlagsConversionMismatchException;

/**
 * Created by dhatt_000 on 9/26/2016.
 */

public class ConversionMath {
    private double mUserInput;
    private String mFromType;
    private String mToType;
    private double mConverted;

    public String getToType() {
        return mToType;
    }

    public void setToType(String toType) {
        mToType = toType;
    }

    public String getFromType() {
        return mFromType;
    }

    public void setFromType(String fromType) {
        mFromType = fromType;
    }

    public double getUserInput() {
        return mUserInput;
    }

    public void setUserInput(double userInput) {
        mUserInput = userInput;
    }

    public ConversionMath(String fromType, String toType, double userInput) {
        mUserInput = userInput;
        mFromType = fromType;
        mToType = toType;
    }

    public ConversionMath() {
        //default constructor
    }

    public double CalculateConversion(String fromType, String toType, double userInput) {
        mUserInput = userInput;
        mFromType = fromType;
        mToType = toType;

        switch (mFromType){
            case "Celsius":
                switch (mToType){
                    case "Celsius":
                        mConverted = mUserInput;
                        break;
                    case "Farenheit":
                        mConverted = mUserInput * 1.8 + 32;
                        break;
                    case "Kelvin":
                        mConverted = mUserInput + 273.15;
                        break;
                    case "Rankin":
                        mConverted = (mUserInput + 273.15) * 9/5;
                        break;
                break;
            case "Farenheit":
                switch (mToType){
                    case "Celsius":
                        mConverted = (mUserInput - 32) * 5/9;
                        break;
                    case "Farenheit":
                        mConverted = mUserInput;
                        break;
                    case "Kelvin":
                        mConverted = (mUserInput  + 459.67) * 5/9;
                        break;
                    case "Rankin":
                        mConverted = mUserInput + 459.67;
                        break;
                break;
            case "Kelvin":
                switch (mToType){
                    case "Celsius":
                        mConverted = mUserInput - 273.15;
                        break;
                    case "Farenheit":
                        mConverted = mUserInput * 9/5 - 459.67;
                        break;
                    case "Kelvin":
                        mConverted = mUserInput;
                        break;
                    case "Rankin":
                        mConverted = mUserInput * 9/5;
                        break;
                break;
            case "Rankin":
                switch (mToType){
                    case "Celsius":
                        mConverted = (mUserInput - 491.67) * 5/9;
                        break;
                    case "Farenheit":
                        mConverted = mUserInput - 459.67;
                        break;
                    case "Kelvin":
                        mConverted = mUserInput * 5/9;
                        break;
                    case "Rankin":
                        mConverted = mUserInput;
                        break;
                    break;
        return mConverted;
    }
}
