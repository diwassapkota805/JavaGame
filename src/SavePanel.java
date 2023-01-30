import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SavePanel extends JPanel implements ActionListener{
    private JLabel PlayersLabel;
    private JComboBox PlayersBox;
    private JFileChooser fileLabel;
    private JButton confirm;
    private JButton cancel;
    private String PlayerInfo;


    public SavePanel(ActionListener actionListener, ArrayList<Player> players)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());


        PlayersLabel = new JLabel("Players:");
        PlayersBox = new JComboBox(players.toArray());
        PlayersBox.setActionCommand("PlayersBox");
        PlayersBox.addActionListener(actionListener);
        setPlayerInfo(PlayersBox.getItemAt(PlayersBox.getSelectedIndex()).toString());

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(PlayersLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(PlayersBox, constraints);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());

        confirm = new JButton("Save");
        confirm.setActionCommand("SavePanel.confirm");
        confirm.addActionListener(actionListener);
        buttonPanel.add(confirm);

        cancel = new JButton("Cancel");
        cancel.setActionCommand("SavePanel.cancel");
        cancel.addActionListener(actionListener);
        buttonPanel.add(cancel);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(buttonPanel, constraints);
    }

    public String getPlayerInfo(){
        return PlayerInfo;
    }

    public void setPlayerInfo( String info){
        PlayerInfo = info;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        if(event == "PlayersBox"){
            int index = PlayersBox.getSelectedIndex();
            System.out.println("Selected item:" + index);
            setPlayerInfo(PlayersBox.getItemAt(index).toString());
        }
    }
}
