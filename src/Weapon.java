import java.util.StringTokenizer;

public class Weapon {
    private String Name;
    private String DiceType;
    private int Bonus;

    //Constructors

    /**
     * Default Constructor
     */
    public Weapon() {
    }

    /**
     * Constructor to create a Weapon
     *
     * @param Name     Weapon Name
     * @param DiceType Dice Type of the weapon
     * @param Bonus    Bonus point associated with the Weapon
     */
    public Weapon(String Name, String DiceType, int Bonus) {
        this.Name = Name;
        this.DiceType = DiceType;
        this.Bonus = Bonus;
    }

    /**
     * Constructor that tokenizes a CSV line and initializes the fields
     *
     * @param CSVline CSV line from Weapon File
     */
    public static Weapon weapon(String CSVline) {
        StringTokenizer string = new StringTokenizer(CSVline, ",");
        String Name = string.nextToken();
        String DiceType = string.nextToken();
        int Bonus = Integer.parseInt(string.nextToken());
        return new Weapon(Name, DiceType, Bonus);
    }

    //Setters

    /**
     * Sets the Name of a Weapon
     *
     * @param Name Name of a Weapon
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Sets the Dice Type of Weapon
     *
     * @param DiceType Dice Type associated with the Weapon
     */
    public void setDiceType(String DiceType) {
        this.DiceType = DiceType;
    }

    /**
     * Sets the Bonus of Weapon
     *
     * @param BONUS Bonus Point associated with the Weapon
     */
    public void setBonus(int BONUS) {
        this.Bonus = BONUS;
    }

    //getters
    public String getName() {
        return Name;
    }

    public String getDiceType() {
        return DiceType;
    }

    public int getBonus() {
        return Bonus;
    }

    /**
     * Rolls Dice to calculate damage that a weapon can cause
     *
     * @return How much damage the weapon can cause
     */
    /*public int rollDamage() {

        return GameUtility.rollDice(DiceType + "+" + Bonus);//d6+4
    }*/

    /**
     * Overrides the toString method
     *
     * @return Returns an information about the weapon
     */
    @Override
    public String toString() {
        return Name + "(" + DiceType + "+" + Bonus + ")";
    }
}
