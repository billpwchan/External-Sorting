package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExternalSorting {

    /**
     * @param path       the file to read
     * @param offset     the offset to start reading from
     * @param memory_cap the number of lines to read (Simulate Hard-limit Memory Capacity)
     * @return the lines of the file
     */
    public static List<String> readFileIntoList(Path path, int offset, int memory_cap) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.newBufferedReader(path, StandardCharsets.UTF_8).lines().skip((long) offset * memory_cap).limit(memory_cap).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * @param output_path  the path to write output file data/output.txt
     * @param memory_cap   the number of lines to read (Simulate Hard-limit Memory Capacity)
     * @param num_ways     the number of ways to split the data
     * @param total_length the total length of the data
     * @throws IOException if the file cannot be written
     */
    public static void mergeFiles(Path output_path, int memory_cap, int num_ways, int total_length) throws IOException {
        // Create Output File if not exist, or empty the content
        if (!Files.exists(output_path, LinkOption.NOFOLLOW_LINKS)) Files.createFile(output_path);
        else {
            FileChannel.open(output_path, StandardOpenOption.WRITE).truncate(0).close();
        }

        // num_ways portions for input sorted files + 1 portion for output file
        int portion_size = memory_cap / (num_ways + 1);

        int[] top_nums = new int[num_ways];
        BufferedReader[] br_arr = new BufferedReader[num_ways];

        for (int i = 0; i < num_ways; i++) {
            try {
                br_arr[i] = new BufferedReader(new FileReader(String.format("data/output_%d.txt", i + 1)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MinHeapNode[] heap_arr = new MinHeapNode[num_ways];
        for (int i = 0; i < num_ways; i++) {
            MinHeapNode node = new MinHeapNode(Integer.parseInt(br_arr[i].readLine()), i, 1);
            heap_arr[i] = node;
        }

        // MinHeap Initialization
        MinHeap mh = new MinHeap(heap_arr, num_ways);

        int[] result = new int[portion_size];
        Arrays.fill(result, Integer.MAX_VALUE);
        int partition_index = 0;

        for (int i = 0; i < total_length; i++) {

            // Extract the top element
            MinHeapNode root = mh.getMin();
            result[partition_index++] = root.element;

            // Add next element of this array to min heap by reading from the BufferedReader
            String line = br_arr[root.i].readLine();
            root.element = line != null ? Integer.parseInt(line) : Integer.MAX_VALUE;

            mh.replaceMin(root);

            if (partition_index == portion_size) {
                partition_index = 0;
                Files.write(output_path, Arrays.stream(result).filter(num -> num != Integer.MAX_VALUE).mapToObj(String::valueOf).collect(Collectors.toList()), StandardOpenOption.APPEND);
                result = new int[portion_size];
                Arrays.fill(result, Integer.MAX_VALUE);
            }
        }
        Files.write(output_path, Arrays.stream(result).filter(num -> num != Integer.MAX_VALUE).mapToObj(String::valueOf).collect(Collectors.toList()), StandardOpenOption.APPEND);

        // close br_arr
        for (BufferedReader br : br_arr) {
            br.close();
        }
        System.out.println("Merge Done! File saved to: " + output_path.toString());
    }

    /**
     * @param path the path to read data/
     */
    public static void removeTempFiles(Path path) {
        // Remove Temporary Output Files
        final File downloadDirectory = new File(path.toString());
        final File[] files = downloadDirectory.listFiles((dir, name) -> name.matches("output_\\d+.txt"));
        assert files != null;
        for (File file : files) {
            file.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        // Create Data Folders for storing Input & Output
        Files.createDirectories(Paths.get("data"));


        // Initialize variables for simulating external sorting scenario
        Properties prop = new Properties();
        prop.load(ExternalSorting.class.getClassLoader().getResourceAsStream("config.properties"));
        int total_lines = Integer.parseInt(Objects.requireNonNull(prop.getProperty("TOTAL_LINES")));
        int memory_cap = Integer.parseInt(Objects.requireNonNull(prop.getProperty("MEMORY_CAP")));

        if (args.length == 2) {
            total_lines = Integer.parseInt(args[0]);
            memory_cap = Integer.parseInt(args[1]);
            assert memory_cap > 0 && total_lines >= memory_cap;
        }


        // If total_lines >= memory_cap, use external sorting. Else we can just use MergeSort to sort the input file
        int num_ways = total_lines >= memory_cap ? Math.floorDiv(total_lines, memory_cap) : 1;

        System.out.println("Total Lines: " + total_lines);
        System.out.println("Memory capacity: " + memory_cap);
        System.out.println("Number of ways: " + num_ways);

        // Generate random numbers with quantity num_ways x memory_cap in Reverse Order
        try (Formatter formatter = new Formatter(Files.newBufferedWriter(Paths.get("data", "input.txt")))) {
            IntStream.range(0, total_lines).boxed().sorted(Collections.reverseOrder()).forEach(i -> formatter.format("%d%n", i + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int offset = 0; offset < num_ways; offset++) {
            // Read file into list of integers
            List<Integer> lines = readFileIntoList(Paths.get("data", "input.txt"), offset, memory_cap).stream().map(Integer::parseInt).collect(Collectors.toList());

            // Sort the list of integers
            int[] inputArray = lines.stream().mapToInt(Integer::intValue).toArray();
            MergeSort.mergeSort(inputArray);

            // Write sorted list to file
            try (Formatter formatter = new Formatter(Files.newBufferedWriter(Paths.get("data", String.format("output_%d.txt", offset + 1))))) {
                IntStream.range(0, inputArray.length).forEach(i -> formatter.format("%d%n", inputArray[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }

        mergeFiles(Paths.get("data", "output.txt"), memory_cap, num_ways, total_lines);
        removeTempFiles(Paths.get("data"));

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total Time: " + totalTime / 1000000000.0 + " seconds");
    }
}
