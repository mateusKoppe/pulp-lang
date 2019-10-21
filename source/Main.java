import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import values.Scope;
import services.Lexer;

class Main {
    public static void main (String args[]) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(args[0]))) {
            Scope scope = new Scope();
            Lexer lexer = new Lexer(br, scope);
            lexer.run();
        } catch (IOException e) {
            System.err.println("Not found file " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Some error here");
            System.err.println(e.getMessage());
        }
    }
}