import java.nio.charset.StandardCharsets;
import java.io.*;
import java.lang.StringBuilder;
import java.util.InputMismatchException;

public class AuScanner {
    private Reader reader;

    private String lineSep = System.lineSeparator();
    private char[] buffer = new char[1024];
    private char ch1;
    private int readed = -1;
    private int inBuff = 0;

    AuScanner(FileInputStream in) {this.reader = new InputStreamReader(in, StandardCharsets.UTF_8);}
    AuScanner(InputStream in) {
        this.reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    }
    AuScanner(String mystring) {
        this.reader = new StringReader(mystring);
    }

    public String nextLine() {
        char ch = fromBuffer();
        StringBuilder buildString = new StringBuilder();
        while(true) {
            try {
                buildString.append(ch);
                if (checkEndLine(buildString)) {
                    return buildString.substring(0, buildString.length() - lineSep.length());
                }
                ch = fromBuffer();
            } catch (InputMismatchException e) {
                break;
            }
        }
        return buildString.toString();
    }
    public String nextStr() {
        StringBuilder buildString1 = new StringBuilder();
        try {
            char ch = fromBuffer();
            while (true) {
                if (!Character.isWhitespace(ch)) {
                    while ((Character.isLetter(ch))
                            || Character.getType(ch) == Character.DASH_PUNCTUATION
                            || ch == '\'') {
                        buildString1.append(ch);
                        ch = fromBuffer();
                    }
                    break;
                }
                ch = fromBuffer();
            }
        } catch (InputMismatchException e) {
        }
        return buildString1.toString();
    }

    public String nextStr_plus(char cnt) {
        StringBuilder buildString = new StringBuilder();
        StringBuilder buildString1 = new StringBuilder();
        char ch;
        boolean flag = false;
        try {
            if (cnt == 0){
                ch = fromBuffer();
            } else {
                ch = ch1;
            }
            while (true) {
                buildString1.append(ch);
                if (checkEndLine(buildString1)) {
                    flag = true;
                }
                if (Character.isLetter(ch)
                        || Character.getType(ch) == Character.DASH_PUNCTUATION
                        || ch == '\'') {
                    if (flag) {
                        buildString.append(" ");
                    }
                    while (Character.isLetter(ch)
                            || Character.getType(ch) == Character.DASH_PUNCTUATION
                            || ch == '\'') {
                        buildString.append(ch);
                        ch = fromBuffer();
                    }
                    ch1 = ch;
                    break;
                }
                ch = fromBuffer();
            }
        } catch (InputMismatchException e) {
        }
        return buildString.toString();
    }

    public int nextInt() {
        char ch = fromBuffer();
        StringBuilder buildString1 = new StringBuilder();
        boolean flag = true;
        while (true) {
            try {
                while (flag) {
                    if (!Character.isWhitespace(ch)) {
                        flag = false;
                        break;
                    }
                    ch = fromBuffer();
                }
                buildString1.append(ch);
                ch = fromBuffer();
                if (!Character.isDigit(ch)) {
                    break;
                }
            } catch (InputMismatchException e) {
                break;
            }
        }
        return Integer.parseInt(buildString1.toString());
    }


    private boolean checkEndLine(StringBuilder s) {
        if (s.length() >= lineSep.length()) {
            return s.substring(s.length() - lineSep.length(), s.length()).equals(lineSep);
        }
        return false;
    }
    public boolean hasNextLine() {
        if (readed == -1 || inBuff >= readed){
            try {
                inBuff = 0;
                readed = reader.read(buffer);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                readerclose();
            }
        }
        return this.readed > 0;
    }
    public char fromBuffer() {
        if (readed == -1 || inBuff >= readed){
            try {
                inBuff = 0;
                readed = reader.read(buffer);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                readerclose();
            }
        }
        if (readed == -1) {
            throw new InputMismatchException();
        }
        return buffer[inBuff++];
    }
    public void readerclose() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
