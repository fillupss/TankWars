package tankwars.brain;

import tankwars.GameWorld;
import tankwars.gui.HUD;
import tankwars.objects.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Spawner {

    Random r = new Random();

    private HUD hud;
    private Controller handler;
    private BufferedImage basicEnemy = null;
    private BufferedImage tankyEnemy = null;
    private BufferedImage enemyBoss = null;
    private BufferedImage[] powerUps = new BufferedImage[5];

    public Spawner(HUD hud, Controller handler){
        this.hud = hud;
        this.handler = handler;
    }

    public void tick(){
        try{
            basicEnemy = ImageIO.read(Spawner.class.getResourceAsStream("/Enemy.png"));
            tankyEnemy = ImageIO.read(Spawner.class.getResourceAsStream("/TankyEnemy.png"));
            enemyBoss = ImageIO.read(Spawner.class.getResourceAsStream("/EnemyBoss.png"));
            powerUps[0] = ImageIO.read(Spawner.class.getResourceAsStream("/Blaster.png"));
            powerUps[1] = ImageIO.read(Spawner.class.getResourceAsStream("/BulletBill.png"));
            powerUps[2] = ImageIO.read(Spawner.class.getResourceAsStream("/HealthUp.png"));
            powerUps[3] = ImageIO.read(Spawner.class.getResourceAsStream("/TripleBullet.png"));
            powerUps[4] = ImageIO.read(Spawner.class.getResourceAsStream("/Shield.png"));

            if(!handler.checkIfMoreEnemies() && hud.getWave() < 8 && handler.checkIfMorePlayers()){
                hud.setWave(hud.getWave() + 1);
            }
            if(hud.getWave() == 1 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new Enemy(basicEnemy,r.nextInt(GameWorld.WIDTH / 2) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new Enemy(basicEnemy,r.nextInt(GameWorld.WIDTH / 2) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
            }
            if(hud.getWave() == 2 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
            }
            if(hud.getWave() == 3 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new Enemy(basicEnemy,r.nextInt(GameWorld.WIDTH / 2) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                choosePowerUps();
            }
            if(hud.getWave() == 4 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new Enemy(basicEnemy,r.nextInt(GameWorld.WIDTH / 2) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new Enemy(basicEnemy,r.nextInt(GameWorld.WIDTH / 2) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
            }
            if(hud.getWave() == 5 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                choosePowerUps();
            }
            if(hud.getWave() == 6 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new TankyEnemy(tankyEnemy,r.nextInt(GameWorld.WIDTH / 2 - 32) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
                handler.addObject(new Enemy(basicEnemy,r.nextInt(GameWorld.WIDTH / 2) + GameWorld.WIDTH/2,
                        r.nextInt(GameWorld.HEIGHT - 64) + 32,"Enemy", handler));
            }
            if(hud.getWave() == 7 && !handler.checkIfMoreEnemies() && handler.checkIfMorePlayers()){
                handler.addObject(new EnemyBoss(enemyBoss, GameWorld.WIDTH + 10, 0,"Enemy", handler));
                choosePowerUps();
            }

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void choosePowerUps(){
        int random = r.nextInt(5);
        int random2 = r.nextInt(5);
        if(random == 0){
            handler.addObject(new BlastPowerUp(powerUps[0], 300,300,"PowerUp", handler));
        }
        else if(random == 1){
            handler.addObject(new MissilePowerUp(powerUps[1], 300,300,"PowerUp", handler));
        }
        else if(random == 2){
            handler.addObject(new ExtraHealthPowerUp(powerUps[2], 300,300,"PowerUp", handler));
        }
        else if (random == 3){
            handler.addObject(new TripleShotPowerUp(powerUps[3],300,300,"PowerUp", handler));
        }
        else{
            handler.addObject(new ExtraShieldPowerUp(powerUps[4],300,300,"PowerUp", handler));
        }
        if(random2 == 0){
            handler.addObject(new BlastPowerUp(powerUps[0], 200,100,"PowerUp", handler));
        }
        else if(random2 == 1){
            handler.addObject(new MissilePowerUp(powerUps[1], 200,100,"PowerUp", handler));
        }
        else if(random2 == 2){
            handler.addObject(new ExtraHealthPowerUp(powerUps[2], 200,100,"PowerUp", handler));
        }
        else if (random2 == 3){
            handler.addObject(new TripleShotPowerUp(powerUps[3],200,100,"PowerUp", handler));
        }
        else{
            handler.addObject(new ExtraShieldPowerUp(powerUps[4],200,100,"PowerUp", handler));
        }
    }
}
