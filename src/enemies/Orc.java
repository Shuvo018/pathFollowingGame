package enemies;
import static helps.Constants.Enemies.ORC;
public class Orc extends Enemy{
    public Orc(float x, float y, int ID) {
        super(x, y, ID, ORC);
        health = 50;
    }
}
