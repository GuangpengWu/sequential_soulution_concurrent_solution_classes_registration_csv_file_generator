package sequentialSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Nested Map named cumulate which is a map of date and sum number of click.
 */
public class SequentialCumulate {
  private final Map<String, Integer> cumulate;
  private final String moduleSession;

  public SequentialCumulate(String moduleSession){
    this.cumulate = new HashMap<>();
    this.moduleSession = moduleSession;
  }

  /**
   * If the date is already exists in the map, then will update the total click number by add current click.
   * If the date doesn't exist, then add this date as new key to the map.
   * @param date as key
   * @param click as current click in this date row
   */
  public void calculate(String date, String click){
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
    SequentialCumulate that = (SequentialCumulate) o;
    return Objects.equals(cumulate, that.cumulate) &&
        Objects.equals(moduleSession, that.moduleSession);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cumulate, moduleSession);
  }

  @Override
  public String toString() {
    return "SequentialCumulate{" +
        "cumulate=" + cumulate +
        ", moduleSession='" + moduleSession + '\'' +
        '}';
  }

  /**
   * @return cumulate
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
