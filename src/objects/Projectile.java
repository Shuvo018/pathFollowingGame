package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private float xSpeed, ySpeed;
    private int id, projecttileType, dmg;
    private boolean active = true;

    public Projectile(float x, float y, float xSpeed, float ySpeed,int dmg, int id, int projecttileType) {
        pos = new Point2D.Float(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.id = id;
        this.projecttileType = projecttileType;
    }
    public void move(){
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public int getProjecttileType() {
        return projecttileType;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getDmg(){
        return dmg;
    }
}
