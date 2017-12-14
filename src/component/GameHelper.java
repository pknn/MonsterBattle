package component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * This class is helper class for the game.
 * All the stuff about files will be manage here.
 *
 * @author Pakanon Pantisawat
 */
public class GameHelper {
    private final String path = "gameSave.save";

    private List<Monster> monsterList = new ArrayList<>();

    public void loadSave() {
        monsterList = new ArrayList<>();
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (Exception e) {
            System.err.println("FILE NOT FOUND");
            return;
        }
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            int id = Integer.parseInt(line.substring(1, line.length()));
            String name = sc.nextLine();
            int exp = Integer.parseInt(sc.nextLine());
            String typeLine = sc.nextLine();
            Type type;
            switch (typeLine) {
                case "Fire":
                    type = Type.FIRE;
                    break;
                case "Water":
                    type = Type.WATER;
                    break;
                default:
                    type = Type.WIND;
                    break;
            }
            int gold = Integer.parseInt(sc.nextLine());
            monsterList.add(new Monster(id, name, exp, type, gold));
        }
    }

    public void addSave(Monster saveMonster) {
        int index = monsterList.indexOf(saveMonster);
        if (index == -1) {
            monsterList.add(saveMonster);
        } else {
            monsterList.set(index, saveMonster);
        }
        saveGame();
    }

    public void deleteSave(Monster monster) {
        int index = monsterList.indexOf(monster);
        if (index != -1) {
            monsterList.remove(monster);
            saveGame();
        }
    }

    private void saveGame() {
        FileWriter fw;
        try {
            fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw);
            for (Monster monster : monsterList) {
                pw.println("#" + monster.getId());
                pw.println(monster.getName());
                pw.println(monster.getExp());
                pw.println(monster.getType());
                pw.println(monster.getGold());
                pw.println();
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("CANNOT WRITE FILE");
        }
    }

    public Monster[] getMonsters() {
        return monsterList.toArray(new Monster[monsterList.size()]);
    }

    public Monster[] getOpponent(int baseLevel) {
        java.util.Random random = new java.util.Random();
        Monster[] monsters = new Monster[3];
        for (int i = 0; i < 3; i++) {
            int lv = baseLevel + random.nextInt(27) % 3;
            int reward = 13 * lv + 20 + random.nextInt(20);
            monsters[i] = new Monster(getMonsterName(), 0, random.nextInt(18) % 3 + 1);
            while (monsters[i].getLevel() < lv) {
                monsters[i].plusExp(monsters[i].getCriteria());
            }
        }
        return monsters;
    }

    private String getMonsterName() {
        int line = new java.util.Random().nextInt(237) + 1;
        try {
            FileInputStream fis = new FileInputStream("monsterList.save");
            Scanner sc = new Scanner(fis);
            for (int count = 0; count != line; count++) sc.nextLine();
            return sc.nextLine();
        } catch (FileNotFoundException fe) {
            System.err.println("Monster list Not found");
        }
        return null;
    }
}
