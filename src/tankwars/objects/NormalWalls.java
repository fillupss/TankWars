package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalWalls extends Obstacles {

    public NormalWalls(BufferedImage img, float x, float y, String id, Controller c){
        super(img,x,y,id,c);
        this.health = 100;
    }

    public void tick(){
        // Does not move
        x += 0;
        y += 0;
    }

    public void draw(Graphics g){
        g.drawImage(image,(int)x,(int)y,image.getWidth(),image.getHeight(), null);
    }

}