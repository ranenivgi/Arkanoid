// 208210708 Ranen Ivgi
package game;

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param count the counter
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Instantiates a new Counter starting from 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * add one to current count.
     */
    public void increase() {
        this.count += 1;
    }

    /**
     * subtract one from current count.
     */
    public void decrease() {
        this.count -= 1;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }
}
