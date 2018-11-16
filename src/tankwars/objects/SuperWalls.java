package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperWalls extends Obstacles {

    private float maxHealth;

    public SuperWalls(BufferedImage img, float x, float y, String id, Controller c){
        super(img,x,y,id,c);
        this.health = 300;
        maxHealth = health;
    }

    public void tick(){
        // Does not move
        x += 0;
        y += 0;
    }

    public void draw(Graphics g){
        g.drawImage(image,(int)x,(int)y,image.getWidth(),image.getHeight(), null);
    }

    public void setHealth(float health){
        // basically will never lose health unless some object does more then 95 damage but
        // in this case nothing does more then 95 damage
        this.health = maxHealth;
    }


}
