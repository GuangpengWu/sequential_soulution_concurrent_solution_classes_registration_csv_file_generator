package sequentialSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class SequentialLoader {
  private final String filename;
  private final String pathname;
  private BufferedReader file;
  private List<String> titles;
  private SequentialContainer Container;
  private static final String CSV_SPLIT = "\"?,\"?";

  public SequentialLoader(String pathName, String filename) throws InvalidFileException, IOException {
    this.pathname = pathName;
    this.filename = filename;
    getFile(pathName);
    setTitle();
  }


  /**
   * Get the target file ready for reading.
   * @param pathName input path with filename
   * @throws InvalidFileException if there is no such file as filename under given path
   */
  private void getFile(String pathName) throws InvalidFileException {
    String path = pathName + filename;
    this.file = new BufferedReader(MyMatcher.validFile(path));
  }


  /**
   * Read the first line and set title list
   * @throws IOException if something goes wrong while reading
   * @throws InvalidFileException if file is not valid
   */
  private void setTitle() throws IOException, InvalidFileException {
    String titleLine = file.readLine();
    // removing leading and ending quote
    titleLine = titleLine.substring(1, titleLine.length()-1);
    List<String> title = Arrays.asList(titleLine.split(CSV_SPLIT));
    if (MyMatcher.validFileTitle(title, filename)){
      this.titles = title;
    }
  }

  /**
   * @param content add to container
   */
  public void add(SequentialContent content){
    this.Container.addContent(content);
  }

  /**
   * @return filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * @return csv split
   */
  public static String getCsvSplit() {
    return CSV_SPLIT;
  }

  /**
   * @return file
   */
  public BufferedReader getFile() {
    return file;
  }

  /**
   * @return file titles
   */
  public List<String> getTitles() {
    return titles;
  }

  /**
   * @return container
   */
  public SequentialContainer getContainer() {
    return Container;
  }

  /**
   * @param container to be set
   */
  public void setContainer(SequentialContainer container) {
    Container = container;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SequentialLoader that = (SequentialLoader) o;
    return Objects.equals(filename, that.filename) &&
        Objects.equals(titles, that.titles) &&
        Objects.equals(Container, that.Container);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filename, file, titles, Container);
  }

  @Override
  public String toString() {
    return "SequentialLoader{" +
        "filename='" + filename + '\'' +
        ", file=" + pathname + filename +
        ", titles=" + titles +
        ", Container=" + Container +
        '}';
  }
}
