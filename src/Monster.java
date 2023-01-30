public class Monster extends Creature {
    MonsterType monsterType;

    public Monster() {
    }

    public Monster(String Name, String monsterType, int HP, int AC, int STR, int DEX, int CON) {
        setName(Name);
        setMonsterType(monsterType);
        setAC(AC);
        setHP(HP);
        setSTR(STR);
        setDEX(DEX);
        setCON(CON);
        setAvatarPath("src/MonsterAvatar.png");
    }

    public static Monster Monster(String CSVline) {
        String[] token = CSVline.trim().split(",");
        String Name = token[0];
        String MonsterType = token[1];
        int HP = Integer.parseInt(token[2]);
        int AC = Integer.parseInt(token[3]);
        int STR = Integer.parseInt(token[4]);
        int DEX = Integer.parseInt(token[5]);
        int CON = Integer.parseInt(token[6]);
        return new Monster(Name, MonsterType, HP, AC, STR, DEX, CON);
    }


    public void setMonsterType(String monsterType) {
        this.monsterType = MonsterType.valueOf(monsterType);
    }


    public MonsterType getMonsterType() {
        return monsterType;
    }


    public int rollHit() {
        int roll = GameUtility.rollDice("1d20") + (getDEX() - 5);
        return roll;
    }

    @Override
    public void attack(Creature creature) {
        System.out.print(getName() + " attacks " + creature.getName());
        System.out.print("(" + creature.getAc() + " to hit)");
        if (rollHit() >= creature.getAc()) {
            System.out.println("....hit");
            int damage = GameUtility.rollDice("1d6") + (getSTR() - 5);
            System.out.println(creature.getName() + " takes " + damage + " points of damage.\n");
            creature.takeDamage(damage);
        } else
            System.out.println("....MISSED!!!");
    }

    @Override
    public String toString() {
        return String.format("Name: %s,Type: %s,  hp: %d, ac: %d, str: %d, dex: %d, " +
                "con: %d.", getName(), getMonsterType().toString(), getHP(), getAc(), getSTR(), getDEX(), getCON());
    }


}

enum MonsterType {
    HUMANOID,
    FIEND,
    DRAGON
}
