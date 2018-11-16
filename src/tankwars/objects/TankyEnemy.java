package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TankyEnemy extends Enemy {

    public TankyEnemy(BufferedImage img, float x, float y, String id, Controller c) {
        super(img, x, y, id, c);
        this.health = 200;
        this.maxHealth = health;
        this.rateOfFire = 100;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void collision() {
        super.collision();
    }

    @Override
    public void shoot() {
        super.shoot();
    }

    @Override
    public void isDead(PlayerTank p) {
        if(health <= 0.0){
            p.setFinalHitCount(p.getFinalHitCount() + 2);
            cont.removeObject(this);
        }
    }

    @Override
    public void followSecondPlayer() {
        super.followSecondPlayer();
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
        //this.health = GameWorld.clamp(this.health,0.0f,maxHealth);
    }

    @Override
    public void dealDamage(GameObject obj) {
        super.dealDamage(obj);
    }
}
