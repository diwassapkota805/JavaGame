import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCharacterPanel extends JPanel implements ActionListener {
    private JComboBox<Player> PlayerOption;
    private JLabel selectPlayer;
    private JButton selectButton;
    private JButton cancelButton;
    private Player player;

    public EditCharacterPanel(ArrayList<Player> players, ActionListener actionListener) {
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        selectPlayer = new JLabel("Select Player");
        this.add(selectPlayer, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        PlayerOption = new JComboBox(players.toArray());
        PlayerOption.setSelectedIndex(0);//defalut
        PlayerOption.addActionListener(this);
        this.add(PlayerOption, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        selectButton = new JButton("Select");
        selectButton.setEnabled(false);
        selectButton.addActionListener(actionListener);
        selectButton.setActionCommand("EditCharacter.Select");
        this.add(selectButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(actionListener);
        cancelButton.setActionCommand("EditCharacter.Cancel");
        this.add(cancelButton, constraints);

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        selectButton.setEnabled(true);
        int index = PlayerOption.getSelectedIndex();
        setPlayer(PlayerOption.getItemAt(index));

    }


}
