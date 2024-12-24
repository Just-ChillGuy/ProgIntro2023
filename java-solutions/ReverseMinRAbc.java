import java.util.Arrays;
public class ReverseMinRAbc {
    public static void main(String[] args) {
        int trans = 0;
        int stringNum = 0;
        int[][] array = new int[1][];
        AuScanner scanString = new AuScanner(System.in);
        while (scanString.hasNextLine()) {
            array[stringNum] = new int[16];
            StringBuilder buildString = new StringBuilder();
            String mystring = scanString.nextLine();
            for (int i = 0; i < mystring.length(); i++) {
                buildString.append(toInt(mystring.charAt(i)));
            }
            AuScanner scanInt1 = new AuScanner(buildString.toString());
            while (scanInt1.hasNextLine()) {
                if (array[stringNum].length == trans) {
                    array[stringNum] = Arrays.copyOf(array[stringNum], array[stringNum].length * 2);
                }
                array[stringNum][trans] = scanInt1.nextInt();
                trans++;
            }
            array[stringNum] = Arrays.copyOf(array[stringNum], trans);
            if ((array.length - 1) == stringNum) {
                array = Arrays.copyOf(array, array.length * 2);
            }
            stringNum++;
            trans = 0;
        }
        array = Arrays.copyOf(array, stringNum);
        int minimum = 0;
        for (int i = 0; i < stringNum; i++) {
            for (int y = 0; y < array[i].length; y++) {
                StringBuilder buildString1 = new StringBuilder();
                String mn = "";
                if (y == 0) {
                    minimum = array[i][y];
                    mn = Integer.toString(minimum);
                    for (int z = 0; z < mn.length(); z++) {
                        buildString1.append(toStr(mn.charAt(z)));
                    }
                    System.out.print(buildString1.toString() + " ");
                } else if (minimum > array[i][y]) {
                    minimum = array[i][y];
                    mn = Integer.toString(minimum);
                    for (int z = 0; z < mn.length(); z++) {
                        buildString1.append(toStr(mn.charAt(z)));
                    }
                    System.out.print(buildString1.toString() + " ");
                } else if (minimum <= array[i][y]) {
                    mn = Integer.toString(minimum);
                    for (int z = 0; z < mn.length(); z++) {
                        buildString1.append(toStr(mn.charAt(z)));
                    }
                    System.out.print(buildString1.toString() + " ");
                }
            }
            System.out.println();
        }
    }

    public static char toStr(char chStr) {

        if (!Character.isWhitespace(chStr) && chStr != '-' && chStr != '+') {
            int ascii = (int) chStr;
            ascii += 49;
            chStr = (char) ascii;
            return chStr;
        }
        return chStr;
    }
    public static char toInt(char chInt) {

        if (!Character.isWhitespace(chInt) && chInt != '-' && chInt != '+') {
            int ascii = (int) chInt;
            ascii -= 49;
            chInt = (char) ascii;
            return chInt;
        }
        return chInt;
    }
}
 /*   public static char toStr(char scanInt){
        if (scanInt == '0'){
            return 'a';
        } else if (scanInt == '1') {
            return 'b';
        } else if (scanInt == '2') {
            return 'c';
        } else if (scanInt == '3') {
            return 'd';
        } else if (scanInt == '4') {
            return 'e';
        } else if (scanInt == '5') {
            return 'f';
        } else if (scanInt == '6') {
            return 'g';
        } else if (scanInt == '7') {
            return 'h';
        } else if (scanInt == '8') {
            return 'i';
        } else if (scanInt == '9') {
            return 'j';
        }
        return scanInt;
    }
    public static char toInt(char scanInt){

        if (scanInt == 'a'){
            return '0';
        } else if (scanInt == 'b') {
            return '1';
        } else if (scanInt == 'c') {
            return '2';
        } else if (scanInt == 'd') {
            return '3';
        } else if (scanInt == 'e') {
            return '4';
        } else if (scanInt == 'f') {
            return '5';
        } else if (scanInt == 'g') {
            return '6';
        } else if (scanInt == 'h') {
            return '7';
        } else if (scanInt == 'i') {
            return '8';
        } else if (scanInt == 'j') {
            return '9';
        }
        return scanInt;
    }
}*/
