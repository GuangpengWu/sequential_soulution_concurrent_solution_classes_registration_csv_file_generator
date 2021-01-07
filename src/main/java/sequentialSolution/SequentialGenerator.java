package sequentialSolution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SequentialGenerator {

  private static final String SAMPLE_PATH = "/sampleStudentVle.csv";
  private static final String REAL_PATH = "/studentVle.csv";

  public static void main(String[] args) throws IOException {
    // Convert String Array to List
    List<String> list = Arrays.asList(args);
    // If input directory is not given
    if (args.length < 1){
      System.err.println(errorMessage());
      System.err.println(usageMessage());
      System.exit(1);
    }

    // After checking the validation of directory, the project will go into SequentialProcessor to do further steps.
    try{
      String sourcePath = MyMatcher.validSource(list.get(0));
      SequentialProcessor.run(sourcePath, "./output");
    } catch (InvalidFileException | IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static String errorMessage(){
    return "Error: Missing Source Directory";
  }

  private static String usageMessage(){
    return "Usage:\n" + "\t" + "Source directory: <path> contains all source csv files.";
  }
}
