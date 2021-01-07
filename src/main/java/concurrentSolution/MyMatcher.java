package concurrentSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMatcher {

    private static final List<String> COURSE_TITLE = new ArrayList<>(Arrays.asList("code_module", "code_presentation", "module_presentation_length"));
    private static final List<String> VLE_TITLE = new ArrayList<>(Arrays.asList("code_module","code_presentation","id_student","id_site","date","sum_click"));

    /**
     * Return pathName iff pathName represent an existed directory
     * @param pathName directory name with all source csv
     * @return pathName iff filename represent an existed directory
     * @throws InvalidFileException if directory is not valid
     */
    public static String validSource(String pathName) throws InvalidFileException {
        File path = new File(pathName);

        if (!path.exists()) {
            throw new InvalidFileException(String.format("No such directory named %s exist.\n", pathName));
        }
        if (!path.isDirectory()) {
            throw new InvalidFileException(String.format("Source path %s is not a directory.\n", pathName));
        }
        return pathName;
    }


    /**
     * Return filename iff filename represent an existed csv file
     * @param filename src file name
     * @return the file object of given filename
     * @throws InvalidFileException if file is not valid
     */
    public static FileReader validFile(String filename) throws InvalidFileException {
        if (!filename.endsWith(".csv")){
            System.out.println(filename);
            throw new InvalidFileException("Please provide valid " + filename + " file.");
        }
        FileReader file;
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new InvalidFileException(String.format("No such file named %s exist.\n", filename));
        }
        return file;
    }


    /**
     * Return true iff file and file title matches
     * @param title column title
     * @param filename src file name
     * @return true iff file and file title matches
     * @throws InvalidFileException if file is not valid
     */
    public static boolean validFileTitle(List<String> title, String filename) throws InvalidFileException {
        if (filename.equals("/courses.csv") && title.equals(COURSE_TITLE)){
            return true;
        } else if ((filename.equals("/sampleStudentVle.csv") || filename.equals("/studentVle.csv")) && title.equals(VLE_TITLE)){
            return true;
        }
        throw new InvalidFileException("Please provide valid " + filename +" file.");
    }



}
