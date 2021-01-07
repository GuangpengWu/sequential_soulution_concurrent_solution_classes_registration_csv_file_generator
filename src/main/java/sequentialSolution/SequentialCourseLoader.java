package sequentialSolution;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SequentialCourseLoader extends SequentialLoader{
  public SequentialCourseLoader(String pathName, String filename) throws InvalidFileException, IOException {
    super(pathName, filename);
    super.setContainer(new SequentialCourseContainer());
    loadFile();
  }

  /**
   * Load courses.csv content.
   * For each line, it has the same order as title list.
   * Create pairs of title, value to create corresponding sequentialCourseContent
   * Add content to courseContainer.
   * @throws IOException if something is wrong while reading
   */
  public void loadFile() throws IOException {
    String line;
    List<String> split;
    while ((line = super.getFile().readLine()) != null) {
      line = line.substring(1, line.length()-1);
      split = Arrays.asList(line.split(SequentialLoader.getCsvSplit()));
      if (isValidLine(split)){
        add(loadLine(split));
      }
    }
  }

  /**
   * Generate the SequentialCourseContent of current line.
   * @param split split value of one line in  courses.csv
   * @return a SequentialCourseContent represent current line
   */
  private SequentialCourseContent loadLine(List<String> split){
    HashMap<String, String> currentContent = new HashMap<>();
    for (int i=0 ; i < split.size() ; i++){
      currentContent.put(super.getTitles().get(i), split.get(i));
    }
    return new SequentialCourseContent(currentContent);
  }

  /**
   * A valid line should contain the same number of strings as the file tile.
   * @param split split value of one line in  courses.csv
   * @return true if they have the same length
   */
  private boolean isValidLine(List<String> split){
    return split.size() == super.getTitles().size();
  }
}
