import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class External_Sorting {


    private static void mergeSort(int[] inputArray) {
        int arrayLength = inputArray.length;

        if (arrayLength < 2) {
            return;
        }

        int midPoint = arrayLength / 2;
        int[] leftArray = new int[midPoint];
        int[] rightArray = new int[arrayLength - midPoint];

        for (int i = 0; i < midPoint; i++) {
            leftArray[i] = inputArray[i];
        }
        for (int i = midPoint; i < arrayLength; i++) {
            rightArray[i - midPoint] = inputArray[i];
        }

        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(inputArray, leftArray, rightArray);
    }

    private static void merge(int[] inputArray, int[] leftArray, int[] rightArray) {
        int leftArrayLength = leftArray.length;
        int rightArrayLength = rightArray.length;

        int x = 0;
        int y = 0;
        int z = 0;

        while (x < leftArrayLength && y < rightArrayLength) {
            if (leftArray[x] <= rightArray[y]) {
                inputArray[z] = leftArray[x];
                x++;
            } else {
                inputArray[z] = rightArray[y];
                y++;
            }
            z++;
        }

        while (x < leftArrayLength) {
            inputArray[z] = leftArray[x];
            x++;
            z++;
        }

        while (y < rightArrayLength) {
            inputArray[z] = rightArray[y];
            y++;
            z++;
        }

    }

    /**
     * @param file       the file to read
     * @param offset     the offset to start reading from
     * @param memory_cap the number of lines to read (Simulate Hard-limit Memory Capacity)
     * @return the lines of the file
     */
    public static List<String> readFileIntoList(String file, int offset, int memory_cap) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.newBufferedReader(Paths.get(file), StandardCharsets.UTF_8).lines().skip((long) offset * memory_cap).limit(memory_cap).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    public static void main(String[] args) {
        // Initialize variables for simulating external sorting scenario
        Dotenv dotenv = Dotenv.load();
        int num_ways = Integer.parseInt(Objects.requireNonNull(dotenv.get("NUM_WAYS")));
        int memory_cap = Integer.parseInt(Objects.requireNonNull(dotenv.get("MEMORY_CAP")));

        System.out.println("Number of ways: " + num_ways);
        System.out.println("Memory capacity: " + memory_cap);

        // Generate random numbers with quantity num_ways x memory_cap in Reverse Order
        try (Formatter formatter = new Formatter(Files.newBufferedWriter(Paths.get("input.txt")))) {
            IntStream.range(0, num_ways * memory_cap).boxed().sorted(Collections.reverseOrder()).forEach(i -> formatter.format("%d%n", i + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int offset = 0; offset < num_ways; offset++) {
            // Read file into list of integers
            List<Integer> lines = readFileIntoList("input.txt", offset, memory_cap).stream().map(Integer::parseInt).collect(Collectors.toList());
            System.out.println("Input file: " + lines.size());

            // Sort the list of integers
            int[] inputArray = lines.stream().mapToInt(Integer::intValue).toArray();
            mergeSort(inputArray);

            // Write sorted list to file
            try (Formatter formatter = new Formatter(Files.newBufferedWriter(Paths.get("output.txt")))) {
                IntStream.range(0, inputArray.length).forEach(i -> formatter.format("%d%n", inputArray[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
