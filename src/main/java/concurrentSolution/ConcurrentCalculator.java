package concurrentSolution;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentCalculator implements Runnable{
    private ConcurrentBuffer<ConcurrentCumulate> calculatorBuffer;
    private ConcurrentBuffer<ConcurrentVleContent> recordBuffer;
    private BlockingQueue<String> moduleSessions;

    public ConcurrentCalculator(ConcurrentBuffer<ConcurrentCumulate> calculatorBuffer, ConcurrentBuffer<ConcurrentVleContent> recordBuffer) {
        this.calculatorBuffer = calculatorBuffer;
        this.recordBuffer = recordBuffer;
        this.moduleSessions = new LinkedBlockingQueue<>();
    }

    /**
     * If module_sessions are equal, then just update current module_session content.
     * Else will create a new module_session as key to store content.
     */
    @Override
    public void run() {
        System.out.println("Calculator starting");
        boolean condition = !ConcurrentLoader.isExit() || recordBuffer.getItemCount() > 0;
        while (condition) {
            System.out.println("Calculator is awake");
            ConcurrentVleContent item = recordBuffer.getFromBuffer();
            System.out.println(item + " consumed");

            String moduleSession = item.getModuleSession();
            // if this file is not added into the calculator buffer yet
            ConcurrentCumulate cumulate;
            if (!this.moduleSessions.contains(moduleSession)){
                cumulate = new ConcurrentCumulate(moduleSession);
                cumulate.calculate(item.getContent().get("date"), item.getContent().get("sum_click"));
                this.moduleSessions.add(moduleSession);
                calculatorBuffer.putIntoBuffer(cumulate);

            } else {
                // get from calculator buffer
                for (int i=0; i<calculatorBuffer.getItemCount(); i++){
                    cumulate = calculatorBuffer.getFromBuffer();
                    if (cumulate.getModuleSession().equals(moduleSession)){
                        cumulate.calculate(item.getContent().get("date"), item.getContent().get("sum_click"));
                    }
                    calculatorBuffer.putIntoBuffer(cumulate);
                }

            }

            condition = !ConcurrentLoader.isExit() || recordBuffer.getItemCount() > 0;

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentCalculator that = (ConcurrentCalculator) o;
        return Objects.equals(calculatorBuffer, that.calculatorBuffer) &&
                Objects.equals(recordBuffer, that.recordBuffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculatorBuffer, recordBuffer);
    }

    @Override
    public String toString() {
        return "ConcurrentCalculator{" +
                "calculatorBuffer=" + calculatorBuffer +
                ", recordBuffer=" + recordBuffer +
                ", moduleSessions=" + moduleSessions +
                '}';
    }

    /**
     * @return calculator buffer
     */
    public ConcurrentBuffer<ConcurrentCumulate> getCalculatorBuffer() {
        return calculatorBuffer;
    }

    /**
     * @return record buffer
     */
    public ConcurrentBuffer<ConcurrentVleContent> getRecordBuffer() {
        return recordBuffer;
    }

    /**
     * @return module_session
     */
    public BlockingQueue<String> getModuleSessions() {
        return moduleSessions;
    }
}

