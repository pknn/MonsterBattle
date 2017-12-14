package component;

/**
 * Enumerator "Type" for monster
 * This enum contains monster type and method to return weak type of any type.
 *
 * @author Pakanon Pantisawat
 */
public enum Type {
    FIRE,
    WATER,
    WIND;

    private Type weakType;

    static {
        FIRE.weakType = WATER;
        WATER.weakType = WIND;
        WIND.weakType = FIRE;
    }

    public Type getWeakType() {
        return weakType;
    }

    @Override
    public String toString() {
        String string = super.toString();
        return string.substring(0, 1) + string.substring(1, string.length()).toLowerCase();
    }
}
