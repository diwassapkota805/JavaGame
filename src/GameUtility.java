import java.util.Random;

public class GameUtility {
    public static int rollDice(String input) {
        int DICETYPE, RANDOM;
        int NUMDICE = 1;
        int CONSTANT = 0;
        int total = 0;
        //2d6  +10
        if (input.contains("+")) {
            String subString1 = input.substring(0, input.indexOf('d'));
            String subString2 = input.substring(input.indexOf('d') + 1, input.indexOf('+'));
            String subString3 = input.substring(input.indexOf('+') + 1, input.length());
            if (subString1.length() >= 1)
                NUMDICE = Integer.parseInt(subString1);
            DICETYPE = Integer.parseInt(subString2);
            if (subString3.length() >= 1)
                CONSTANT = Integer.parseInt(subString3);
        } else {
            String subString1 = input.substring(0, input.indexOf('d'));
            String subString2 = input.substring(input.indexOf('d') + 1, input.length());
            if (subString1.length() >= 1)
                NUMDICE = Integer.parseInt(subString1);
            DICETYPE = Integer.parseInt(subString2);
        }


        Random random = new Random();
        RANDOM = random.nextInt(1, DICETYPE + 1);

        for (int a = 0; a < NUMDICE; a++)
            total += RANDOM;

        return total + CONSTANT;
    }
}
