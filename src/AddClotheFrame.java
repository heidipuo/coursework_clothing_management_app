import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

public class AddClotheFrame {
    
    private JFrame frame;
    private ArrayList<Product> products = new ArrayList<Product>();

    public AddClotheFrame() {
        initialize();
    }
    
    public AddClotheFrame(ArrayList<Product> products) {
        this.products = products;
        initialize();
    }

    private void initialize() {
        // Asetetaan frame
        frame = new JFrame();
        this.frame.setTitle("Lisää vaate");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(350, 700);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        // Ylävalikko
        JMenuBar menuBar = new JMenuBar();
        JMenu profileMenu = new JMenu("Menu");

        JMenuItem addClothes = new JMenuItem("Lisää vaate");
        addClothes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //sulkee nykyisen framen
                frame.dispose(); 
                new AddClotheFrame(products);
            }
        });

        JMenuItem Wardrobe = new JMenuItem("Katso vaatekaappi");
        Wardrobe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //sulkee nykyisen framen
                frame.dispose(); 
                new SeeWardrobeFrame();
            }
        });

        JMenuItem settingsButton = new JMenuItem("Asetukset");
        JMenuItem logoutButton = new JMenuItem("Kirjaudu ulos");
        
        // Lisätään painikkeet valikkoon
        profileMenu.add(addClothes);
        profileMenu.add(Wardrobe);       
        profileMenu.add(settingsButton);
        profileMenu.add(logoutButton);
        
        menuBar.add(profileMenu);
        frame.setJMenuBar(menuBar);
        
        // Asetetaan menu vasempaan yläreunaan
        menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        //Yläpaneeli
        JPanel panel = new JPanel();
    
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(new Color(60, 72, 107));
        panel.setPreferredSize(new Dimension(350, 70));
    
        //Paikka logolle
        JLabel label = new JLabel();
        ImageIcon logo = new ImageIcon(getClass().getResource("logo1.png"));
        label.setIcon(logo);
        panel.add(label);
    
        // Asetetaan midSectionPanel
        JPanel midSectionPanel = new JPanel();
        midSectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        midSectionPanel.setLayout(new BoxLayout(midSectionPanel, BoxLayout.Y_AXIS));
        midSectionPanel.setBackground(new Color(240, 240, 240));
    
        // Takaisin napin asetus 
        ImageIcon backIcon = new ImageIcon(getClass().getResource("backbutton.png"));  
        JButton backButton = new JButton(backIcon);
        backButton.setBackground(new Color(240, 5, 240));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        backButton.setBorder(emptyBorder);
        backButton.setIcon(backIcon);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                frame.setVisible(false);
            }
        });

        midSectionPanel.add(backButton);
        midSectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ohjeteksti
        JLabel instructionText = new JLabel("<html><center>Täytä tunnistetiedot lisättävälle vaatteelle. <br> Tyyppi ja nimi ovat pakollisia tietoja.</center></html>");
        instructionText.setHorizontalAlignment(JLabel.CENTER);
        midSectionPanel.add(instructionText);
        midSectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
        typePanel.setBackground(new Color(240, 240, 240));

        JLabel typeFieldLabel = new JLabel("Tyyppi: *");
        typePanel.add(typeFieldLabel);
        typePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel radioButtonPanel = new JPanel();

        //Napit päällekkäin
        radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
        radioButtonPanel.setBackground(new Color(240, 240, 240));

        JRadioButton outdoorClothes = new JRadioButton("Ulkovaate");
        JRadioButton indoorClothes = new JRadioButton("Sisävaate");
        JRadioButton otherClothes = new JRadioButton("Muut");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(outdoorClothes);
        buttonGroup.add(indoorClothes);
        buttonGroup.add(otherClothes);

        radioButtonPanel.add(outdoorClothes);
        radioButtonPanel.add(indoorClothes);
        radioButtonPanel.add(otherClothes);

        typePanel.add(radioButtonPanel);
        typePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        midSectionPanel.add(typePanel);
    
        //Nimi kenttä
        JLabel nameLabel = new JLabel("Nimi: *");
        midSectionPanel.add(nameLabel);
        JTextField nameField = new JTextField(30);
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Tieto voi olla max 15 merkkiä pitkä
                if (nameField.getText().length() >= 15) {
                    e.consume(); 
                }
            }
        });
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        midSectionPanel.add(nameField);
        midSectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //Väri kenttä
        JLabel colorLabel = new JLabel("Väri:");
        midSectionPanel.add(colorLabel);
        JTextField colorField = new JTextField(30);
        colorField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Tieto voi olla max 15 merkkiä pitkä
                if (colorField.getText().length() >= 15) {
                    e.consume(); 
                }
            }
        });
        colorField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        midSectionPanel.add(colorField);
        midSectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //Koko kenttä
        JLabel sizeLabel = new JLabel("Koko:");
        midSectionPanel.add(sizeLabel);
        JTextField sizeField = new JTextField(30);
        sizeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Tieto voi olla max 7 merkkiä pitkä
                if (sizeField.getText().length() >= 7) {
                    e.consume(); 
                }
            }
        });
        sizeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        midSectionPanel.add(sizeField);
        midSectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //muu tieto
        JLabel otherinfoLabel = new JLabel("Muu tunniste:");
        midSectionPanel.add(otherinfoLabel);
        JTextField otherinfoField = new JTextField(30);
        otherinfoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Tieto voi olla max 15 merkkiä pitkä
                if (otherinfoField.getText().length() >= 15) {
                    e.consume(); 
                }
            }
        });
        otherinfoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        midSectionPanel.add(otherinfoField);

       // Aseteetaan centrePanel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(new Color(240, 240, 240));

        // Lisää vaatekaappiin painike
        JButton addButton = new JButton("Lisää vaatekaappiin");
        centerPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Radionapin valinta
                String type = "";
                if (outdoorClothes.isSelected()) {
                    type = "ulkovaatteet";
                } else if (indoorClothes.isSelected()) {
                    type = "sisävaatteet";
                } else if (otherClothes.isSelected()) {
                    type = "muut";
                }  

                String name = nameField.getText();
                String color = colorField.getText();
                String size = sizeField.getText();
                String otherinfo = otherinfoField.getText();

                // Tarkistetaan onko pakolliset kentät täytetty
                if (type.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "<html><center>Täytä pakolliset tiedot!</center></html>", "Virhe", JOptionPane.ERROR_MESSAGE);

                    //Jos pakollinen kenttä on tyhjä, muutetaan label punaiseksi
                    if (type.isEmpty()) {
                        typeFieldLabel.setForeground(Color.RED);
                    } else {
                        typeFieldLabel.setForeground(Color.BLACK);
                    }
                    if (name.isEmpty()) {
                        nameLabel.setForeground(Color.RED);
                    } else {
                        nameLabel.setForeground(Color.BLACK);
                    }

                    return;
                }

                // Varmistusviesti lisättävän vaatteen tiedoista
                String message = "Lisätäänkö kaappiin?\n" + type + " " + name + " " + color + " " + size + " " + otherinfo;
                int dialogResult = JOptionPane.showOptionDialog(null, message, "Varmistusviesti", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Kyllä", "Ei"}, null);
                
                if(dialogResult == JOptionPane.YES_OPTION) {

                    Products.addNewClothe(type, name, size, color, otherinfo);
                    frame.dispose();
                    new MainFrame();
                    
                    // Ilmoitus onnistuneesta vaatteen lisäyksestä
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel("Vaate lisätty onnistuneesti!");
                    label.setHorizontalAlignment(SwingConstants.CENTER); // center the text
                    panel.add(label);
                    
                    // Ajastin, pop up katoaa automaattisesti n.1 sek jälkeen jos käyttäjä ei paina pop upin x näppäintä ennen sitä
                    Timer timer = new Timer(1050, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.getRootFrame().dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    
                    JOptionPane.showOptionDialog(null, panel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{}, null);
                } 
            }
        });

        //Tilaa napin ja alareunan väliin
        centerPanel.add(Box.createRigidArea(new Dimension(0, 70)));

        //Asetetaan CentrePanel Frameen
        frame.add(centerPanel, BorderLayout.SOUTH);

        //midSectionPaneli Frameen
        frame.add(panel, BorderLayout.NORTH);
        frame.add(midSectionPanel, BorderLayout.CENTER);

        this.frame.setVisible(true);
    }
}