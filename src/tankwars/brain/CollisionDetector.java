package tankwars.brain;

import tankwars.objects.*;

import java.util.ArrayList;

public class CollisionDetector {

    public static boolean PlayerVsPlayer(PlayerTank p, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("Player2") && p.getId().equals("Player1")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    return true;
                }
            }
            if(tempObj.getId().equals("Player1") && p.getId().equals("Player2")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean PlayerVsWall(PlayerTank p, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("Wall") && p.getId().equals("Player1")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    Obstacles w = (Obstacles) tempObj;
                    w.setHealth(w.getHealth() - 1);
                    w.isDead();
                    return true;
                }
            }
            if(tempObj.getId().equals("Wall") && p.getId().equals("Player2")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    Obstacles w = (Obstacles) tempObj;
                    w.setHealth(w.getHealth() - 1);
                    w.isDead();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean EnemyVsPlayer(Enemy e, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().contains("Player") && !tempObj.getId().contains("Bullet") && e.getId().equals("Enemy")){
                if(e.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    e.dealDamage(tempObj);
                    PlayerTank p = (PlayerTank) tempObj;
                    p.isDead();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean PlayerVsEnemyBullet(PlayerTank p, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("EnemyBullet") && p.getId().contains("Player")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    Bullet b = (Bullet) tempObj;
                    b.dealDamage(p);
                    objs.remove(tempObj);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean BulletVsWall(Bullet b, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("Wall") && b.getId().contains("Bullet")){
                if(b.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    b.dealDamage(tempObj);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean EnemyVsWall(Enemy e, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("Wall") && e.getId().equals("Enemy")){
                if(e.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    e.dealDamage(tempObj);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean PlayerBulletVsEnemy(Bullet b, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("Enemy") && b.getId().equals("PlayerBullet1")){
                if(b.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    for(int j = 0; j < objs.size(); j++){
                        if(objs.get(j).getId().equals("Player1")){
                            PlayerTank temp = (PlayerTank) objs.get(j);
                            temp.setCountBulletsHit(temp.getCountBulletsHit() + 1);
                            Enemy something = (Enemy)tempObj;
                            b.dealDamage(tempObj);
                            something.isDead(temp);
                        }
                    }
                    return true;
                }
            }
            if(tempObj.getId().equals("Enemy") && b.getId().equals("PlayerBullet2")){
                if(b.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    for(int j = 0; j < objs.size(); j++){
                        if(objs.get(j).getId().equals("Player2")){
                            PlayerTank temp = (PlayerTank) objs.get(j);
                            temp.setCountBulletsHit(temp.getCountBulletsHit() + 1);
                            Enemy something = (Enemy)tempObj;
                            b.dealDamage(tempObj);
                            something.isDead(temp);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean PlayerBulletVsEnemyBullet(Bullet b, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("EnemyBullet") && b.getId().contains("PlayerBullet")){
                if(b.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    objs.remove(tempObj);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean PlayerVsPowerUp(PlayerTank p, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("PowerUp") && p.getId().equals("Player1")){
                PowerUp temp = (PowerUp) tempObj;
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    temp.function(p);
                    objs.remove(tempObj);
                    return true;
                }
            }
            if(tempObj.getId().equals("PowerUp") && p.getId().equals("Player2")){
                PowerUp temp = (PowerUp) tempObj;
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    temp.function(p);
                    objs.remove(tempObj);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean PlayerVsPlayerBullet(PlayerTank p, ArrayList<GameObject> objs){
        for(int i = 0; i < objs.size(); i++){
            GameObject tempObj = objs.get(i);
            if(tempObj.getId().equals("PlayerBullet2") && p.getId().equals("Player1")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    // for friendly fire
                    //Bullet b = (Bullet) tempObj;
                    //b.dealDamage(p);
                    objs.remove(tempObj);
                    return true;
                }
            }
            if(tempObj.getId().equals("PlayerBullet1") && p.getId().equals("Player2")){
                if(p.getCollisionBounds().intersects(tempObj.getCollisionBounds())){
                    // for friendly fire
                    //Bullet b = (Bullet) tempObj;
                    //b.dealDamage(p);
                    objs.remove(tempObj);
                    return true;
                }
            }
        }
        return false;
    }


}