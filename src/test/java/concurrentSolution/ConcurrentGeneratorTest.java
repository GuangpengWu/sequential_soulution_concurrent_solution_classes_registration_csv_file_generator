package concurrentSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentGeneratorTest {

  @Before
  public void setUp(){
    ConcurrentGenerator.resetFlags();
  }

  @Test
  public void mainNoThresholdTest() throws InterruptedException {

    ConcurrentGenerator.main(new String[]{"test_data"});

    while (!ConcurrentGenerator.endWriting()){
      Thread.sleep(100);
    }
    System.out.println();

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

  @Test
  public void mainThresholdTest() throws InterruptedException {
    String[] args = {"test_data", "20"};

    ConcurrentGenerator.main(args);
    while (!ConcurrentGenerator.endWriting()){
      Thread.sleep(100);
    }
    System.out.println("test 2");

    try {
      BufferedReader reader_output2 = new BufferedReader(new FileReader("output/activity-20.csv"));
      BufferedReader reader_expected2 = new BufferedReader(new FileReader("activity-20_expected.csv"));

      String line_output2 = "";
      String everyLine_output2 = "";

      String line_expected2 = "";
      String everyLine_expected2 = "";

      List<String> allList_output2 = new ArrayList<>();
      List<String> allList_expected2 = new ArrayList<>();

      while((line_output2=reader_output2.readLine())!=null){
        everyLine_output2 = line_output2;
        allList_output2.add(everyLine_output2);
      }

      while((line_expected2=reader_expected2.readLine())!=null){
        everyLine_expected2 = line_expected2;
        allList_expected2.add(everyLine_expected2);
      }

      Assert.assertEquals(allList_expected2, allList_output2);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
