import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerPanel extends JPanel implements MouseListener {
    private JLabel nameLabel;
    ArrayList<Player> PlayersInPpanel;
    private int currentIndex = 0;
    GridBagLayout layout;
    private GridBagConstraints constraints = new GridBagConstraints();


    public PlayerPanel() {
        layout = new GridBagLayout();
        this.setLayout(layout);

        constraints.gridx = 0;
        constraints.gridy = 0;
        nameLabel = new JLabel("No character");
        add(nameLabel);

    }


    public void updatePlayerPanel(Player player, ArrayList<Player> playersInPpanel) throws IOException {
        remove(nameLabel);
        constraints.gridx = 0;
        constraints.gridy = currentIndex;
        JLabel img = new JLabel();
        img.setIcon(imageIcon(player));
        add(img, constraints);

        constraints.gridx = 3;
        constraints.gridy = currentIndex;
        JLabel label = new JLabel(player.getName());
        add(label, constraints);

        playersInPpanel.add(player);

        currentIndex++;
    }

    public void setPlayer(Player p) {
        nameLabel.setText(p.getName());
    }

    public ImageIcon imageIcon(Player player) throws IOException {
        File file = new File(player.getAvatarPath());
        Image image = ImageIO.read(file);
        image = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
