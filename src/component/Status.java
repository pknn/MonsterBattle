package component;

/**
 * Status class is a class for status object
 * Contain all the status for monster and helper method.
 *
 * @author Pakanon Pantisawat
 */
public class Status {

    private double atk;
    private double def;
    private double spd;
    private double hp;
    private double maxHp;

    Status (int level, Type type) {
        atk = 15 * (level + 5);
        def = 10 * (1.2 * level + 2);
        spd = 10 * level;
        hp = 10 * (level + 85);
        maxHp = hp;

        switch (type) {
            case FIRE:
                atk += 20;
                break;
            case WATER:
                def += 20;
                break;
            case WIND:
                spd += 20;
                break;
        }
    }

    public void deHp(int factor) {
        this.hp -= factor;
    }

    public double getAtk() {
        return atk;
    }

    public double getDef() {
        return def;
    }

    public double getSpd() {
        return spd;
    }

    public double getHp() {
        return hp;
    }

    public double getMaxHp() {
        return maxHp;
    }
}

