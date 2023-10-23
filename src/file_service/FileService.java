package file_service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static final String COMMA_DELIMITER =",";
    public static final String SAVE_FILE = "files/ARRAY_SAVE.TXT";
    public static final String BUBBLE_SORT_OUTPUT ="files/OUTPUT1.TXT";
    public static final String INSERTION_SORT_OUTPUT ="files/OUTPUT2.TXT";
    public static final String SELECTION_SORT_OUTPUT ="files/OUTPUT3.TXT";

    public static int[] readFile(String fileName) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            List<Integer> numbList = new ArrayList<Integer>();
            String line = buffer.readLine();
            if(line!= null) {
                String[] elements = line.split(COMMA_DELIMITER);
                for(String st: elements) {
                    numbList.add(Integer.valueOf(st.trim()));
                }
                return numbList.stream().mapToInt(Integer::intValue).toArray();
            }

        } catch (IOException e) {
            System.out.println("Error when reading file or file does not exist");
        } catch (NumberFormatException e) {
            System.out.println("Datas are invalid!!!");
        }
        return null;
    }



    public static void writeFile(String fileName, String content) {
        tryCreateSaveFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFile2(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder str = new StringBuilder();
            String line;
            while((line = reader.readLine())!=null){
                str.append(line);
                str.append("%n");
            }
            return str.toString();
        } catch (IOException e) {
            System.out.println("Error when reading file or file does not exist");
        } catch (NumberFormatException e) {
            System.out.println("Datas are invalid!!!");
        }
        return null;
    }

    public static void writeFile2(String filenPath, String content) {
        tryCreateSaveFile(filenPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenPath))) {
            String[] lines = content.split("%n");
            for(String line: lines){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearDatas(String fileName) {
        tryCreateSaveFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static boolean tryCreateSaveFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isFileExisted(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }
//        System.out.println("ERROR: File not Found");
        return false;
    }

    public static boolean isFileEmpty(String fileName) {
        if(isFileExisted(fileName)){
            try(BufferedReader buffer = new BufferedReader(new FileReader(fileName))){
                if(buffer.readLine() == null) {
                    System.out.println("The file is empty");
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }




}
