import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Wspp {
    public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        Map<String, ArrayList<Integer>> StringandIndex = new LinkedHashMap<>();
        int cnt = 0;
        String trans = "";
        AuScanner scanString = null;
        try {
            scanString = new AuScanner(new FileInputStream(input));
                while (scanString.hasNextLine()) {
                    trans = scanString.nextStr().toLowerCase();
                    if (!trans.isEmpty()) {
                        cnt++;
                        if (StringandIndex.containsKey(trans)) {
                            StringandIndex.get(trans).add(cnt);
                        } else {
                            StringandIndex.put(trans, new ArrayList<Integer>(List.of(cnt)));
                        }
                    }
                }
        } catch (IOException fError) {
            System.err.println("Error of opening file: " + fError.getMessage());
        } finally {
            scanString.readerclose();
        }
        try {
            BufferedWriter fOutput = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
            try {
                Set<String> keys = StringandIndex.keySet();
                for (String k: keys){
                    fOutput.write(k + " " + StringandIndex.get(k).size());
                    for (int i = 0; i < StringandIndex.get(k).size(); i++){
                        fOutput.write(" " + Integer.toString(StringandIndex.get(k).get(i)));
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
