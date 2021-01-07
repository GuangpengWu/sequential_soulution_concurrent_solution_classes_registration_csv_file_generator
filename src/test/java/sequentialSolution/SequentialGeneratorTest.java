package sequentialSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.io.IOException;

/**
 * SequentialGeneratorTest
 */
public class SequentialGeneratorTest {

  private SequentialGenerator sequentialGenerator;

  /**
   * test main
   * @throws IOException when invalid input
   */
  @Test
  public void mainTest() throws IOException {
    sequentialGenerator.main(new String[]{"test_data"});

    try {
      BufferedReader reader_output = new BufferedReader(new FileReader("output/AAA_2013J.csv"));
      BufferedReader reader_expected = new BufferedReader(new FileReader("AAA_2013J_expected.csv"));

      String line_output = "";
      String everyLine_output = "";

      String line_expected = "";
      String everyLine_expected = "";

      List<String> allList_output = new ArrayList<>();
      List<String> allList_expected = new ArrayList<>();

      while((line_output=reader_output.readLine())!=null){
        everyLine_output = line_output;
        allList_output.add(everyLine_output);
      }

      while((line_expected=reader_expected.readLine())!=null){
        everyLine_expected = line_expected;
        allList_expected.add(everyLine_expected);
      }

      Assert.assertEquals(allList_expected, allList_output);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
