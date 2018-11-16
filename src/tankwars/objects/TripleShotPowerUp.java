package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TripleShotPowerUp extends PowerUp {

    public TripleShotPowerUp(BufferedImage img, float x, float y, String id, Controller c) {
        super(img, x, y, id, c);
        bulletCount = 15;
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
        p.setTripleShotPowerUp(true);
        p.setBlastPowerUp(false);
        p.setMissilePowerUp(false);
        p.setCount(bulletCount);
        p.setTotalCount(bulletCount);
    }
}

