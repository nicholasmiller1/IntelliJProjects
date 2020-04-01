import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Map<String, Integer> dictionary;

    public static void main(String[] args) {

        lzwFromFile();
//        uncompressLZWFromFile();
    }

    private static void lzwFromFile() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("./text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder stringInput = new StringBuilder();

        while (scan.hasNext()) {
            stringInput.append(scan.next()).append(" ");
        }

        stringInput = new StringBuilder(stringInput.substring(0, stringInput.length() - 1));

        int bit = 8;
        String stringOutput = lsw(stringInput.toString(), bit);
        double currentCompression = findCompressionRatio(stringOutput, stringInput.toString());
        String nextStringOutput = lsw(stringInput.toString(), bit + 1);
        double nextCompression = findCompressionRatio(nextStringOutput, stringInput.toString());
        System.out.println(currentCompression + ":1" + "\n" + nextCompression + ":1");

        while (currentCompression < nextCompression) {
            stringOutput = nextStringOutput;
            currentCompression = nextCompression;

            bit++;
            nextStringOutput = lsw(stringInput.toString(), bit + 1);
            nextCompression = findCompressionRatio(nextStringOutput, stringInput.toString());
            System.out.println(nextCompression + ":1");
        }

        System.out.println("Final Ratio: " + currentCompression + ":1");

        File outputFile = new File("./text_compressed.txt");
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            FileWriter outputWriter = new FileWriter(outputFile);
            outputWriter.write(bit + "\n" + stringOutput);
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String lsw(String input, int bits) {
        dictionary = new TreeMap<>();

        String current, next = "";
        int output;
        StringBuilder result = new StringBuilder();
        int currentValue = 128;

        for ( int i = 0; i < input.length(); i++ ) {
            current = String.valueOf(input.charAt(i));

            if (i < input.length() - 1) {
                next = String.valueOf(input.charAt(i + 1));
            }

            output = (int) current.charAt(0);

            while (i < input.length() - 1 && dictionary.containsKey(current + next)) {
                i++;
                current += next;
                if (i < input.length() - 1) {
                    next = String.valueOf(input.charAt(i + 1));
                }
                output = dictionary.get(current);
            }

            if (dictionary.size() < (Math.pow(2, bits) - 128)) {
                dictionary.put(current + next, currentValue);
                currentValue++;
            }

            StringBuilder str = new StringBuilder(Integer.toBinaryString(output));
            while (str.length() < bits) {
                str.insert(0, "0");
            }

            result.append(str);
        }

        return result.toString();
    }

    private static double findCompressionRatio(String output, String input) {
        double inputLength = input.length() * 7;
        double outputLength = output.length();

        return Math.floor((inputLength / outputLength) * 100) / 100;
    }

    private static void uncompressLZWFromFile() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("./text_compressed.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int bitSize = scan.nextInt();
        scan.nextLine();
        String stringInput = scan.nextLine();

        String stringOutput = uncompressLZW(stringInput, bitSize);

        File outputFile = new File("./text_uncompressed.txt");
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            FileWriter outputWriter = new FileWriter(outputFile);
            outputWriter.write(stringOutput);
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String uncompressLZW(String input, int bits) {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < input.length(); i+=bits) {
            values.add(Integer.parseInt(input.substring(i, i+bits), 2));
        }

        Map<Integer, String> reverseDictionary = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            reverseDictionary.put(i, (char)i + "");
        }

        String current = (char)(int)values.remove(0) + "";
        StringBuilder result = new StringBuilder(current);

        for (int i : values) {
            String next = null;
            if (reverseDictionary.containsKey(i)) {
                next = reverseDictionary.get(i);
            }
            else if (i == reverseDictionary.size()) {
                next = current + current.charAt(0);
            }

            result.append(next);

            if (next != null) {
                reverseDictionary.put(reverseDictionary.size(), current + next.charAt(0));
            }

            current = next;
        }

        return result.toString();
    }
}
