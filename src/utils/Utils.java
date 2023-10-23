package utils;

import java.util.Arrays;

public class Utils {
    public static String formatArraySave(int[] arr){
        String strArr = Arrays.toString(arr);
        return strArr.substring(1, strArr.length()-1);
    }

    public static String repeatCharacter(char character, int length) {
        StringBuilder myString = new StringBuilder();
        for(int i = 0; i<length; i++) {
            myString.append(character);
        }
        return myString.toString();
    }

    public static String arrayFormatedString(int[] arr) {
        StringBuilder index = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (int i =0; i< arr.length; i++) {
            int a = String.valueOf(arr[i]).length();
            int b = String.valueOf(i).length();
            int len = (a>b)?a:b;
            index.append(String.format("| %"+len+"d ", i));
            values.append(String.format("| %"+len+"d ", arr[i]));
        }
        index.append("|%n");
        values.append("|%n");
        String line = repeatCharacter('-', index.length()-2)+"%n";

        String result =line + index.toString()+line+values.toString()+line;
        return result;
    }


    public static void showStep(int[] arr, int step) {
        System.out.println("step "+step+": ");
        System.out.printf(Utils.arrayFormatedString(arr));
    }

    public static String showStep2(int[] arr, int step) {
        StringBuilder str = new StringBuilder();
        str.append("Step "+step+":%n");
        str.append(arrayFormatedString(arr));
        return str.toString();
    }



}
