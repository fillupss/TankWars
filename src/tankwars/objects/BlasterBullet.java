package tankwars.objects;

import tankwars.brain.CollisionDetector;
import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlasterBullet extends Bullet {

    public BlasterBullet(BufferedImage img, float x, float y, String id, Controller c, int degree) {
        super(img, x, y, id, c, degree);
        damage = 10.0f;
        velX = (float) (15 * Math.cos(Math.toRadians(degree)));
        velY = (float) (15 * Math.sin(Math.toRadians(degree)));
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
    public void dealDamage(GameObject obj) {
        super.dealDamage(obj);
    }

    // This is meant to be on purpose as we want the bullet to go through objects
    // It is one of its special perks
    // Other types of bullets will go through collision
    @Override
    public void collision() {
        if(CollisionDetector.BulletVsWall(this, cont.getArray())){

        }
        if(CollisionDetector.PlayerBulletVsEnemyBullet(this, cont.getArray())){

        }
        if(CollisionDetector.PlayerBulletVsEnemy(this,cont.getArray())){

        }
    }
}
