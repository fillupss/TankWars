package tankwars.brain;

import tankwars.objects.GameObject;

import java.util.ArrayList;
import java.awt.Graphics;
public class Controller {

    ArrayList<GameObject> handler = new ArrayList<>();

    public void tick(){
        for(int i = 0; i < handler.size(); i++){
            GameObject temp = handler.get(i);
            temp.tick();
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < handler.size(); i++){
            GameObject temp = handler.get(i);
            temp.draw(g);
        }
    }


    public void addObject(GameObject obj){
        this.handler.add(obj);
    }

    public void removeObject(GameObject obj){
        this.handler.remove(obj);
    }

    public ArrayList<GameObject> getArray(){
        return handler;
    }

    // check if theres anymore enemies so the players can advance to next wave
    // true if theres still an enemy
    // false if no more enemies
    public boolean checkIfMoreEnemies(){
        for(int i = 0; i < handler.size(); i++){
            if(handler.get(i).getId().equals("Enemy")){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfMorePlayers(){
        for(int i = 0; i < handler.size(); i++){
            if(handler.get(i).getId().contains("Player") && !handler.get(i).getId().contains("Bullet")){
                return true;
            }
        }
        return false;
    }

    // Game Over or Game finished
    public void removeAllObjects(){
        handler.clear();

    }
}

