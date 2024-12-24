import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WsppPosition {
    public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        Map<String, ArrayList<Integer>> StringandIndex = new LinkedHashMap<>();
        char cnt = 0;
        int cnt_String = 1;
        String trans = "";
        boolean flag;
        String trans1 = "";
        AuScanner scanString = null;
        ArrayList<String> words = new ArrayList<>();
        try {
            scanString = new AuScanner(new FileInputStream(input));
            try {
                while (scanString.hasNextLine()) {
                    trans = scanString.nextStr_plus(cnt).toLowerCase();
                    cnt = 1;
                    if (!trans.isEmpty()) {
                        if (trans.charAt(0) != ' ') {
                            words.add(trans);
                        } else {
                            String[] arrayStr;
                            arrayStr = words.toArray(new String[words.size()]);
                            for (int i = 0; i < arrayStr.length; i++) {
                                trans1 = arrayStr[i];
                                if (StringandIndex.containsKey(trans1)) {
                                    StringandIndex.get(trans1).add(cnt_String);
                                    StringandIndex.get(trans1).add(arrayStr.length - i);
                                } else {
                                    StringandIndex.put(trans1, new ArrayList<Integer>(List.of(cnt_String)));
                                    StringandIndex.get(trans1).add(arrayStr.length - i);
                                }
                            }
                            cnt_String++;
                            words = new ArrayList<>();
                            trans = trans.substring(1, trans.length());
                            words.add(trans);
                        }
                    }
                }
                String[] arrayStr;
                arrayStr = words.toArray(new String[words.size()]);
                for (int i = 0; i < arrayStr.length; i++) {
                    trans1 = arrayStr[i];
                    if (StringandIndex.containsKey(trans1)) {
                        StringandIndex.get(trans1).add(cnt_String);
                        StringandIndex.get(trans1).add(arrayStr.length - i);
                    } else {
                        StringandIndex.put(trans1, new ArrayList<Integer>(List.of(cnt_String)));
                        StringandIndex.get(trans1).add(arrayStr.length - i);
                    }
                }
            } finally {
            scanString.readerclose();
            }
        } catch (IOException fError) {
            System.err.println("Error of opening file: " + fError.getMessage());
        }
        try {
            BufferedWriter fOutput = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
            try {
                Set<String> keys = StringandIndex.keySet();
                for (String k: keys){
                    fOutput.write(k + " " + StringandIndex.get(k).size() / 2);
                    for (int i = 0; i < StringandIndex.get(k).size(); i++){
                        if (i % 2 == 0) {
                            fOutput.write(" " + Integer.toString(StringandIndex.get(k).get(i)));
                        } else {
                            fOutput.write(':' + Integer.toString(StringandIndex.get(k).get(i)));
                        }
                    }
                    fOutput.newLine();
                }
            } catch (IOException writerErr){
                System.err.println("Output error: " + writerErr.getMessage());
            } finally {
                fOutput.close();
            }
        } catch (IOException writerErr){
            System.err.println("Input error: " + writerErr.getMessage());
        }
    }
}
