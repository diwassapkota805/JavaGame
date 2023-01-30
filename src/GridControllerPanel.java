import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GridControllerPanel extends JPanel implements SpriteMoveListener {
    private MainPanel gamePanel;
    private ArrayList<Creature> Creatures = new ArrayList<>();

    public GridControllerPanel(ArrayList<Creature> creatures) {

        gamePanel = new MainPanel();
        gamePanel.addSpriteMoveListener(this);
        add(gamePanel);


        ArrayList<Integer> randomNumbers = new ArrayList<>();
        for (int a = 0; a < 10; a++)
            randomNumbers.add(a);
        Collections.shuffle(randomNumbers);

        int count = 1;
        for (Creature c : creatures) {
            c.move(randomNumbers.get(count), randomNumbers.get(count-1));
            addCreature(c, c.getAvatarPath());
            count++;
        }
    }

    public void addCreature(Creature c, String assetPath) {
        Creatures.add(c);
        int idx = gamePanel.addSprite(assetPath, c.getX(), c.getY());
    }

    @Override
    public void spriteMoved(int id, int x, int y) {
        Creature p = Creatures.get(id);
        p.move(x, y);
    }

    @Override
    public boolean canMove(int id) {
        Creature p = Creatures.get(id);

        return p.getCurrentMovement() > 0;
    }

    @Override
    public boolean canMoveTo(int id, int x, int y) {
        // incoming x and y are in relative pixel coordinates, convert to grid coordinates
        Point p = gamePanel.getGridLocation(x, y);

        Creature player = Creatures.get(id);
        int dx = Math.abs(p.x - player.getX());
        int dy = Math.abs(p.y - player.getY());
        int min = Math.min(dx, dy);
        int max = Math.max(dx, dy);
        int diagonalSteps = min;
        int straightSteps = max - min;

        int distance = (int) (Math.sqrt(2) * diagonalSteps + straightSteps);

        System.out.println("Distance = " + distance);

        return player.getCurrentMovement() >= distance;
    }
}

