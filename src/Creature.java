public abstract class Creature implements Comparable {
    private String Name;
    private int HP;
    private int AC;
    private int STR;
    private int DEX;
    private int CON;
    private boolean armed;
    private String avatarPath;
    private int x;
    private int y;
    private int totalMovement = 5;
    private int currentMovement = 5;
    private int d20Roll = GameUtility.rollDice("1d20");

    //setters
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }


    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    public void setCurrentMovement(int movement) {
        this.currentMovement = movement;
    }


    //getters
    public String getName() {
        return Name;
    }

    public int getHP() {
        return HP;
    }

    public int getAc() {
        return AC;
    }


    public int getSTR() {
        return STR;
    }

    public int getDEX() {
        return DEX;
    }

    public int getCON() {
        return CON;
    }


    public int getD20Roll() {
        return d20Roll;
    }

    public boolean isarmed() {
        return armed;
    }

    public String getAvatarPath() {
        return this.avatarPath;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCurrentMovement() {
        return this.currentMovement;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;

        System.out.printf("%s moved to (%d, %d)\n", this.Name, x, y);
    }

    public void takeDamage(int damage) {
        if (HP - damage <= 0) {
            System.out.println("Hp is O. Dead");
            HP = 0;
        } else
            HP = HP - damage;
    }

    public void disarm(Creature rival) {
        int attackerD20Roll = GameUtility.rollDice("d20") + this.getSTR() - 5;
        int targetD20Roll = GameUtility.rollDice("d20") + rival.getSTR() - 5;
        if (attackerD20Roll > targetD20Roll) {
            System.out.println("Successfully disarmed the target for next 2 rounds");
            rival.setArmed(false);
        } else {
            System.out.println("Unsuccessful to disarm the target");
        }
    }

    public abstract void attack(Creature creature);

    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && Name.equals(((Creature) o).getName());
    }

    public int compareTo(Object o) {
        Creature p = (Creature) o;
        return Integer.compare(getHP(), p.getHP());
    }
}


