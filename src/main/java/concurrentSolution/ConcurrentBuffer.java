package concurrentSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConcurrentBuffer<E> {

    private final int BUFFER_SIZE = 500;
    private int itemCount;
    private List<E> contents;

    /**
     * Concurrent Buffer Initialize
     */
    public ConcurrentBuffer() {
        this.itemCount = 0;
        this.contents = new ArrayList<>();
    }

    /**
     * @return true if items number smaller than buffer size
     */
    private boolean roomInBuffer() {
        return this.itemCount < this.BUFFER_SIZE;
    }

    /**
     * @return true if buffer is empty
     */
    private boolean bufferIsEmpty() {
        return this.itemCount == 0;
    }

    /**
     * Put content into buffer
     * @param content add into buffer
     */
    public synchronized void putIntoBuffer(E content) {
        while (!this.roomInBuffer()) {
            System.out.println("no room...wait ");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Adding into buffer");
        this.contents.add(content);
        this.itemCount++;
        notifyAll();
    }

    public synchronized E getFromBuffer() {
        while(this.bufferIsEmpty()) {
            System.out.println("Empty buffer...wait");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Removing from buffer");
        this.itemCount--;
        notifyAll();
        return this.contents.remove(0);
    }

    /**
     * @param item to check if exists in buffer
     * @return true if the buffer already contains the item
     */
    public synchronized boolean contains(E item){
        return this.contents.contains(item);
    }


    /**
     * @return buffer size
     */
    public int getBUFFER_SIZE() {
        return BUFFER_SIZE;
    }

    /**
     * @return number of items in buffer
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * @return contents in buffer
     */
    public List<E> getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentBuffer<?> buffer = (ConcurrentBuffer<?>) o;
        return itemCount == buffer.itemCount && Objects.equals(contents, buffer.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BUFFER_SIZE, itemCount, contents);
    }

    @Override
    public String
    toString() {
        return "ConcurrentBuffer{" +
                "BUFFER_SIZE=" + BUFFER_SIZE +
                ", itemCount=" + itemCount +
                ", contents=" + contents +
                '}';
    }
}
