// 208210708 Ranen Ivgi
package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private final Color color;
    private final String levelName;

    /**
     * Instantiates a new Level background.
     *
     * @param c the color
     * @param s the level Name
     */
    public Background(Color c, String s) {
        this.color = c;
        this.levelName = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0, 128, 255));
        switch (levelName) {
            case "Direct Hit":
                for (int i = 120; i > 30; i -= 30) {
                    d.drawCircle(400, 220, i);
                }
                d.setColor(Color.RED);
                d.drawLine(400, 100, 400, 340);
                d.drawLine(280, 220, 520, 220);
                break;
            case "Wide Easy":
                // stems
                d.setColor(Color.GREEN);
                d.fillRectangle(50, 500, 10, 100);
                d.fillRectangle(230, 500, 10, 100);
                d.fillRectangle(410, 500, 10, 100);
                d.fillRectangle(580, 500, 10, 100);
                d.fillRectangle(750, 500, 10, 100);
                // first flower
                d.setColor(new Color(0, 153, 153));
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(55 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(85, 440, 30);
                d.fillCircle(55, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(55, 440, 25);
                // second flower
                d.setColor(new Color(255, 128, 0));
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(235 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(265, 440, 30);
                d.fillCircle(235, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(235, 440, 25);
                // third flower
                d.setColor(new Color(150, 0, 153));
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(415 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(445, 440, 30);
                d.fillCircle(415, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(415, 440, 25);
                // fourth flower
                d.setColor(Color.RED);
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(585 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(615, 440, 30);
                d.fillCircle(585, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(585, 440, 25);
                // fifth flower
                d.setColor(Color.BLUE);
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(755 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(785, 440, 30);
                d.fillCircle(755, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(755, 440, 25);
                // sun
                d.setColor(new Color(204, 204, 0));
                for (int i = 1; i < 40; i++) {
                    d.drawLine(25, 25, 250 - 10 * i, 250);
                }
                for (int i = 1; i < 40; i++) {
                    d.drawLine(25, 25, 250 + 20 * i, 250);
                }
                d.fillCircle(25, 25, 130);
                d.fillCircle(25, 25, 125);
                break;
            case "Turquoise":
                //setting up the building
                d.setColor(new Color(43, 43, 43));
                d.fillRectangle(50, 250, 100, 350);
                d.setColor(Color.WHITE);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.drawLine(50 + i * 20 + j, 250, 50 + i * 20 + j, 600);
                    }
                }
                d.setColor(new Color(192, 192, 192));
                d.fillRectangle(90, 175, 20, 75);
                d.setColor(Color.GRAY);
                d.fillRectangle(95, 125, 10, 50);
                //setting the antenna
                d.setColor(Color.DARK_GRAY);
                d.fillCircle(100, 115, 10);
                d.setColor(Color.WHITE);
                d.fillCircle(100, 115, 5);
                d.setColor(Color.BLACK);
                d.drawRectangle(90, 175, 20, 75);
                d.drawRectangle(95, 125, 10, 50);
                d.drawCircle(100, 115, 10);
                break;
            case "Final Four":
                //setting clouds
                d.setColor(Color.GRAY);
                for (int i = 0; i < 3; i++) {
                    d.fillCircle(100 + i * 30, 450, 30);
                    d.fillCircle(700 + i * 30, 500, 30);
                }
                d.setColor(Color.DARK_GRAY);
                for (int i = 0; i < 3; i++) {
                    d.fillCircle(70 + i * 30, 420, 30);
                    d.fillCircle(670 + i * 30, 470, 30);
                }
                d.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < 3; i++) {
                    d.fillCircle(40 + i * 30, 390, 30);
                    d.fillCircle(640 + i * 30, 440, 30);
                }
                //setting the rain
                for (int i = 0; i < 4; i++) {
                    d.drawLine(50 + i * 30, 390 + i * 30, 80 + i * 30, 600);
                    d.drawLine(650 + i * 30, 440 + i * 30, 680 + i * 30, 600);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void timePassed() {
        //for the future
    }
}
