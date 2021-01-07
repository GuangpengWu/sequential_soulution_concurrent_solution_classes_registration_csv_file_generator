package concurrentSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MyMatcherTest {

  private static final List<String> VLE_TITLE = new ArrayList<>(Arrays.asList("code_module","code_presentation","id_student","id_site","date","sum_click"));

  @Test
  public void testValidSource() throws InvalidFileException {
    Assert.assertEquals("test_data", MyMatcher.validSource("test_data"));
  }

  @Test
  public void testValidFile() throws InvalidFileException, IOException {
    FileReader file = new FileReader("test_data/studentVle.csv");
    BufferedReader expected = new BufferedReader(file);
    FileReader actualFile = MyMatcher.validFile("test_data/studentVle.csv");
    BufferedReader actual = new BufferedReader(actualFile);
    StringBuilder e = new StringBuilder();
    StringBuilder a = new StringBuilder();
    String line;
    while((line=expected.readLine())!=null){
      e.append(line);
    }
    while((line=actual.readLine())!=null){
      a.append(line);
    }
    Assert.assertEquals(e.toString(), a.toString());
  }

  @Test(expected = InvalidFileException.class)
  public void testInvalidFile() throws InvalidFileException {
      MyMatcher.validFile("");
  }

  @Test
  public void validFileTitleTest() throws InvalidFileException {
    boolean actual = MyMatcher.validFileTitle(VLE_TITLE, "/studentVle.csv");
    Assert.assertTrue(actual);
  }

}
