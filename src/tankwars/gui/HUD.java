package tankwars.gui;

import tankwars.GameWorld;
import tankwars.brain.Controller;
import tankwars.objects.PlayerTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    private PlayerTank player1;
    private PlayerTank player2;
    private Controller c;
    private int wave = 0; // will animate the beginning of wave
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private BufferedImage heart = null;
    private static final float SCALE = (GameWorld.WIDTH / 8.0f) / 100.0f;

    public HUD(PlayerTank p1, PlayerTank p2, Controller c){
        this.player1 = p1;
        this.player2 = p2;
        this.c = c;
    }

    public void tick(){
        scoreP1 = (player1.getCountBulletsHit() * 50 + player1.getFinalHitCount() * 100);
        scoreP2 = (player2.getCountBulletsHit() * 50 + player2.getFinalHitCount() * 100);
    }

    public void draw(Graphics g){

        // an if/else statement where if both players lose all their lives
        // or it is last wave (enemy boss is dead) then it is game over
        // else then continue displaying everything else

        if((player1.getLifeLeft() == -1 && player2.getLifeLeft() == -1) || wave == 8){
            c.removeAllObjects();
            g.setFont(new Font("arial",1,36));
            g.setColor(Color.WHITE);
            g.drawString("Game Over!!", GameWorld.WIDTH/8 * 3, GameWorld.HEIGHT/4);
            g.setFont(new Font("arial",1,18));
            g.drawString("Player 1 score: " + scoreP1, GameWorld.WIDTH/4, GameWorld.HEIGHT/2);
            g.drawString("Player 2 score: " + scoreP2, GameWorld.WIDTH/2, GameWorld.HEIGHT/2);
        }
        else{
            // One set of health for player1 and other set for player 2
            g.setColor(Color.GRAY);
            g.fillRect(0,35, GameWorld.WIDTH/8,30);
            g.fillRect(GameWorld.WIDTH/8,35, GameWorld.WIDTH/8,30);
            g.fillRect(GameWorld.WIDTH/4 + 20,35, GameWorld.WIDTH/8,30);
            g.fillRect(GameWorld.WIDTH/8 * 3 + 20,35, GameWorld.WIDTH/8,30);

            // Fill in the health bar and the shield bar
            g.setColor(Color.GREEN);
            g.fillRect(0,35,(int)(player1.getHealth()*SCALE),30);
            g.fillRect(GameWorld.WIDTH/4 + 20,35,(int)(player2.getHealth()*SCALE),30);
            g.setColor(Color.BLUE);
            g.fillRect(GameWorld.WIDTH/8,35,(int)(player1.getShield()*SCALE),30);
            g.fillRect(GameWorld.WIDTH/8 * 3 + 20,35,(int)(player2.getShield()*SCALE),30);

            // make an outline of the bars
            g.setColor(Color.WHITE);
            g.drawRect(0,35, GameWorld.WIDTH/8,30);
            g.drawRect(GameWorld.WIDTH/8,35, GameWorld.WIDTH/8,30);
            g.drawRect(GameWorld.WIDTH/4 + 20,35, GameWorld.WIDTH/8,30);
            g.drawRect(GameWorld.WIDTH/8 * 3 + 20,35, GameWorld.WIDTH/8,30);

            // Display Lives left
            try{
                heart = ImageIO.read(HUD.class.getResourceAsStream("/Heart.png"));
                for(int i = 1; i < player1.getLifeLeft() + 1; i++){
                    // for player 1
                    g.drawImage(heart, heart.getWidth() * i + 20, 2, null);
                }
                for(int j = 1; j < player2.getLifeLeft() + 1; j++){
                    // for player 2
                    g.drawImage(heart, heart.getWidth() * j + GameWorld.WIDTH/4 + 40,2,null);
                }
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }

            // Display the text underneath the bars
            g.setColor(Color.BLACK);
            g.drawString((int)Math.ceil(player1.getHealth()) + "/" + 100 +" HP",0,77);
            g.drawString((int)Math.ceil(player1.getShield()) + "/" + 100 + " Shield", GameWorld.WIDTH/8,77);
            g.drawString((int)Math.ceil(player2.getHealth()) + "/" + 100 +" HP", GameWorld.WIDTH/4 + 20,77);
            g.drawString((int)Math.ceil(player2.getShield()) + "/" + 100 + " Shield", GameWorld.WIDTH/8 * 3 + 20,77);

            // Display the text above the bars
            g.setColor(Color.GREEN);
            g.drawString(player1.getId(), 0,12);
            g.drawString(player2.getId(), GameWorld.WIDTH/4 + 20,12);

            // Display the scores
            g.setColor(Color.CYAN);
            g.drawString("Score: " + scoreP1, GameWorld.WIDTH/16,90);
            g.drawString("Score: " + scoreP2, GameWorld.WIDTH/8 * 3 - 20,90);
            g.drawString("Wave: " + wave, GameWorld.WIDTH / 8 * 5, GameWorld.HEIGHT - 32);

            // Display the bullet count
            g.setColor(Color.red);
            g.drawString(player1.getId() + " bullet count: " + player1.getCount() + "/" + player1.getTotalCount(),
                    GameWorld.WIDTH/16, GameWorld.HEIGHT - 32);
            g.drawString(player2.getId() + " bullet count: " + player2.getCount() + "/" + player2.getTotalCount(),
                    GameWorld.WIDTH/8 * 3 - 20, GameWorld.HEIGHT - 32);
        }
    }

    public int getWave(){
        return wave;
    }

    public void setWave(int i){
        this.wave = i;
    }
}

