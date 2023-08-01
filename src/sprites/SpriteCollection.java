// 208210708 Ranen Ivgi
package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type sprites.Sprite collection.
 */
public class SpriteCollection {
    private final List<Sprite> spriteables;

    /**
     * Instantiates a new sprites.Sprite collection.
     */
    public SpriteCollection() {
        this.spriteables = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        spriteables.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.spriteables.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> temp = new ArrayList<>(this.spriteables);
        for (Sprite sprite : temp) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteables) {
            sprite.drawOn(d);
        }
    }
}