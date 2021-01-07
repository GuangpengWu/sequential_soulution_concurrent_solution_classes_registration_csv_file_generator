package concurrentSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ConcurrentLoader implements Runnable{
    private volatile static boolean exit = false;
    private ConcurrentBuffer<ConcurrentVleContent> buffer;
    private List<String> titles;
    private static final String CSV_SPLIT = "\"?,\"?";
    private BufferedReader file;
    private final String filename;
    private final String pathName;

    public ConcurrentLoader(ConcurrentBuffer<ConcurrentVleContent> buffer, String filename, String pathName) throws InvalidFileException, IOException {
        this.buffer = buffer;
        this.filename = filename;
        this.pathName = pathName;
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


    @Override
    public void run() {
        System.out.println("Starting Loader");

        while(!exit) {
            try {
                String line = file.readLine();
                if (line != null){
                    line = line.substring(1, line.length()-1);
                    List<String> split = Arrays.asList(line.split(CSV_SPLIT));
                    if (isValidLine(split)){
                        buffer.putIntoBuffer(loadLine(split));
                    }
                } else {
                    System.out.println("Loader is done!");
                    exit = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Generate the ConcurrentVleFileContent of current line.
     * @param split split value of one line in StudentVle.csv
     * @return a SequentialVleFileContent represent current line
     */
    private ConcurrentVleContent loadLine(List<String> split){
        HashMap<String, String> currentContent = new HashMap<>();
        for (int i=0 ; i < split.size() ; i++){
            currentContent.put(titles.get(i), split.get(i));
        }
        return new ConcurrentVleContent(currentContent);
    }

    /**
     * A valid line should contain the same number of strings as the file tile.
     * @param split split value of one line in StudentVle.csv
     * @return true if they have the same length
     */
    private boolean isValidLine(List<String> split){
        return split.size() == titles.size();
    }

    public static boolean isExit() {
        return exit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentLoader that = (ConcurrentLoader) o;
        return Objects.equals(buffer, that.buffer) &&
                Objects.equals(titles, that.titles) &&
                Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buffer, titles, file, filename);
    }

    @Override
    public String toString() {
        return "ConcurrentLoader{" +
                "buffer=" + buffer +
                ", titles=" + titles +
                ", file=" + pathName + filename +
                ", filename='" + filename + '\'' +
                '}';
    }

    /**
     * Set exit status.
     */
    public static void resetExit(){
        ConcurrentLoader.exit = false;
    }
}
