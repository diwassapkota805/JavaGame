import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadPanel {

    public static void loadPlayer(ArrayList<Player> players, PlayerPanel playerPanel, ArrayList<Player> playersInPpanel) {
        JFileChooser chooser = new JFileChooser();
        File parentDir = new File("src/Players");
        chooser.setCurrentDirectory(parentDir);
        if (parentDir.listFiles().length == 0) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "No file to load at this moment", null, JOptionPane.WARNING_MESSAGE);
        } else {
            int UserSelection = chooser.showOpenDialog(new JFrame());
            if (UserSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    Scanner S = new Scanner(new File(chooser.getSelectedFile().getAbsolutePath()));

                    Player player = Player.loadFromCsv(S.nextLine());
                    System.out.println("sent players info through load");

                    if (player != null) {
                        players.add(player);
                        playerPanel.updatePlayerPanel(player, playersInPpanel);
                    }


                } catch (IOException e) {
                    System.out.println("File not found");
                    e.printStackTrace();
                }
            }
        }


    }


}
