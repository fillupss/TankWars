package tankwars.objects;

import tankwars.GameWorld;
import tankwars.brain.CollisionDetector;
import tankwars.brain.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBoss extends Enemy {

    private int tick = 0;
    private int checkRateOfFire = 0;
    private BufferedImage basicEnemy = null;
    private BufferedImage tankyEnemy = null;
    Random r = new Random();

    public EnemyBoss(BufferedImage img, float x, float y, String id, Controller c) {
        super(img, x, y, id, c);
        this.health = 3000;
        rateOfFire = 600;
        maxHealth = health;
        damage = 100;
        velX = -0.2f;
        velY = 0;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(x <= 0){
            velX = velX * -1;
        }
        collision();
        shoot();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
        g.setColor(Color.GRAY);
        g.fillRect(20, GameWorld.HEIGHT - 100, GameWorld.WIDTH - 100, 20);
        g.setColor(Color.blue);
        g.fillRect(20, GameWorld.HEIGHT - 100, (int) (health * ((GameWorld.WIDTH - 100) / maxHealth)),20);
        g.setColor(Color.white);
        g.drawRect(20, GameWorld.HEIGHT - 100, GameWorld.WIDTH - 100, 20);
    }

    @Override
    public void collision() {
        if(CollisionDetector.EnemyVsWall(this, cont.getArray())){
            // do nothing

        }
        if(CollisionDetector.EnemyVsPlayer(this, cont.getArray())){
            // do nothing
        }
    }

    @Override
    public void shoot() {
        try{
            basicEnemy = ImageIO.read(EnemyBoss.class.getResourceAsStream("/Enemy.png"));
            tankyEnemy = ImageIO.read(EnemyBoss.class.getResourceAsStream("/TankyEnemy.png"));
            // each time the enemy boss summons an enemy, the frequency of the boss to summon the next enemy
            // will be faster until it dies
            if(rateOfFire == checkRateOfFire){
                if(tick < 2){
                    cont.addObject(new Enemy(basicEnemy,r.nextInt(30) + this.x + 30, r.nextInt(GameWorld.HEIGHT - 64) - 32,
                            "Enemy", cont));
                    tick++;
                }
                else if(tick == 2){
                    cont.addObject(new TankyEnemy(tankyEnemy,r.nextInt(30) + this.x + 30, r.nextInt(GameWorld.HEIGHT - 64) - 32,
                            "Enemy", cont));
                    tick = 0;
                }
                checkRateOfFire = 0;
                rateOfFire = rateOfFire - 25;
                if(rateOfFire <= 0){
                    rateOfFire = 50;
                }
            }
            else{
                checkRateOfFire++;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void isDead() {
        super.isDead();
    }

    @Override
    public void isDead(PlayerTank p) {

        if(health <= 0.0){
            p.setFinalHitCount(p.getFinalHitCount() + 1);
            cont.removeObject(this);
        }
    }

    @Override
    public void dealDamage(GameObject obj) {
        super.dealDamage(obj);
    }

    public void setHealth(float health){
        this.health = health;
        // fix a small bug on the HUD
        if(this.health < 0){
            this.health = 0;
        }
        else if(this.health > maxHealth){
            this.health = maxHealth;
        }
    }
}

