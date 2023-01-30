import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EditPanel extends JPanel implements DocumentListener, ActionListener{
    private JTextField nameTextField;
    private JTextField strTextField;
    private JTextField dexTextField;
    private JTextField conTextField;
    private JComboBox WeaponsComboBox;
    private JComboBox<File> avatarComboBox;

    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel avatarPanel;

    public EditPanel(ActionListener listener){
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        JLabel namePanel = new JLabel("Name");//namePanel.setB
        JLabel strPanel = new JLabel("STR");
        JLabel dexPanel = new JLabel("DEX");
        JLabel conPanel = new JLabel("CON");
        JLabel weaponPanel = new JLabel("Weapon");
        avatarPanel = new JLabel("Avatar");

        nameTextField = new JTextField("");
        strTextField = new JTextField("");
        dexTextField = new JTextField("");
        conTextField = new JTextField("");
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");


        try{
            File file = new File("src/weapons.csv");
            Scanner wep = new Scanner(file);
            ArrayList<Weapon> weapons = new ArrayList<>();
            while(wep.hasNext())
            {
                weapons.add(Weapon.weapon(wep.nextLine()));
            }
                WeaponsComboBox = new JComboBox<>(weapons.toArray());
        }catch (FileNotFoundException i)
        {
            System.out.println("File couldn't be found");

        }


        File[] files = new File("src/img").listFiles();
        avatarComboBox = new JComboBox<>(files);
        avatarComboBox.addActionListener(this);



        constraints.gridx = 0;
        constraints.gridy = 0;
        add(namePanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        nameTextField.getDocument().addDocumentListener(this);
        nameTextField.setPreferredSize(new Dimension(200,25));
        add(nameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(strPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        strTextField.getDocument().addDocumentListener(this);
        strTextField.setPreferredSize(new Dimension(200,25));
        add(strTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(dexPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        dexTextField.getDocument().addDocumentListener(this);
        dexTextField.setPreferredSize(new Dimension(200,25));
        add(dexTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(conPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        conTextField.getDocument().addDocumentListener(this);
        conTextField.setPreferredSize(new Dimension(200,25));
        add(conTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(weaponPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        WeaponsComboBox.setPreferredSize(new Dimension(200,25));
        add(WeaponsComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy= 5;
        add(avatarPanel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        avatarComboBox.setPreferredSize(new Dimension(200,25));
        add(avatarComboBox, constraints);




        //panel for buttons
        JPanel buttonPanal = new JPanel();
        buttonPanal.setLayout(new GridLayout());

        confirmButton.addActionListener(listener);
        confirmButton.setActionCommand("EditPanel.Confirm");
        confirmButton.setEnabled(false);
        buttonPanal.add(confirmButton);

        cancelButton.addActionListener(listener);
        cancelButton.setActionCommand("EditPanel.Cancel");
        buttonPanal.add(cancelButton);

        constraints.gridx = 1;
        constraints.gridy = 6;
        add(buttonPanal, constraints);







    }

    public void clearField(){
        nameTextField.setText("");
        strTextField.setText("");
        dexTextField.setText("");
        conTextField.setText("");
    }

    public void setNameTextField(String Name)
    {
        nameTextField.setText(Name);
    }
    public void setStrTextField(String STR)
    {
        strTextField.setText(STR);
    }
    public void setDexTextField(String DEX)
    {
        dexTextField.setText(DEX);
    }
    public void setConTextField(String CON)
    {
        conTextField.setText(CON);
    }
    public void setSelectetdWeapon(String weapon)
    {
        WeaponsComboBox.setSelectedItem(weapon);
    }
    public void setSelectedAvater(String path)
    {
        avatarComboBox.setSelectedItem(new File(path));
    }

    public String getPlayerName(){
        return nameTextField.getText();
    }
    public String getPlayerStr(){
        return strTextField.getText();
    }
    public String getPlayerDex(){
        return dexTextField.getText();
    }
    public String getPlayerCon(){
        return conTextField.getText();
    }
    public Weapon getPlayerWeapon()
    {
        return (Weapon) WeaponsComboBox.getSelectedItem();
    }
    public String getAvatarPath(){
        int index = avatarComboBox.getSelectedIndex();
        File f = avatarComboBox.getItemAt(index);
        return f.getAbsolutePath();
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        boolean enable = false;
        if (nameTextField.getDocument().getLength() != 0 &&
                strTextField.getDocument().getLength() != 0 &&
                dexTextField.getDocument().getLength() != 0 &&
                conTextField.getDocument().getLength() != 0)
                    enable = true;

        confirmButton.setEnabled(enable);


    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        boolean enable = false;
        if(nameTextField.getDocument().getLength()!=0 &&
                strTextField.getDocument().getLength()!=0 &&
                dexTextField.getDocument().getLength()!=0&&
                conTextField.getDocument().getLength()!=0)
            enable = true;
        confirmButton.setEnabled(enable);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("Changed");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = avatarComboBox.getSelectedIndex();
        File f = avatarComboBox.getItemAt(index);
        try{
            Image image = ImageIO.read(f.getAbsoluteFile());
            image = image.getScaledInstance(50,50, Image.SCALE_DEFAULT);
            avatarPanel.setIcon(new ImageIcon(image));
        }
        catch (IOException a)
        {
            a.printStackTrace();
        }

    }

    //public static void
}
