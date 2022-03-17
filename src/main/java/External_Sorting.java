import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Objects;
import java.util.stream.IntStream;

public class External_Sorting {

    public static void main(String[] args) {
        // Initialize variables for simulating external sorting scenario
        Dotenv dotenv = Dotenv.load();
        int num_ways = Integer.parseInt(Objects.requireNonNull(dotenv.get("NUM_WAYS")));
        int memory_cap = Integer.parseInt(Objects.requireNonNull(dotenv.get("MEMORY_CAP")));

        System.out.println("Number of ways: " + num_ways);
        System.out.println("Memory capacity: " + memory_cap);

        // Generate random numbers
        try (Formatter formatter = new Formatter(Files.newBufferedWriter(Paths.get("input.txt")))) {
            IntStream.range(0, 1000).forEach(i -> formatter.format("%d%n", i + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
