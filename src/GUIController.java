import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUIController implements ActionListener {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private EditPanel editPanel;
    private EditPanel editPanel1;
    private PlayerPanel playerPanel;
    private SavePanel savePanel;
    private EditCharacterPanel editCharacterPanel;
    private MonsterPanel monsterPanel;
    private GridControllerPanel gridControllerPanel;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> playersInPpanel = new ArrayList<>();

    private JMenuItem newItem;
    private JMenuItem saveItem;
    private JMenuItem loadItem;
    private JMenuItem exitItem;

    private JButton startGameButton;
    private JButton newCharButton;
    private JButton loadCharButton;
    private JButton saveCharButton;
    private JButton editCharButton;


    GUIController() {
        mainFrame = new JFrame("Phase 3");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 900);

        playerPanel = new PlayerPanel();


        ConfigureMenu();
        configureMainPanel();


        mainFrame.setVisible(true);
    }

    private void ConfigureMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        newItem = new JMenuItem("New Character");
        saveItem = new JMenuItem("Save Character");
        loadItem = new JMenuItem("Load Character");
        exitItem = new JMenuItem("Exit");

        //listeners
        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveItem.setEnabled(false);
        loadItem.addActionListener(this);
        exitItem.addActionListener(this);

        file.add(newItem);
        file.add(saveItem);
        file.add(loadItem);
        file.add(exitItem);

        menuBar.add(file);
        mainFrame.add(menuBar, BorderLayout.NORTH);


    }

    private void configureMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        startGameButton = new JButton("Start Game");
        newCharButton = new JButton("New Character");
        loadCharButton = new JButton("Load Character");
        saveCharButton = new JButton("Save Character");
        saveCharButton.setEnabled(false);
        editCharButton = new JButton("Edit Character");
        editCharButton.setEnabled(false);

        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newCharButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadCharButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveCharButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editCharButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startGameButton.addActionListener(this);
        newCharButton.addActionListener(this);
        loadCharButton.addActionListener(this);
        saveCharButton.addActionListener(this);
        editCharButton.addActionListener(this);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(playerPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(startGameButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(newCharButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(loadCharButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(loadCharButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(saveCharButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(editCharButton);

        mainFrame.add(mainPanel, BorderLayout.CENTER);

    }

    private void openPanel(JPanel Panel) {
        if (!Panel.isShowing()) {
            mainFrame.remove(mainPanel);
            mainFrame.add(Panel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }


    private void closePanel(JPanel Panel) {


        if (Panel instanceof EditPanel) {
            if (Panel.equals(editPanel))
                editPanel.clearField();
            else if (Panel.equals(editPanel1))
                editPanel1.clearField();
        }
        mainFrame.remove(Panel);
        mainFrame.add(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();

    }


    private void EnableSaveButtons() {
        saveItem.setEnabled(true);
        saveCharButton.setEnabled(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action == "Start Game") {
            if (players.size() == 0) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Create a Player First", null, JOptionPane.WARNING_MESSAGE);
            } else {
                monsterPanel = new MonsterPanel(new monsterPanelAL());
                openPanel(monsterPanel);
            }

        } else if (action == "New Character") {
            editPanel = new EditPanel(new EditPanelListener());
            openPanel(editPanel);

        } else if (action == "Load Character") {
            LoadPanel.loadPlayer(players, playerPanel, playersInPpanel);

            //if there is are players in the memory enable save, edit buttons
            if (players.size() > 0) {
                EnableSaveButtons();
                editCharButton.setEnabled(true);
            }

            mainFrame.revalidate();
            mainFrame.repaint();//repaints the mainPanel after loading a player to the playerPanel
        } else if (action == "Save Character") {
            if (players.size() < 1)
                JOptionPane.showMessageDialog(new JFrame(),
                        "no player to save", null, JOptionPane.WARNING_MESSAGE);
            else {
                savePanel = new SavePanel(new SavePanelActionListener(), players);
                openPanel(savePanel);
            }

        } else if (action == "Edit Character") {
            editCharacterPanel = new EditCharacterPanel(players, new editCharPanelListener());
            openPanel(editCharacterPanel);
        } else if (action == "Exit") {
            JFrame yesNoFrame = new JFrame();
            int choice = JOptionPane.showConfirmDialog(yesNoFrame,
                    "You might have some unsaved Players. " +
                            "Do you want to exit without saving??", null, JOptionPane.YES_NO_OPTION);

            if (choice == 1) {
                savePanel = new SavePanel(new SavePanelActionListener(), players);
                openPanel(savePanel);
            } else
                mainFrame.dispose();

        } else
            mainFrame.dispose();
    }


    private class EditPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent a) {
            String action1 = a.getActionCommand();
            switch (action1) {
                case "EditPanel.Confirm":
                    int STR = Integer.parseInt(editPanel.getPlayerStr());
                    int DEX = Integer.parseInt(editPanel.getPlayerDex());
                    int CON = Integer.parseInt(editPanel.getPlayerCon());
                    System.out.println(STR + DEX + CON);

                    if ((STR + DEX + CON) > 15) {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "total Stat point cannot be > 15", null, JOptionPane.WARNING_MESSAGE);
                    } else {
                        Player p = new Player(editPanel.getPlayerName(), 50, STR, DEX, CON, editPanel.getPlayerWeapon(), editPanel.getAvatarPath());
                        players.add(p);
                        System.out.printf("Player %s Created\n", p);

                        //if there is are players in the memory enable save, edit buttons
                        if (players.size() > 0) {
                            EnableSaveButtons();
                            editCharButton.setEnabled(true);
                        }


                        try {
                            if (playersInPpanel.size() <= 4)
                                playerPanel.updatePlayerPanel(p, playersInPpanel);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        closePanel(editPanel);
                    }
                    break;

                case "EditPanel.Cancel":
                    closePanel(editPanel);
                    break;
            }
        }
    }

    private class SavePanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();
            switch (event) {
                case "SavePanel.confirm":
                    closePanel(savePanel);

                    JFileChooser files = new JFileChooser();
                    File PlayerFolder = new File("src/Players");
                    files.setCurrentDirectory(PlayerFolder);

                    int userSelection = files.showSaveDialog(new JFrame());

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        try {
                            FileWriter writer = new FileWriter(files.getSelectedFile());
                            writer.write(savePanel.getPlayerInfo());
                            writer.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                    break;

                case "SavePanel.cancel":
                    closePanel(savePanel);
                    break;
            }
        }
    }

    private class editCharPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if (action == "EditCharacter.Select") {
                mainFrame.remove(editCharacterPanel);
                editPanel1 = new EditPanel(new editPanel1Listener());

                String name = editCharacterPanel.getPlayer().getName();
                int STR = editCharacterPanel.getPlayer().getSTR();
                int DEX = editCharacterPanel.getPlayer().getDEX();
                int CON = editCharacterPanel.getPlayer().getCON();
                Weapon weapon = editCharacterPanel.getPlayer().getWeapon();
                String path = editCharacterPanel.getPlayer().getAvatarPath();

                editPanel1.setNameTextField(name);
                editPanel1.setStrTextField(String.valueOf(STR));
                editPanel1.setDexTextField(String.valueOf(DEX));
                editPanel1.setConTextField(String.valueOf(CON));
                editPanel1.setSelectetdWeapon(weapon.toString());
                editPanel1.setSelectedAvater(path);


                openPanel(editPanel1);
            } else if (action == "EditCharacter.Cancel") {
                closePanel(editCharacterPanel);
            }
        }
    }

    private class editPanel1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String action1 = e.getActionCommand();
            if (action1 == "EditPanel.Confirm") {
                String name = editPanel1.getPlayerName();
                int STR = Integer.parseInt(editPanel1.getPlayerStr());
                int DEX = Integer.parseInt(editPanel1.getPlayerDex());
                int CON = Integer.parseInt(editPanel1.getPlayerCon());
                Weapon weapon = editPanel1.getPlayerWeapon();
                String path = editPanel1.getAvatarPath();

                editCharacterPanel.getPlayer().setName(name);
                editCharacterPanel.getPlayer().setSTR(STR);
                editCharacterPanel.getPlayer().setDEX(DEX);
                editCharacterPanel.getPlayer().setCON(CON);
                editCharacterPanel.getPlayer().setWeapon(weapon);
                editCharacterPanel.getPlayer().setAvatarPath(path);


                closePanel(editPanel1);
            }
            if (action1 == "EditPanel.Cancel") {
                closePanel(editPanel1);
            }
        }
    }

    private class monsterPanelAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Start") {
                ArrayList<Creature> creatures = new ArrayList<>();
                creatures.addAll(playersInPpanel);
                creatures.addAll(monsterPanel.getMonstersInGame());

                closePanel(monsterPanel);
                gridControllerPanel = new GridControllerPanel(creatures);
                openPanel(gridControllerPanel);
            }
        }
    }
}
