package utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String formatArraySave(int[] arr){
        String strArr = "";
        for (int e: arr){
            strArr+=(e+" ");
        }
        return strArr;
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

    public static String arrayFormatedString2(int[][] arr) {
        StringBuilder index = new StringBuilder();
        StringBuilder values = new StringBuilder();
        index.append("Index: ");
        values.append("Value: ");
        for (int i =0; i< arr.length; i++) {
            int a = String.valueOf(arr[i][0]).length();
            int b = String.valueOf(arr[i][1]).length();
            int len = (a>b)?a:b;
            index.append(String.format("| %"+len+"d ", arr[i][0]));
            values.append(String.format("| %"+len+"d ", arr[i][1]));
        }
        index.append("|%n");
        values.append("|%n");
        String line ="       "+ repeatCharacter('-', index.length()-9)+"%n";
        String result =line + index.toString()+line+values.toString()+line;
        return result;
    }


    public static String showStep(int[] arr, int step) {
        StringBuilder str = new StringBuilder();
        str.append("Step "+step+":%n");
        str.append(arrayFormatedString(arr));
        return str.toString();
    }

    public static int[] changeStrToIntArr(String str, String separateRegex){
        List<Integer> list = new ArrayList<Integer>();
        String[] datas = str.split(separateRegex);
        for(String data: datas) {
            list.add(Integer.valueOf(data.trim()));
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static String handleFilePathInput(String path) {
        if(path.contains(String.valueOf('/'))) {
            return path;
        }
        return "files/"+path;
    }




}
