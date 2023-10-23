import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import alrigothms.Searching;
import file_service.FileService;
import utils.Utils;

public class Main {
    // file path: files/INPUT.TXT

    private static final Scanner scanner = new Scanner(System.in);
    // Biến isFresh là để kiểm tra xem người dùng đã nhập 1 array nào chưa
    private static boolean isFresh = true;

    public static void main(String[] args) {
        mainProgram();
    }

    // Chương trình chính
    public static void mainProgram() {
        // Khi khởi động chương trình xóa dữ liệu ở file ARRAY_SAVE.TXT
        FileService.clearDatas(FileService.SAVE_FILE);
        while(true) {
            menuTable();
            // Kiểm tra function người dùng chọn hợp lệ
            int functionChoose = validFunctionInput(8);
            // Nếu ban đầu chưa nhập mảng thì hiển thị thông báo
            if(isFresh&&functionChoose>2) {
                System.out.println("Current array is unavailable. Please enter an array");
                continue;
            }
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
                    showCurrentArray();
                    break;
                case 4:
                    bubbleSort();
                    break;
                case 5:
                    selectionSort();
                    break;
                case 6:
                    insertionSort();
                    break;
                case 7:
                    linearSearch();
                    break;
                case 8:
                    binarySearch();
                    break;
            }
        }
    }

    // Menu chức năng
    public static void menuTable() {
        final String ROWLINE = "+--------+-------------+--------+%n";
        int ROWLINELength = ROWLINE.length();
        String placeHolderStr = "| %-"+ (ROWLINELength-6) +"s |%n";
        System.out.format(ROWLINE);
        System.out.format("| %"+ ((ROWLINELength-6)/2) +"s |%n", "MENU");
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

    // Chức năng 1: Nhập mảng thủ công
    public static void manualInput() {
        /*Kiểm tra xem đã tồn tại mảng trong file save chưa
        nếu chưa hoặc nhập lần đầu thì nhập mảng
        nếu không hỏi nguoời dùng có muốn xóa mảng hiện tại để nhập mảng mới không
         */
        if(enterNewArrCheck() || isFresh){
            while(true) {
                try {
                    System.out.print("Enter the array length: ");
                    // kiểm tra dữ liệu người dùng nhập: (độ dài mảng)
                    String intput = scanner.nextLine();
                    int len = Integer.parseInt(intput);
                    if(len<=0 || len>20) {
                        throw new NumberFormatException();
                    }
                    // Kiểm tra dữ liệu người dùng nhập: (giá trị các phần tử của mảng)
                    boolean isValidinput = false; //flag
                    while(!isValidinput) {
                        int[] arr = new int[len];
                        System.out.println("Enter elements:");
                        for(int i =0; i< len;i++) {
                            try{
                                arr[i] = scanner.nextInt();
                                isValidinput = true;
                            } catch (InputMismatchException e) {
                                // bắt lỗi input người dùng và yêu cầu nhập lại
                                System.out.println("Value of elements must be a Integer.");
                                System.out.println("Please enter again.");
                                isValidinput = false;
                                break;
                            }
                        }
                        scanner.nextLine();
                        // Nếu dữ liệu hợp lệ, xóa mảng trước đó, lưu mảng nguời dùng nhập vào file save
                        if(isValidinput){
                            FileService.clearDatas(FileService.SAVE_FILE);
                            FileService.writeFile(FileService.SAVE_FILE, Utils.formatArraySave(arr));
                            System.out.printf(Utils.arrayFormatedString(arr));
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
    // Chức năng 2: nhập mảng thông qua đường dẫn
    public static void fileInput() {
        // Kiểm tra tương tự chức năng 1
        if(enterNewArrCheck() || isFresh) {
            while(true) {
                System.out.print("Enter file path: ");
                String path = scanner.nextLine();
                //Kiểm tra xem file có tồn tại hay không
                if(FileService.isFileExisted(path)){
                    int[] arr = FileService.readFile(path);
                    if(arr != null) {
                        FileService.clearDatas(FileService.SAVE_FILE);
                        FileService.writeFile(FileService.SAVE_FILE, Utils.formatArraySave(arr));
                        System.out.printf(Utils.arrayFormatedString(arr));
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
        System.out.printf(Utils.arrayFormatedString(arr));
    }

    public static void bubbleSort() {
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        alrigothms.Sorting.bubleSort(arr);
        System.out.printf(FileService.readFile2(FileService.BUBBLE_SORT_OUTPUT));
    }

    public static void selectionSort(){
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        alrigothms.Sorting.selectionSort(arr);
        System.out.printf(FileService.readFile2(FileService.SELECTION_SORT_OUTPUT));
    }

    public static void insertionSort(){
        int[] arr = FileService.readFile(FileService.SAVE_FILE);
        alrigothms.Sorting.insertSort(arr);
        System.out.printf(FileService.readFile2(FileService.INSERTION_SORT_OUTPUT));
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