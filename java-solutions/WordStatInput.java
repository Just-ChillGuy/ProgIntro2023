import java.util.*;
import java.io.*;

public class WordStatInput {
	public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        Map<String, Integer> StringandIndex = new LinkedHashMap<>();
        String trans;
        AuScanner scanString = null;
        AuScanner scanStr = null;
        try {
            scanString = new AuScanner(new FileInputStream(args[0]));
            while (scanString.hasNextLine()) {
                scanStr = new AuScanner(scanString.nextLine());
                while (scanStr.hasNextLine()) {
                    trans = scanStr.nextStr().toLowerCase();
                    if (trans.length() != 0) {
                        if (StringandIndex.containsKey(trans)) {
                            StringandIndex.put(trans, StringandIndex.get(trans) + 1);
                        } else {
                            StringandIndex.put(trans, 1);
                        }
                    }
                }
            }

        } catch (IOException fError) {
            System.err.println("Error of opening file: " + fError.getMessage());
        } finally {
            scanString.readerclose();
            scanStr.readerclose();
        }
        /*	try {
            AuScanner fReader = new AuScanner(new InputStream(new FileInputStream(args[0])));
			try {
				while (true) {
					int ch = fReader.fromBuffer();
					if (ch == -1){
						break;
					}
					StringBuilder mystring = new StringBuilder();
					while ((Character.isLetter(ch)) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == 39) {
						mystring.append((char) ch);
						ch = fReader.fromBuffer();
					}
					trans = mystring.toString().toLowerCase();
					if (trans.length() != 0){
						if (StringandIndex.containsKey(trans)){
						StringandIndex.put(trans,StringandIndex.get(trans) + 1);
                        } else {
                            StringandIndex.put(trans, 1);
                        }
					}		
				}
            } catch (IOException parsingErr) {
                System.err.println("Parsing error: " + parsingErr.getMessage());
            } finally {
//                fReader.close();
            }
        } catch (IOException fError) {
            System.err.println("Error of opening file: " + fError.getMessage());
        } */
        try {
            BufferedWriter fOutput = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                Set<String> keys = StringandIndex.keySet();
                for (String k : keys) {
                    fOutput.write(k + " " + StringandIndex.get(k) + "\n");
                }
            } catch (IOException writerErr) {
                System.err.println("Output error: " + writerErr.getMessage());
            } finally {
                fOutput.close();
            }
        } catch (IOException writerErr) {
            System.err.println("Input error: " + writerErr.getMessage());
        }
    }
}
