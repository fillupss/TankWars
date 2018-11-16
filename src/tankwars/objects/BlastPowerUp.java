package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlastPowerUp extends PowerUp {

    public BlastPowerUp(BufferedImage img, float x, float y, String id, Controller c) {
        super(img, x, y, id, c);
        bulletCount = 10;
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
        p.setCount(bulletCount);
        p.setTotalCount(bulletCount);
        p.setMissilePowerUp(false);
        p.setTripleShotPowerUp(false);
        p.setBlastPowerUp(true);
    }
}

