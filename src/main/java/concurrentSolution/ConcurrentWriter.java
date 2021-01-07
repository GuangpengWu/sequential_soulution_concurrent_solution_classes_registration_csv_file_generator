package concurrentSolution;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class ConcurrentWriter implements Runnable {
    private ConcurrentBuffer<ConcurrentCumulate> calculatorBuffer;
    private String outputPath;
    private int threshold=-1;
    PrintWriter activity=null;

    /**
     * Concurrent Writer without threshold.
     * @param calculatorBuffer module_session as key and related content as value
     * @param outputPath output dir
     */
    public ConcurrentWriter(ConcurrentBuffer<ConcurrentCumulate> calculatorBuffer, String outputPath) {
        this.calculatorBuffer = calculatorBuffer;
        this.outputPath = outputPath;
        this.createOutput();
    }

    /**
     *  Concurrent Writer with threshold.
     * @param calculatorBuffer module_session as key and related content as value
     * @param outputPath output dir
     * @param threshold input
     * @throws IOException when encounter I/O exceptions
     */
    public ConcurrentWriter(ConcurrentBuffer<ConcurrentCumulate> calculatorBuffer, String outputPath, int threshold) throws IOException {
        this.calculatorBuffer = calculatorBuffer;
        this.outputPath = outputPath;
        this.threshold = threshold;
        this.createOutput();
        File activityFile = new File(outputPath + "/activity-" + this.threshold + ".csv");
        activityFile.createNewFile();
        this.activity = new PrintWriter(activityFile);
        writeActivityTitle();
    }

    /**
     * Create output dir under root path.
     */
    private void createOutput(){
        Path path = Paths.get(this.outputPath);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int size = calculatorBuffer.getItemCount();
        while (size > 0) {
            ConcurrentCumulate content = calculatorBuffer.getFromBuffer();

            try {
                Map<String, Integer> map = content.getCumulate();
                String filename = content.getModuleSession();
                File f = new File(outputPath + "/" + filename + ".csv");
                f.createNewFile();
                System.out.println("Writing " + filename + "....");

                PrintWriter writer = new PrintWriter(f);
                writeTitle(writer);
                if (threshold == -1){
                    writeWithoutThreshold(map, writer);
                } else {
                    writeWithThreshold(content, writer, activity);
                }

                writer.close();
                System.out.println("File " + filename + " written successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            size--;
        }
        if (activity != null){
            activity.close();
        }
    }

    /**
     * Write General File Title
     * @param writer of new file
     */
    private void writeTitle(PrintWriter writer){
        String title = "date,total\n";
        writer.write(title);
    }

    /**
     * Write activity file title
     */
    private void writeActivityTitle(){
        String title = "module_presentation,date,total_clicks\n";
        this.activity.write(title);
    }

    /**
     * Write without threshold
     * @param map date as key and click as value
     * @param writer of new file
     */
    private void writeWithoutThreshold(Map<String, Integer> map, PrintWriter writer) {

        for (String date : map.keySet()) {
            String line = date + "," + map.get(date).toString() + "\n";
            writer.write(line);
        }
    }

    /**
     * Write with threshold
     * @param content ate as key and click as value
     * @param writer of new file
     * @param activity writer of activity file
     */
    private void writeWithThreshold(ConcurrentCumulate content, PrintWriter writer, PrintWriter activity) {
        Map<String, Integer> map = content.getCumulate();
        String line;
        String filename = content.getModuleSession();
        for (String date : map.keySet()) {
            if (map.get(date) >= threshold){
                line = date + "," + map.get(date).toString() + "\n";
                writer.write(line);
                line = filename + "," + line;
                activity.write(line);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentWriter writer = (ConcurrentWriter) o;
        return threshold == writer.threshold &&
                Objects.equals(calculatorBuffer, writer.calculatorBuffer) &&
                Objects.equals(outputPath, writer.outputPath) &&
                Objects.equals(activity, writer.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculatorBuffer, outputPath, threshold, activity);
    }

    @Override
    public String toString() {
        return "ConcurrentWriter{" +
                "calculatorBuffer=" + calculatorBuffer +
                ", outputPath='" + outputPath + '\'' +
                ", threshold=" + threshold +
                ", activity=" + activity +
                '}';
    }
}
