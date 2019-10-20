import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import values.Scope;

class Main {
    public static void main (String args[]) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(args[0]))) {
            Scope scope = new Scope();
            Lexer lexer = new Lexer(br, scope);
            lexer.run();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}