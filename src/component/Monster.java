package component;

import javafx.scene.image.Image;

/**
 * This class contain all attribute for monster
 * and helper method
 *
 * @author Pakanon Pantisawat
 */

public class Monster {

    private int id;
    private int gold;
    private String name;
    private int level;
    private int exp;
    private Status status;
    private Type type;
    private Image[] image;
    private String[] skill = new String[2];

    public Monster(String name, Type type) {
        this(name, 0, type, 0);
    }

    public Monster(String name, int exp, Type type, int gold) {
        this.id = hashCode();
        this.name = name;
        this.level = 1;
        this.exp = exp;
        this.type = type;
        this.gold = gold;
        setLevel();
        setStatus();
        setImage();
    }

    public Monster(String name, int exp, int type) {
        this(name, exp, Type.FIRE, 0);
        this.setType(type);
    }

    public Monster(int id, String name, int exp, Type type, int gold) {
        this(name, exp, type, gold);
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = ((Monster) o);
        return this.getId() == monster.getId();
    }

    // Stand alone SETTER

    private boolean setLevel() {
        setSkills();
        if (exp < getCriteria()) return false;
        while (exp >= getCriteria()) {
            exp -= getCriteria();
            level++;
        }
        return true;
    }

    private void setSkills() {
        switch (type) {
            case FIRE:
                skill[0] = "Fire rain";
                skill[1] = "Lava rain";
                break;
            case WATER:
                skill[0] = "Waterfall";
                skill[1] = "Waterstorm";
                break;
            case WIND:
                skill[0] = "Tornado";
                skill[1] = "Mega Tonado";
                break;
        }
    }

    private void setStatus() {
        this.status = new Status(this.level, this.type);
    }

    private void setImage() {
        this.image = new Image[2];
        String path = "";
        String pathFlip = "";
        switch (type) {
            case FIRE:
                path = "component/rel/monster/fire.png";
                pathFlip = "component/rel/monster/FireFlip.png";
                break;
            case WATER:
                path = "component/rel/monster/water.png";
                pathFlip = "component/rel/monster/WaterFlip.png";
                break;
            case WIND:
                path = "component/rel/monster/wind.png";
                pathFlip = "component/rel/monster/WindFlip.png";
                break;
        }
        this.image[0] = new Image(path);
        this.image[1] = new Image(pathFlip);
    }

    void setLevel(int level) {
        this.level = level;
        setStatus();
    }

    public void reset() {
        this.status = new Status(this.level, this.type);
    }


    // GETTER
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public Image getImage(boolean isFlip) {
        if (isFlip) return image[1];
        else return image[0];
    }

    public String[] getSkill() {
        return skill;
    }

    public int getCriteria() {
        return (int)Math.round(Math.pow(3 * level + 70, 2) / 9);
    }

    public int getId() {
        return id;
    }

    public int getGold() {
        return gold;
    }

    // SETTER

    public void setType(int type) {
        switch (type) {
            case 1:
                setType(Type.FIRE);
                break;
            case 2:
                setType(Type.WATER);
                break;
            case 3:
                setType(Type.WIND);
                break;
        }
    }

    private void setType(Type type) {
        this.type = type;
        setStatus();
        setImage();
    }

    public boolean plusExp(int exp) {
        this.exp += exp;
        return setLevel();
    }

    public void increaseGold(int factor) {
        this.gold += factor;
    }

    void setGold(int gold) {
        this.gold = gold;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ACTION PERFORM
    public int attackPerform(Monster opponent) {
        double multiplier = 1.0;
        double damageBonus = new java.util.Random().nextInt(10);
        if (opponent.getType().equals(this.type.getWeakType())) multiplier = 1.2;
        double damage = Math.floor(this.status.getAtk() - opponent.getStatus().getDef() * multiplier + damageBonus);
        if (opponent.getStatus().getHp() - damage <= 0) damage = opponent.getStatus().getHp();
        return (int) damage;
    }

    public int skillPerform(Monster opponent, int skill) {
        double multiplier = 2.0;
        if (this.type.equals(opponent.getType())) multiplier = 1.0;
        else if (opponent.getType().equals(this.type.getWeakType())) multiplier = 2.8;

        int fail = new java.util.Random().nextInt(99) + 1;
        if (fail > 90) return 0;

        if (skill == 2) {
            multiplier += 0.85;
        }
        double damage = Math.floor((this.status.getAtk() - opponent.getStatus().getDef()) * multiplier);
        if (opponent.getStatus().getHp() - damage <= 0) damage = opponent.getStatus().getHp();

        return (int) damage;
    }

    public boolean isDead() {
        return this.status.getHp() <= 0;
    }

    public int botPerform(Monster player) {
        int perform = new java.util.Random().nextInt(9) + 1;
        double damage;
        if (perform < 5) damage = attackPerform(player);
        else {
            if (this.level < 7) damage = skillPerform(player, 1);
            else damage = skillPerform(player, perform % 2);
        }
        return (int) damage;
    }
}