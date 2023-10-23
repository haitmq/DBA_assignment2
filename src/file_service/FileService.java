package file_service;

import java.io.*;

public class FileService {
    public static final String COMMA_DELIMITER =",";
    public static final String SAVE_FILE = "files/ARRAY_SAVE.TXT";
    public static final String BUBBLE_SORT_OUTPUT ="files/OUTPUT1.TXT";
    public static final String INSERTION_SORT_OUTPUT ="files/OUTPUT2.TXT";
    public static final String SELECTION_SORT_OUTPUT ="files/OUTPUT3.TXT";

    public static String readFile(String fileName, boolean isOneLine) {
        // bien isOneLine kiem doc 1 dong hay doc het
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // bien luu du lieu
            StringBuilder str = new StringBuilder();
            String line;
            int lineCount =0;
            while((line = reader.readLine())!=null){
                str.append(line);
                lineCount++;
                if (isOneLine){
                    break;
                }
                str.append("%n");
            }
            //Neu khong co du lieu thong bao cho nguoi dung
            if(lineCount==0) {
                System.out.println("The file is empty");
                throw new IOException();
            }
            return str.toString();
        } catch (IOException e) {
            System.out.println("Error when reading file or file does not exist");
        } catch (NumberFormatException e) {
            System.out.println("Datas are invalid!!!");
        }
        // Neu loi tra ve null
        return null;
    }

    public static void writeFile(String filenPath, String content) {
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

    // tao file neu file khong ton tai
    private static void tryCreateSaveFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isFileExisted(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }
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
