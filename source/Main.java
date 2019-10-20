import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main (String args[]) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("../examples/main.pulp"))) {
            Lexer lexer = new Lexer(br);
            lexer.run();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}