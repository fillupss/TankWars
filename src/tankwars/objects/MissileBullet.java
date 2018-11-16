package tankwars.objects;

import tankwars.brain.CollisionDetector;
import tankwars.brain.Controller;
import tankwars.brain.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MissileBullet extends Bullet {

    private boolean impact = false;
    private int areaOfImpactX = 0;
    private int areaOfImpactY = 0;
    private Animation explosion;
    private BufferedImage[] bulletExplosion = new BufferedImage[7];

    public MissileBullet(BufferedImage img, float x, float y, String id, Controller c, int degree) {
        super(img, x, y, id, c, degree);
        damage = 95.0f;
        velX = (float) (12 * Math.cos(Math.toRadians(degree)));
        velY = (float) (12 * Math.sin(Math.toRadians(degree)));
        try{
            bulletExplosion[0] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion1.png"));
            bulletExplosion[1] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion2.png"));
            bulletExplosion[2] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion3.png"));
            bulletExplosion[3] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion4.png"));
            bulletExplosion[4] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion5.png"));
            bulletExplosion[5] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion6.png"));
            bulletExplosion[6] = ImageIO.read(MissileBullet.class.getResourceAsStream("/Animations/BulletExplosion7.png"));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        explosion = new Animation(200, bulletExplosion);
    }

    @Override
    public void tick() {
        super.tick();
        // if the missile collide with an object then impact will be true and
        // will do an animation
        if(impact){
            if(explosion.getIndex() < 4){
                explosionDamage(explosion.getCurrentFrame(),1);
            }
            if(explosion.getIndex() == bulletExplosion.length - 1){
                impact = false;
                explosion.setIndex(0);
                cont.removeObject(this);
            }
            explosion.tick();
        }
    }

    @Override
    public void draw(Graphics g) {

        super.draw(g);
        if(impact || (explosion.getIndex() == bulletExplosion.length - 1)){
            g.drawImage(explosion.getCurrentFrame(),areaOfImpactX,areaOfImpactY,null);
        }
    }

    @Override
    public void dealDamage(GameObject obj){
        super.dealDamage(obj);
    }

    @Override
    public void collision() {

        if(CollisionDetector.BulletVsWall(this, cont.getArray())){
            damage = 0;
            areaOfImpactY = (int)(this.y + velY);
            areaOfImpactX = (int)(this.x + velX);
            velX = 0;
            velY = 0;
            impact = true;

        }
        if(CollisionDetector.PlayerBulletVsEnemyBullet(this, cont.getArray())){
            // Do nothing
        }
        if(CollisionDetector.PlayerBulletVsEnemy(this, cont.getArray())){
            damage = 0;
            areaOfImpactY = (int)(this.y + velY);
            areaOfImpactX = (int)(this.x + velX);
            velX = 0;
            velY = 0;
            impact = true;

        }
    }

    // Explosion damage is done by the intersection of each animation frame (rectangle bounds) to a game object,
    // which is found by going through the controller
    public void explosionDamage(BufferedImage explosionPhase, int damage) {
        Rectangle explosionBounds = new Rectangle(this.areaOfImpactX, this.areaOfImpactY, explosionPhase.getWidth(), explosionPhase.getHeight());
        for (int i = 0; i < cont.getArray().size(); i++) {
            GameObject obj = cont.getArray().get(i);
            if (obj.getCollisionBounds().intersects(explosionBounds)) {
                if (obj.getId().equals("Enemy")) {
                    MovingGameObjects mobj = (MovingGameObjects) obj;
                    mobj.setHealth(mobj.getHealth() - damage);
                } else if (obj.getId().equals("Wall")) {
                    Obstacles oobj = (Obstacles) obj;
                    oobj.setHealth(oobj.getHealth() - damage);
                }
            }
        }
    }
}

