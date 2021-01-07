package sequentialSolution;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SequentialProcessor {
  private static final String COURSE_FILE_NAME = "/courses.csv";
  private static final String VLE_FILE_NAME = "/studentVle.csv";
  private static final String SAMPLE_PATH="/sampleStudentVle.csv";

  public static void run(String inputPath, String outputPath) throws IOException, InvalidFileException {
    // cumulateMap: String Key is Filename created, and SequentialCumulate is its related date and click content map.
    Map<String, SequentialCumulate> cumulateMap;

    SequentialVleLoader vle = new SequentialVleLoader(inputPath, VLE_FILE_NAME);
    SequentialVleFileContainer container = (SequentialVleFileContainer) vle.getContainer();

    container.calculate();
    vle.clearMemory();
    // To ensure that the file read to the end.
    while (!vle.reachEnd()){
      vle.loadFile();
      container.calculate();
      vle.clearMemory();
    }
    cumulateMap = container.getCumulates();

    write(outputPath, cumulateMap);

    SequentialCourseLoader courses = new SequentialCourseLoader(inputPath, COURSE_FILE_NAME);

  }

  /**
   * For each line of data in courses.csv, write one file.
   * @param outputPath the output directory, create if not exist
   * @param cumulates a nested hashmap where key is the output filename and the value contains its date with the
   *                cumulated total clicks
   * @throws IOException if something goes wrong while reading
   */
  private static void write(String outputPath, Map<String, SequentialCumulate> cumulates) throws IOException {
    Path path = Paths.get(outputPath);
    Files.createDirectories(path);
    SequentialCumulate current;
    Map<String, Integer> map;
    // for each file, write content of cumulate
    for (String filename : cumulates.keySet()){
      current = cumulates.get(filename);
      File f = new File(outputPath + "/" + filename + ".csv");
      f.createNewFile();
      PrintWriter writer = new PrintWriter(f);
      writeTitle(writer);
      map = current.getCumulate();
      for (String date: map.keySet()){
        String line = date + "," + map.get(date).toString() + "\n";
        writer.write(line);
      }
      writer.close();
      System.out.println("File " + filename + " written successfully.");
    }
  }


  /**
   * @param writer of new files
   */
  private static void writeTitle(PrintWriter writer){
    String title = "date,total\n";
    writer.write(title);
  }
}
