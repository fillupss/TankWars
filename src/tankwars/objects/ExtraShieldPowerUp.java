package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ExtraShieldPowerUp extends PowerUp {

    public ExtraShieldPowerUp(BufferedImage img, float x, float y, String id, Controller c) {
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
        p.setShield(p.getShield() + 25);
    }
}