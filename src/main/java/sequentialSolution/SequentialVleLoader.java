package sequentialSolution;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SequentialVleLoader extends SequentialLoader{
  // endLine is the start line of a new module_season, which is also means we get to the endLine of last module_season.
  private String endLine;

  public SequentialVleLoader(String pathName, String filename) throws InvalidFileException, IOException {
    super(pathName, filename);
    super.setContainer(new SequentialVleFileContainer());
    loadFile();
  }

  /**
   * Load StudentVle.csv content.
   * For each line, it has the same order as title list.
   * Create pairs of title, value to create corresponding SequentialVleFileContent
   * Add content to SequentialVleFileContainer.
   * @throws IOException if something is wrong while reading
   */
  public void loadFile() throws IOException {
    String module_season;
    String line;
    String current_module_season;
    List<String> split;

    // IF to record the first endLine,  ELSE helps to load file on the first call
    if (this.endLine != null) {
      line = this.endLine;
    } else {
      line = getFile().readLine();
      line = line.substring(1, line.length()-1);
    }

    // Every Module Session Start
    split = Arrays.asList(line.split(SequentialLoader.getCsvSplit()));
    line = getFile().readLine();
    module_season = split.get(0) + "_" + split.get(1);
    if (isValidLine(split)){
      add(loadLine(split));
    }

    // Load same module session line by line.
    // Break out when encounter the first line of a new module.
    while (line != null) {
      line = line.substring(1, line.length()-1);
      split = Arrays.asList(line.split(SequentialLoader.getCsvSplit()));
      current_module_season = split.get(0) + "_" + split.get(1);
      if (!current_module_season.equals(module_season)){
        this.endLine = line;
        break;
      }
      if (isValidLine(split)){
        add(loadLine(split));
      }
      line = getFile().readLine();
    }

    if (line == null){
      this.endLine = null;
    }

  }

  /**
   * Generate the SequentialVleFileContent of current line.
   * @param split split value of one line in StudentVle.csv
   * @return a SequentialVleFileContent represent current line
   */
  private SequentialVleFileContent loadLine(List<String> split){
    HashMap<String, String> currentContent = new HashMap<>();
    for (int i=0 ; i < split.size() ; i++){
      currentContent.put(super.getTitles().get(i), split.get(i));
    }
    return new SequentialVleFileContent(currentContent);
  }

  /**
   * A valid line should contain the same number of strings as the file tile.
   * @param split split value of one line in StudentVle.csv
   * @return true if they have the same length
   */
  private boolean isValidLine(List<String> split){
    return split.size() == super.getTitles().size();
  }

  /**
   * @return true if endLine is null
   */
  public boolean reachEnd(){
    return this.endLine == null;
  }

  /**
   * Container clear
   */
  public void clearMemory(){
    getContainer().flash();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SequentialVleLoader that = (SequentialVleLoader) o;
    return Objects.equals(endLine, that.endLine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), endLine);
  }

  @Override
  public String toString() {
    return "SequentialVleLoader{" +
        super.toString() +
        "endLine='" + endLine + '\'' +
        '}';
  }
}
