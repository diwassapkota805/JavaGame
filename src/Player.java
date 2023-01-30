import javax.swing.*;

public class Player extends Creature{

    private int HP = 50;
    private Weapon weapon;
    private String weaponName;
    private String DiceType;
    private int Bonus;




    public Player(String Name, int HP,  int STR, int DEX, int CON, Weapon weapon, String avatarPath)
    {
        setName(Name);
        setSTR(STR);
        setDEX(DEX);
        setCON(CON);
        setHP(50);
        this.weapon = weapon;
        setAvatarPath(avatarPath);
    }
    public static Player loadFromCsv(String CVSline) {
        try {

            String[] token = CVSline.trim().split(",");
            if (token.length < 9) {
                throw new CsvReadException(CVSline);
            }
            String Name = token[0];
            int HP = Integer.parseInt(token[1]);
            int STR = Integer.parseInt(token[2]);
            int DEX = Integer.parseInt(token[3]);
            int CON = Integer.parseInt(token[4]);
            String AvatarPath = token[5];
            String WeaponName = token[6];
            String DiceType = token[7];
            int Bonus = Integer.parseInt(token[8]);

            return new Player(Name, HP, STR, DEX, CON, new Weapon(WeaponName,DiceType,Bonus), AvatarPath);
        } catch (Exception csv) {
            //csv.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    "Player couldn't be loaded",null, JOptionPane.WARNING_MESSAGE);
            CsvReadException CSV = new CsvReadException(CVSline);
            System.out.println(csv);
            return null;
        }
    }




    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }



    @Override
    public void attack(Creature creature) {

    }

    public Weapon getWeapon(){
        return weapon;
    }


    public String toString(){
        return String.format("%s,%d,%d,%d,%d,%s,%s,%s,%d",
                getName(), getHP(), getSTR(), getDEX(),getCON(),getAvatarPath(), weapon.getName(), weapon.getDiceType(), weapon.getBonus());
    }
}
