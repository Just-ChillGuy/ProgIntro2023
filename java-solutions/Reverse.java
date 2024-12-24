/*
import java.util.Scanner;
import java.util.Arrays;

public class src.Reverse {
    public static void main(String[] args) {
        int trans = 0;
        int stringNum = 0;
        int[][] array = new int[1][];
        Scanner scanString = new Scanner(System.in);   
        while (scanString.hasNextLine()){
            Scanner scanInt = new Scanner(scanString.nextLine());   
            array[stringNum] = new int[16];
            while (scanInt.hasNextLine()){
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
        for (int i = stringNum - 1; i >= 0; i--){
            for (int y = array[i].length - 1; y >= 0; y--){
                System.out.print(array[i][y] + " ");
            }
            System.out.println();
        }
    }   
}
 */
import java.util.Arrays;
public class Reverse {
    public static void main(String[] args) {
        int trans = 0;
        int stringNum = 0;
        int[][] array = new int[1][];
        AuScanner scanString = new AuScanner(System.in);
        while (scanString.hasNextLine()){
            AuScanner scanInt = new AuScanner(scanString.nextLine());
            array[stringNum] = new int[16];
            while (scanInt.hasNextLine()){
                if (array[stringNum].length == trans){
                    array[stringNum] = Arrays.copyOf(array[stringNum], array[stringNum].length * 2); // * 2
                }
                array[stringNum][trans] = scanInt.nextInt();
                trans ++;
            }
            array[stringNum] = Arrays.copyOf(array[stringNum], trans);
            if ((array.length - 1) == stringNum) {
                array = Arrays.copyOf(array, array.length * 2);  // * 2
            }
            stringNum ++;
            trans = 0;
        }
        array = Arrays.copyOf(array, stringNum);
        for (int i = stringNum - 1; i >= 0; i--){
            for (int y = array[i].length - 1; y >= 0; y--){
                System.out.print(array[i][y] + " ");
            }
            System.out.println();
        }
    }
}
