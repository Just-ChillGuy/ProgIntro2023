import java.util.Scanner;
import java.util.Arrays;

public class ReverseMinR {
    public static void main(String[] args) {
        int trans = 0;
        int stringNum = 0;
        int[][] array = new int[1][];
        Scanner scanString = new Scanner(System.in);
        while (scanString.hasNextLine()){
            Scanner scanInt = new Scanner(scanString.nextLine());
            array[stringNum] = new int[16]; 
            while (scanInt.hasNextInt()){
                if (array[stringNum].length == trans){
                    array[stringNum] = Arrays.copyOf(array[stringNum], array[stringNum].length * 2); 
                } 
                array[stringNum][trans] = scanInt.nextInt();
                trans ++;
            }
            array[stringNum] = Arrays.copyOf(array[stringNum], trans);
            if ((array.length - 1) == stringNum) {
                array = Arrays.copyOf(array, array.length * 2);  
            }
            stringNum ++;
            trans = 0;
        }
        array = Arrays.copyOf(array, stringNum);
        int minimum = 0;
        for (int i = 0; i < stringNum; i++){
            for (int y = 0; y < array[i].length; y++){
                if (y == 0) {
                    minimum = array[i][y];
                    System.out.print(minimum + " ");               
                } else if (minimum > array[i][y]){
                    minimum = array[i][y]; 
                    System.out.print(minimum + " ");

                } else if (minimum <= array[i][y]) {
                    System.out.print(minimum + " ");    
                }
            }
            System.out.println();
        }
    }
}
