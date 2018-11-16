package tankwars.objects;

import tankwars.GameWorld;
import tankwars.brain.CollisionDetector;
import tankwars.brain.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends MovingGameObjects {

    private BufferedImage bullet = null;
    private GameObject followIt;
    private float distance = 1000; // purpose is to find the minimum distance
    private int degree = 0;
    private int checkRateOfFire = 0;
    protected float maxHealth;
    protected int rateOfFire;
    protected float damage;

    public Enemy(BufferedImage img, float x, float y, String id, Controller c){
        super(img,x,y,id,c);
        health = 100;
        maxHealth = health;
        rateOfFire = 75;
        damage = 1;

        // Figure out which PlayerTank to track based on initial distance using distance formula
        for(int i = 0; i < cont.getArray().size(); i++){
            GameObject tempObject = cont.getArray().get(i);
            if(tempObject.getId().equals("Player1") || tempObject.getId().equals("Player2")){
                float temp = (float) Math.hypot(x - tempObject.getX(), y - tempObject.getY());
                if(temp < distance){
                    distance = temp;
                    followIt = tempObject;
                }
            }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        // Figure out which PlayerTank to track based on distance using distance formula
        for(int i = 0; i < cont.getArray().size(); i++){
            GameObject tempObject = cont.getArray().get(i);
            if(tempObject.getId().equals("Player1") || tempObject.getId().equals("Player2")){
                float temp = (float) Math.hypot(x - tempObject.getX(), y - tempObject.getY());
                if(temp < distance){
                    distance = temp;
                    followIt = tempObject;
                }
            }
        }

        // Track the Player Tank
        float diffX = x - followIt.getX() - 16;
        float diffY = y - followIt.getY() - 16;
        distance = (float) Math.hypot(x - followIt.getX(), y - followIt.getY());

        // the speed of the enemy based on the distance between the enemy and player
        velX = (-1.0f/distance) * diffX;
        velY = (-1.0f/distance) * diffY;

        collision();
        // check out of bounds
        if(y <= 0 || y > GameWorld.HEIGHT - 32){
            velY *= -1;
        }
        if(x <= 0 || x > GameWorld.WIDTH - 16){
            velX *= -1;
        }
        shoot();
        followSecondPlayer();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);

        // display health bar above the enemy
        g.setColor(Color.GRAY);
        g.fillRect((int)(x - image.getWidth() / 2), (int)(y - image.getHeight() / 2),image.getWidth() * 2, 5);
        g.setColor(Color.RED);
        g.fillRect((int)(x - image.getWidth() / 2), (int)(y - image.getHeight() / 2), (int)(health * (image.getWidth()/maxHealth)) * 2, 5);
        g.setColor(Color.WHITE);
        g.drawRect((int)(x - image.getWidth() / 2), (int)(y - image.getHeight() / 2),image.getWidth() * 2,5);
        g.drawString((int)health + "/" + (int)maxHealth, (int)(x - image.getWidth() / 2) + 5, (int)(y - image.getHeight() / 2 - 2));
    }

    public void collision(){
        if(CollisionDetector.EnemyVsWall(this,cont.getArray())){
            velX = velX * -5;
            velY = velY * -5;
        }
        if(CollisionDetector.EnemyVsPlayer(this, cont.getArray())){
            velX = velX * -1;
            velY = velY * -1;
        }
    }

    @Override
    public void shoot(){
        // figure out a frequency for the enemy to shoot
        if(rateOfFire == checkRateOfFire){
            try{
                // find the degree of bullet based on how fast enemy is
                // traveling in X and Y and then use inverse tangent
                // we can use how fast enemy is traveling assuming the enemy will travel at the same speed
                // for at least one delta time
                degree = (int) Math.toDegrees(Math.atan(velY/velX));
                bullet = ImageIO.read(Enemy.class.getResourceAsStream("/Mario.png"));
                // if statement is to fix a small bug
                if((followIt.getY() < this.y && followIt.getX() > this.x) || (followIt.getY() > this.y && followIt.getX() > this.x)){
                    degree += 180;
                }
                cont.addObject(new Bullet(bullet, (float)(this.x - 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                        (float)(this.y + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "EnemyBullet", cont, degree - 180));
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
            checkRateOfFire = 0;
        }
        else{
            checkRateOfFire++;
        }
    }

    @Override
    public void isDead() {

    }

    // Overload method so that we can access the PlayerTank finalHitCount
    public void isDead(PlayerTank p) {
        if(health <= 0.0){
            p.setFinalHitCount(p.getFinalHitCount() + 1);
            cont.removeObject(this);
        }
    }

    // if one of the players die follow the other one
    public void followSecondPlayer(){
        PlayerTank p = (PlayerTank) followIt;
        if(p.getHealth() <= 0){
            for(int i = 0; i < cont.getArray().size(); i++){
                if(cont.getArray().get(i).getId().equals("Player1") || cont.getArray().get(i).getId().equals("Player2"))
                    followIt = cont.getArray().get(i);

            }
        }
    }

    public void dealDamage(GameObject obj){
        if(obj.getId().equals("Wall")){
            Obstacles temp = (Obstacles) obj;
            temp.setHealth(temp.getHealth() - damage + 0.8f);
            temp.isDead();
        }
        else if(obj.getId().contains("Player")){
            PlayerTank temp = (PlayerTank) obj;
            temp.setHealth(temp.getHealth() - damage);
            temp.isDead();
        }
    }


}

