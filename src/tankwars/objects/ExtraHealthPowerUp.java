package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ExtraHealthPowerUp extends PowerUp {

    public ExtraHealthPowerUp(BufferedImage img, float x, float y, String id, Controller c) {
        super(img, x, y, id, c);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void function(PlayerTank p) {
        p.setHealth(p.getHealth() + 25);
    }
}
