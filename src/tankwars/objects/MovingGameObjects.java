package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MovingGameObjects extends GameObject {

    protected float health;
    public MovingGameObjects(BufferedImage img, float x, float y, String id, Controller c){
        super(img,x,y,id,c);
    }

    public abstract void tick();
    public abstract void draw(Graphics g);

    public abstract void shoot();
    public abstract void isDead();

    public void setHealth(float health){
        this.health = health;
        // fix a small bug on the HUD
        if(this.health < 1){
            this.health = 0;
        }
        else if(this.health > 100){
            this.health = 100;
        }
    }
    public float getHealth(){
        return health;
    }

}