package concurrentSolution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConcurrentGenerator {

    private static final String SAMPLE_PATH="/sampleStudentVle.csv";
    private static final String REAL_PATH="/studentVle.csv";
    private static boolean end = false;

    public static void main (String[] args) {
        // Convert String Array to List
        List<String> list = Arrays.asList(args);

        // if input directory is not given
        if (args.length < 1){
            System.err.println(errorMessage());
            System.err.println(usageMessage());
            System.exit(1);
        }

        int threshold=-1;
        if (args.length == 2) {
            try {
                threshold = Integer.parseInt(list.get(1));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid threshold");
                System.exit(1);
            }
            if (threshold < 0){
                System.out.println("Please enter a valid threshold.");
                System.out.println(usageMessage());
                System.exit(1);
            }
        }

        try {
            String sourcePath = MyMatcher.validSource(list.get(0));
            ConcurrentBuffer<ConcurrentVleContent> buffer1 = new ConcurrentBuffer<>();
            ConcurrentBuffer<ConcurrentCumulate> buffer2 = new ConcurrentBuffer<>();

            ConcurrentLoader loader = new ConcurrentLoader(buffer1, REAL_PATH, sourcePath);
            ConcurrentCalculator cumulate = new ConcurrentCalculator(buffer2, buffer1);

            ConcurrentWriter writer;
            if (threshold == -1){
                writer = new ConcurrentWriter(buffer2, "./output");
            } else {
                writer = new ConcurrentWriter(buffer2, "./output", threshold);
            }

            Thread Producer = new Thread(loader);
            Thread Customer = new Thread(cumulate);
            Thread Writer = new Thread(writer);
            Producer.start();
            Customer.start();
            Customer.join();

            Writer.start();
            Writer.join();

            ConcurrentGenerator.end = true;
        } catch (InvalidFileException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }


    private static String errorMessage(){
        return "Error: Missing Source Directory";
    }

    private static String usageMessage(){
        return "Usage:\n" + "\t" + "Source directory: <path> contains all source csv files."
                + "\t" + "optional threshold: <int> >= 0.";
    }

    /**
     * @return true if concurrent done
     */
    public static boolean endWriting(){
        return ConcurrentGenerator.end;
    }

    // Test Helper

    /**
     * Set to help test reset status
     */
    public static void resetFlags(){
        // reset end
        ConcurrentGenerator.end = false;
        ConcurrentLoader.resetExit();
    }

}
