import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MonsterPanel extends JPanel implements ActionListener {
    JLabel SelectMonsters;
    JList monstersList;
    JList selectedMonsters;
    JButton selectButton;
    JButton StartButton;
    ActionListener mainPanelActionL;
    ArrayList<Monster> monsters = new ArrayList<Monster>();
    ArrayList<Monster> monstersInGame = new ArrayList<Monster>();



    public MonsterPanel(ActionListener mainPanelActionL) {
        this.setLayout(new FlowLayout());
        ConfigureMonsters();

        //Monster ListPanel
        GridBagConstraints constraints = new GridBagConstraints();
        JPanel MonsterListPanel = new JPanel();
        constraints.gridx=0;
        constraints.gridy=0;
        SelectMonsters = new JLabel("Select Monsters");
        MonsterListPanel.add(SelectMonsters, constraints);
        constraints.gridx=0;
        constraints.gridy=1;
        monstersList = new JList(monsters.toArray());
        monstersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        MonsterListPanel.add(monstersList,constraints);

        selectButton = new JButton("-->");
        selectButton.addActionListener(this);


        this.mainPanelActionL = mainPanelActionL;

        add(MonsterListPanel);
        add(selectButton);



    }

    public void ConfigureMonsters()
    {
        Scanner FILE = null;
        try {
            FILE = new Scanner(new File("src/monsters-1.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (FILE.hasNextLine()) {
            monsters.add(Monster.Monster(FILE.nextLine()));
        }
    }
    public ArrayList<Monster> getMonstersInGame()
    {
        return monstersInGame;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectButton)
        {
            selectButton.setEnabled(false);
            selectedMonsters = new JList(monstersList.getSelectedValuesList().toArray());
            selectedMonsters.setVisibleRowCount(monsters.size());
            selectedMonsters.setFixedCellWidth(150);
            selectedMonsters.setFixedCellHeight(15);



            StartButton = new JButton("Start");
            StartButton.setActionCommand("Start");
            StartButton.addActionListener(mainPanelActionL);
            this.repaint();
            this.revalidate();

            add(selectedMonsters);
            add(StartButton);

            for(Object m: monstersList.getSelectedValuesList())
            {
                System.out.println("monster" + m + "added" );
                monstersInGame.add((Monster) m);

            }
        }
    }
}
