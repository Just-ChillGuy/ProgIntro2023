package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        Parser parser = new Parser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            StringBuilder paragraph = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty() && !paragraph.isEmpty()) {
                    result.append(parser.parser(paragraph));
                    paragraph.delete(0, paragraph.length());
                } else if (!line.isEmpty()) {

                    paragraph.append(line).append("\n");
                }
                line = reader.readLine();
            }
            result.append(parser.parser(paragraph));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            writer.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}