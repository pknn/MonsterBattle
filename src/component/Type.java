package component;

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

    public static void main(String[] args) {
        System.out.println(Type.FIRE.weakType);
    }

    @Override
    public String toString() {
        String string = super.toString();
        return string.substring(0, 1) + string.substring(1, string.length()).toLowerCase();
    }
}
