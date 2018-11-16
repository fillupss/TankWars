package tankwars.objects;

import tankwars.brain.CollisionDetector;
import tankwars.brain.Controller;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    protected float damage;
    private int degree;

    public Bullet(BufferedImage img, float x, float y, String id, Controller c, int degree){
        super(img,x,y,id,c);
        this.damage = 15.0f;
        this.degree = degree;

        // due to tank rotation we need to find the angle for the bullet to travel
        // cosine represents X position in a triangle
        // sine represents Y position in a triangle
        velX = (float) (5 * Math.cos(Math.toRadians(this.degree)));
        velY = (float) (5 * Math.sin(Math.toRadians(this.degree)));
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        collision();

        if(this.x >= game.WIDTH || this.x <= 0 || this.y >= game.HEIGHT || this.y <= 0){
            cont.removeObject(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x,y);
        rotation.rotate(Math.toRadians(degree), this.image.getWidth()/2.0, this.image.getHeight()/2.0);
        Graphics2D ge = (Graphics2D) g;
        ge.drawImage(this.image, rotation, null);
    }

    public void dealDamage(GameObject obj){
        if(obj.getId().contains("Player")){
            PlayerTank temp = (PlayerTank) obj;
            temp.setShield(temp.getShield() - damage);
            if(temp.getShield() <= 0){
                temp.setHealth(temp.getHealth() + temp.getShield());
                temp.setShield(0);
            }
            temp.isDead();
        }
        else if(obj.getId().equals("Enemy")){
            Enemy temp = (Enemy) obj;
            temp.setHealth(temp.getHealth() - damage);
        }
        else if(obj.getId().equals("Wall")){
            Obstacles temp = (Obstacles) obj;
            temp.setHealth(temp.getHealth() - damage);
            temp.isDead();
        }
    }

    public void collision(){
        if(CollisionDetector.BulletVsWall(this, cont.getArray())){
            cont.removeObject(this);
        }
        if(CollisionDetector.PlayerBulletVsEnemyBullet(this, cont.getArray())){
            cont.removeObject(this);
        }
        if(CollisionDetector.PlayerBulletVsEnemy(this, cont.getArray())){
            cont.removeObject(this);
        }
    }
}

