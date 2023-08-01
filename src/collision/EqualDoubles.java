// 208210708 Ranen Ivgi
package collision;

/**
 * The type Equal doubles.
 */
public class EqualDoubles {
    /**
     * The constant Comparison_threshold.
     */
    private static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * checks if double parameters are equal.
     *
     * @param a the first double
     * @param b the second double
     * @return true if they are equal, false otherwise.
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EqualDoubles.COMPARISON_THRESHOLD;
    }
}
