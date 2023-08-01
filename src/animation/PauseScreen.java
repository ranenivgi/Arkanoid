// 208210708 Ranen Ivgi
package animation;

import biuoop.DrawSurface;
import game.Constants;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(150, 209, 239));
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        d.setColor(Color.WHITE);
        d.drawText(168, d.getHeight() / 2 - 48, "don't", 50);
        d.drawText(298, d.getHeight() / 2 - 48, "STOP", 50);
        d.drawText(453, d.getHeight() / 2 - 48, "the party", 50);

        d.setColor(Color.BLACK);
        d.drawText(170, d.getHeight() / 2 - 50, "don't", 50);
        d.setColor(Color.RED);
        d.drawText(300, d.getHeight() / 2 - 50, "STOP", 50);
        d.setColor(Color.BLACK);
        d.drawText(455, d.getHeight() / 2 - 50, "the party", 50);
        d.drawText(300, d.getHeight() - 200, "press space to continue", 20);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}