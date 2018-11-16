package tankwars.objects;

import tankwars.GameWorld;
import tankwars.brain.CollisionDetector;
import tankwars.brain.Controller;
import tankwars.brain.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class PlayerTank extends MovingGameObjects {

    final static int ROTATION_SPEED = 4;
    final static int VELOCITY_SPEED = 4;
    private BufferedImage bullet = null;
    private BufferedImage[] explosionSprites = new BufferedImage[8];
    private int degree = 0;
    private int rotationspeed = 0;
    private float shield;
    private int life = 2;
    private Animation explosion;
    private int areaOfDeathX = 0, areaOfDeathY = 0;

    // for animation
    private boolean deadAnimation = false;

    private boolean missilePowerUp = false; // 3 shots
    private boolean tripleShotPowerUp = false; // 15 shots (can be used as normal bullet x3)
    private boolean blastPowerUp = false; // 10 shots
    private int count = 0; // used to know how many shots are left in the power up

    // for HUD purposes
    private int totalCount = 0;
    private int countBulletsHit;
    private int finalHitCount;


    public PlayerTank(BufferedImage img, float x, float y, String id, Controller c){
        super(img, x, y, id, c);
        this.shield = 0;
        this.health = 100;
        this.countBulletsHit = 0;
        this.finalHitCount = 0;

        try{
            explosionSprites[0] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite1.png"));
            explosionSprites[1] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite2.png"));
            explosionSprites[2] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite3.png"));
            explosionSprites[3] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite4.png"));
            explosionSprites[4] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite5.png"));
            explosionSprites[5] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite6.png"));
            explosionSprites[6] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite7.png"));
            explosionSprites[7] = ImageIO.read(PlayerTank.class.getResourceAsStream("/Animations/explosionSprite8.png"));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        explosion = new Animation(200, explosionSprites);
    }

    // This tick method will move and rotate the tank.
    // Shooting will come from the bullet
    @Override
    public void tick() {
        // deadanimation is true when the tank is 0 health
        // then an explosion animation pops up
        // we stop the animation when the bufferimage array is in its last index and set deadanimation to false
        if(deadAnimation){
            if(explosion.getIndex() == explosionSprites.length - 1){
                deadAnimation = false;
                explosion.setIndex(0);
            }
            explosion.tick();
        }
        else{
            if(life == -1){
                cont.removeObject(this);
            }
        }

        x += velX;
        y += velY;
        collision();
        degree += rotationspeed;
        if(x < 0){
            x = 0;
        }
        else if(x > GameWorld.WIDTH - 50){
            x = GameWorld.WIDTH - 50;
        }

        if(y < 0){
            y = 0;
        }
        else if(y > GameWorld.HEIGHT - 66){
            y = GameWorld.HEIGHT - 66;
        }
    }

    // need to distinguish between player1 and player2 bullets to see who gets the score
    public void shoot(){
        try{
            if(id.equals("Player1")){
                if(missilePowerUp && count > 0){
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Missile.png"));
                    cont.addObject(new MissileBullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                            (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "PlayerBullet1", cont, degree));
                    count--;
                }
                else if(tripleShotPowerUp && count > 0){
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Mario.png"));
                    for(int i = -20; i <= 30; i = i + 20){
                        cont.addObject(new Bullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree + i))) ,
                                (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree + i))), "PlayerBullet1", cont, degree + i));
                    }
                    count--;
                }
                else if(blastPowerUp && count > 0){
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Blast.png"));
                    cont.addObject(new BlasterBullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                            (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "PlayerBullet1", cont, degree));
                    count--;
                }
                else{
                    totalCount = 0;
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Mario.png"));
                    cont.addObject(new Bullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                            (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "PlayerBullet1", cont, degree));
                }
            }
            else if(id.equals("Player2")){
                if(missilePowerUp && count > 0){
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Missile.png"));
                    cont.addObject(new MissileBullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                            (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "PlayerBullet2", cont, degree));
                    count--;
                }
                else if(tripleShotPowerUp && count > 0){
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Mario.png"));
                    for(int i = -20; i <= 20; i = i + 20){
                        cont.addObject(new Bullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree + i))) ,
                                (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree + i))), "PlayerBullet2", cont, degree + i));
                    }
                    count--;
                }
                else if(blastPowerUp && count > 0){
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Blast.png"));
                    cont.addObject(new BlasterBullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                            (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "PlayerBullet2", cont, degree));
                    count--;
                }
                else{
                    bullet = ImageIO.read(PlayerTank.class.getResourceAsStream("/Mario.png"));
                    cont.addObject(new Bullet(bullet, (float)(this.x + 22 + (image.getWidth()/2) * Math.cos(Math.toRadians(degree))) ,
                            (float)(this.y + 22 + (image.getWidth()/2) * Math.sin(Math.toRadians(degree))), "PlayerBullet2", cont, degree));
                }
                if(count == 0){
                    totalCount = 0;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void collision(){
        if(CollisionDetector.PlayerVsWall(this, cont.getArray())){
            // change later
            this.x = x - velX;
            this.y = y - velY;
            this.health = health - 0.2f;
            this.isDead();
        }
        if(CollisionDetector.PlayerVsPlayer(this, cont.getArray())){
            this.x = x - velX;
            this.y = y - velY;
        }
        if(CollisionDetector.PlayerVsPowerUp(this, cont.getArray())){
            velX = 0;
            velY = 0;
        }
        if(CollisionDetector.PlayerVsPlayerBullet(this, cont.getArray())){
            // do nothing
        }
        if(CollisionDetector.PlayerVsEnemyBullet(this, cont.getArray())){
            velX = 0;
            velY = 0;
        }

    }

    public void moveUp(){
        velY = VELOCITY_SPEED * -1;
    }

    public void moveDown(){
        velY = VELOCITY_SPEED;
    }

    public void moveRight(){
        velX = VELOCITY_SPEED;
    }

    public void moveLeft(){
        velX = VELOCITY_SPEED * -1;
    }

    public void rotateCW(){
        rotationspeed = -1 * ROTATION_SPEED;
    }
    public void rotateCCW(){
        rotationspeed =  ROTATION_SPEED;
    }

    public void stopX(){
        velX = 0;
    }

    public void stopY(){
        velY = 0;
    }

    public void stopRotating(){
        rotationspeed = 0;
    }

    // method for when the player tank has 0 health and will cause an explosion
    public void isDead(){
        if(health <= 0){
            areaOfDeathX = (int)x;
            areaOfDeathY = (int)y;
            if(life >= 0){
                if(life == 0){
                    this.health = 0;
                    velX = 0;
                    velY = 0;
                }
                else{
                    this.x = 0;
                    this.y = 0;
                    this.health = 100;
                }
                this.life--;
                deadAnimation = true;
            }
        }
    }

    public void setMissilePowerUp(boolean b){
        this.missilePowerUp = b;
    }
    public void setTripleShotPowerUp(boolean b){
        this.tripleShotPowerUp = b;
    }
    public void setBlastPowerUp(boolean b){
        this.blastPowerUp = b;
    }
    public void setCount(int count){
        this.count = count;
    }
    public void setTotalCount(int count){
        this.totalCount = count;
    }

    public int getCount(){
        return count;
    }
    public int getTotalCount(){
        return totalCount;
    }
    public int getCountBulletsHit(){
        return countBulletsHit;
    }
    public int getFinalHitCount(){
        return finalHitCount;
    }
    public void setCountBulletsHit(int c){
        this.countBulletsHit = c;
    }
    public void setFinalHitCount(int c){
        this.finalHitCount = c;
    }


    // shield is used for the purpose that it will only be deducted
    // when an enemy bullet collides with the player tank.
    public void setShield(float shield){
        this.shield = shield;
    }
    public float getShield(){
        return this.shield;
    }

    @Override
    public void draw(Graphics g) {
        // we divide with 2.0 so that the image will rotate in the center of the image instead
        // of rotating in the right-most edge of the image
        AffineTransform rotation = AffineTransform.getTranslateInstance(x,y);
        rotation.rotate(Math.toRadians(degree), this.image.getWidth()/2.0, this.image.getHeight()/2.0);
        Graphics2D ge = (Graphics2D) g;
        ge.drawImage(this.image, rotation, null);
        if(deadAnimation)
            ge.drawImage(explosion.getCurrentFrame(), areaOfDeathX, areaOfDeathY,null);
    }

    public int getLifeLeft(){
        return life;
    }
}

