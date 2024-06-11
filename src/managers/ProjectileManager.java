package managers;

import enemies.Enemy;
import helps.Constants;
import helps.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helps.Constants.Towers.*;
import static helps.Constants.Projectiles.*;

public class ProjectileManager {
    private Playing playing;
    private int proj_id = 0;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_img;
    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }
    private void importImgs(){
        proj_img = new BufferedImage[3];
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        for(int i=0; i<3; i++){
            proj_img[i] = atlas.getSubimage((7+i)*32, 32, 32, 32);
        }
    }
    public void newProjectile(Tower t, Enemy e){
        int type = getProjType(t);

        int xDist = (int) Math.abs(t.getX() - e.getX());
        int yDist = (int) Math.abs(t.getY() - e.getY());
        int totDist = xDist + yDist;

        float xPer = (float) xDist/totDist;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;

        if(t.getX() > e.getX()){
            xSpeed *= -1;
        }
        if(t.getY() > e.getY()){
            ySpeed *= -1;
        }
        projectiles.add(new Projectile(t.getX()+16, t.getY()+16, xSpeed, ySpeed, t.getDmg(), proj_id++, type));
    }

    public void update(){
        for (Projectile p : projectiles){
            if(p.isActive()){
                p.move();
                if(isProjHittingEnemy(p)){
                    p.setActive(false);
                }else{

                }
            }
        }
    }

    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e: playing.getEnemyManager().getEnemies()){
            if(e.getBounds().contains(p.getPos())){
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g){
        for (Projectile p: projectiles){
            if (p.isActive())
                g.drawImage(proj_img[p.getProjecttileType()], (int)p.getPos().x,(int)p.getPos().y, null);
        }
    }
    private int getProjType(Tower t) {
        switch (t.getTowerType()){
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }
        return 0;
    }
}
