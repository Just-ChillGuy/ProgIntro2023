import java.util.Arrays;
import java.io.*;
import java.lang.StringBuilder;

public class WordStatCountPrefixL {
	public static void main(String[] args) {
		String[] arrayWords = new String[1];
		String trans = new String();
		int[] arrayCnt = new int[1];
		boolean flag = true;
		int transInt = 0;
		BufferedReader fReader = null;
		Writer fOutput = null;
		try {
			fReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"));
			try {
				int ch;
				while ((ch = fReader.read()) != -1) {
					StringBuilder mystring = new StringBuilder();
					while ((Character.isLetter(ch)) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'') {
						mystring.append((char) ch);
						ch = fReader.read();
					}
					trans = mystring.toString();
					if (trans.length() > 2){
						trans = trans.substring(0, 3).toLowerCase();
						for (int i = 0; i < transInt; i++){	
							if (arrayWords[i].equals(trans)){
						 		flag = false;
						 		arrayCnt[i] += 1;
						 		break;
						 	}
						}
						if (flag == true) {
							if (arrayCnt.length == transInt){
								arrayWords = Arrays.copyOf(arrayWords, arrayWords.length * 2);
								arrayCnt = Arrays.copyOf(arrayCnt, arrayCnt.length * 2);
							} 						
							arrayWords[transInt] =  trans;
							arrayCnt[transInt] += 1;
							transInt ++;
						}
						flag = true;
					}
				}
				arrayWords = Arrays.copyOf(arrayWords, transInt);
				arrayCnt = Arrays.copyOf(arrayCnt, transInt);
				transInt = 0;
				for (int i = 0; i < arrayCnt.length; i++) {
					for (int y = 0; y < arrayCnt.length - i - 1; y++) {
						if (arrayCnt[y] > arrayCnt[y + 1]) {
							trans = arrayWords[y];
							arrayWords[y] = arrayWords[y + 1];
							arrayWords[y + 1] = trans;
							transInt = arrayCnt[y];
							arrayCnt[y] = arrayCnt[y + 1];
							arrayCnt[y + 1] = transInt;
						}
					}
				}
			} catch (IOException parsingErr) {
				System.err.println("Parsing error: " + parsingErr.getMessage());
			} finally {
				fReader.close();
			}
		} catch (IOException fError) {
			System.err.println("Error of opening file: " + fError.getMessage());
		}
		try {
			fOutput = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
			try {
				for (int i = 0; i < arrayWords.length; i++) {
					if (arrayWords[i].isBlank()) {
						break;
					}
					fOutput.write(arrayWords[i]);
					fOutput.write(" " + arrayCnt[i] + '\n');
				}			
			} catch (IOException writerErr){
				System.err.println("Output error: " + writerErr.getMessage());
			} finally {
				fOutput.close();
			}
		} catch (IOException e) {
			System.err.println("Input error: " + e.getMessage());
		}
	}
}