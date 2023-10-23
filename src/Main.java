import java.util.InputMismatchException;
import java.util.Scanner;

import alrigothms.Searching;
import file_service.FileService;
import utils.Utils;

import javax.swing.*;

public class Main {
    // file path: files/INPUT.TXT

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isFresh = true;

    public static void main(String[] args) {
        mainProgram();
    }

    public static void mainProgram() {
        FileService.clearDatas(FileService.SAVE_FILE);
        while(true) {
            menuTable();
            int functionChoose = validFunctionInput(8);
            switch (functionChoose) {
                case 0:
                    System.out.println("Thanks for using the program!!!");
                    return;
                case 1:
                    manualInput();
                    break;
                case 2:
                    fileInput();
                    break;
                case 3:
                    if(isFresh) {
                        System.out.println("Current array is unavailable. Please enter an array");
                    } else {
                        showCurrentArray();
                    }
                    break;
                case 4:
                    if(isFresh) {
                        System.out.println("Current array is unavailable. Please enter an array");
                    } else {
                        bubbleSort();
                    }
                    break;
                case 5:
                    if(isFresh) {
                        System.out.println("Current array is unavailable. Please enter an array");
                    } else {
                        selectionSort();
                    }
                    break;
                case 6:
                    if(isFresh) {
                        System.out.println("Current array is unavailable. Please enter an array");
                    } else {
                        insertionSort();
                    }
                    break;
                case 7:
                    if(isFresh) {
                        System.out.println("Current array is unavailable. Please enter an array");
                    } else {
                        linearSearch();
                    }
                    break;
                case 8:
                    if(isFresh) {
                        System.out.println("Current array is unavailable. Please enter an array");
                    } else {
                        binarySearch();
                    }
                    break;
            }
        }
    }

    public static void menuTable() {

        final String ROWLINE = "+--------+-------------+--------+%n";
        int ROWLINELength = ROWLINE.length();
        String placeHolderStr = "| %-"+ (ROWLINELength-6) +"s |%n";
        System.out.format(ROWLINE);
        System.out.format(placeHolderStr, "MENU");
        System.out.format(ROWLINE);
        System.out.format(placeHolderStr, "1. Manual input");
        System.out.format(placeHolderStr, "2. File input");
        System.out.format(placeHolderStr, "3. Show current array");
        System.out.format(placeHolderStr, "4. Bubble sort");
        System.out.format(placeHolderStr, "5. Selection sort");
        System.out.format(placeHolderStr, "6. Insertion sort");
        System.out.format(placeHolderStr, "7. Search > value");
        System.out.format(placeHolderStr, "8. Search = value");
        System.out.format(placeHolderStr, "0. Exit");
        System.out.format(ROWLINE);
    }

    public static int validFunctionInput(int funcNumbers) {
        while(true) {
//            nếu chức năng không hợp lệ thì yêu cầu nhập lại
            try {
                System.out.print("Select function: ");
                String input = scanner.nextLine();
                int intInput = Integer.parseInt(input);
                if((intInput>(funcNumbers)) || (intInput<0)) {
//                    nếu không nằm trong phạm vi chức năng thì quăng lỗi
                    throw new Exception();
                }
                return intInput;
            }
            catch(Exception e) {
                System.out.println("Selected function is not valid");
                System.out.println("Please enter again");
            }
        }
    }

    public static void manualInput() {
        if(enterNewArrCheck() || isFresh){
            while(true) {
                try {
                    System.out.print("Enter the array length: ");
                    String intput = scanner.nextLine();
                    int len = Integer.parseInt(intput);
                    if(len<=0 || len>20) {
                        throw new NumberFormatException();
                    }
                    boolean isValidinput = false;
                    while(!isValidinput) {
                        int[] arr = new int[len];
                        System.out.println("Enter elements:");
                        for(int i =0; i< len;i++) {
                            try{
                                arr[i] = scanner.nextInt();
                                isValidinput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Value of elements must be a Integer.");
                                System.out.println("Please enter again.");
                                isValidinput = false;
                                break;
                            }
                        }
                        scanner.nextLine();
                        if(isValidinput){
                            FileService.clearDatas(FileService.SAVE_FILE);
                            FileService.writeFile(FileService.SAVE_FILE, Utils.formatArraySave(arr));
                            Utils.showArray(arr);
                            isFresh = false;
                            return;
                        }

                    }
                } catch (NumberFormatException e) {
                    System.out.println("The array length must be a number between 1 and 20");
                    System.out.println("Please enter again.");
                }
            }
        }

    }

    public static void fileInput() {
        if(enterNewArrCheck() || isFresh) {
            while(true) {
                System.out.print("Enter file path: ");
                String path = scanner.nextLine();
                if(FileService.isFileExisted(path)){
                    int[] arr = FileService.readFile(path);
                    if(arr != null) {
                        FileService.clearDatas(FileService.SAVE_FILE);
                        FileService.writeFile(FileService.SAVE_FILE, Utils.formatArraySave(arr));
                        Utils.showArray(arr);
                        isFresh = false;
                        return;
                    } else {
                        System.out.println("Datas are invalid");
                        System.out.println("Please enter again");
                    }
                } else {
                    System.out.println("File not found. Please enter again");
                    System.out.println("please enter again");
                }
            }
        }
    }

    public static void showCurrentArray(){
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        Utils.showArray(arr);
    }

    public static void bubbleSort() {
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        alrigothms.Sorting.bubleSort(arr);
    }

    public static void selectionSort(){
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        alrigothms.Sorting.selectionSort(arr);
    }

    public static void insertionSort(){
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        alrigothms.Sorting.insertSort(arr);
    }

    public static void linearSearch() {
        while(true) {
            System.out.print("Enter searched input value: ");
            String input = scanner.nextLine();
            try{
                int value = Integer.parseInt(input);
                int[] result = Searching.linearSearch(FileService.readFile(FileService.SAVE_FILE), value);
                if(result!=null) {
                    System.out.print("The Larger position: ");
                    for(int i =0; i< result.length; i++) {
                        System.out.print(result[i]+" ");
                    }
                    System.out.println();
                } else {
                    System.out.println("The value does not exist!");
                }
                return;
            } catch (NumberFormatException e) {
                System.out.println("The value must be a number");
            }
        }
    }

    public static void binarySearch() {
        while(true) {
            System.out.print("Enter searched input value: ");
            String input = scanner.nextLine();
            try{
                int value = Integer.parseInt(input);
                int[] arr = FileService.readFile(FileService.SAVE_FILE);
                int result = Searching.binarySearch(FileService.readFile(FileService.SAVE_FILE), 0, arr.length-1, value);
                if(result!=-1) {
                    System.out.print("The right position: " + result);
                } else {
                    System.out.println("The value does not exist!");
                }
                return;
            } catch (NumberFormatException e) {
                System.out.println("The value must be a number");
            }
        }
    }


    public static void setInitial() {
        FileService.clearDatas(FileService.SAVE_FILE);
        FileService.clearDatas(FileService.BUBBLE_SORT_OUTPUT);
        FileService.clearDatas(FileService.SELECTION_SORT_OUTPUT);
        FileService.clearDatas(FileService.INSERTION_SORT_OUTPUT);
    }

    public static boolean enterNewArrCheck() {
        if(isFresh == false) {
            System.out.println("New array will override the current array.");
            System.out.println("Are you sure want to enter new array?(Y/N)");
            String createNew = scanner.nextLine().toLowerCase();
            if(createNew.equals("y") || createNew.equals("yes")) {
                return true;
            }
        }
        return false;
    }
}