package concurrentSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConcurrentCumulate {
    private Map<String, Integer> cumulate;
    private String moduleSession;

    /**
     * Cumulate is a hashmap with date as key, and click as value
     * @param moduleSession module_session
     */
    public ConcurrentCumulate(String moduleSession){
        this.cumulate = new HashMap<>();
        this.moduleSession = moduleSession;
    }

    /**
     * If the date already exists, then just update the click; else, create a new date, click pair
     * @param date value in the column
     * @param click value in the column
     */
    public synchronized void calculate(String date, String click){
        int currentClick = Integer.parseInt(click);
        // if already exist
        if (cumulate.containsKey(date)){
            cumulate.put(date, cumulate.get(date)+currentClick);
        } else {
            cumulate.put(date, currentClick);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentCumulate that = (ConcurrentCumulate) o;
        return Objects.equals(cumulate, that.cumulate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cumulate);
    }

    @Override
    public String toString() {
        return "ConcurrentCumulate{" +
                "moduleSession=" + moduleSession +
                ", cumulate='" + cumulate + '\'' +
                '}';
    }

    /**
     * @return cumulate as hashmap
     */
    public Map<String, Integer> getCumulate() {
        return cumulate;
    }

    /**
     * @return module_session
     */
    public String getModuleSession() {
        return moduleSession;
    }
}
