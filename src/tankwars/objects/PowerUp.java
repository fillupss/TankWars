package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUp extends GameObject {

    protected int bulletCount;

    public PowerUp(BufferedImage img, float x, float y, String id, Controller c) {

        super(img, x, y, id, c);
        velX = 0;
        velY = 0;
        bulletCount = 0;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x, (int)y,image.getWidth(),image.getHeight(),null);
    }

    public abstract void function(PlayerTank p);
}

