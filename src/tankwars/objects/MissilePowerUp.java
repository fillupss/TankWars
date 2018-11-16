package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MissilePowerUp extends PowerUp {

    public MissilePowerUp(BufferedImage img, float x, float y, String id, Controller c) {
        super(img, x, y, id, c);
        bulletCount = 3;
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
        p.setMissilePowerUp(true);
        p.setBlastPowerUp(false);
        p.setTripleShotPowerUp(false);
        p.setCount(bulletCount);
        p.setTotalCount(bulletCount);
    }
}

