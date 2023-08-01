// 208210708 Ranen Ivgi
package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * runs one frame of the animation.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * checks if the animation should be stopped.
     *
     * @return the boolean
     */
    boolean shouldStop();
}