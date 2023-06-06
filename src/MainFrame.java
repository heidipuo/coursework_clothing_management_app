import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame {
    
    private JFrame frame;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        this.frame.setTitle("Vaatekaappi");
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
                new AddClotheFrame();
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
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        //panel.setBackground(Color.pink);
        panel.setBackground(new Color(60, 72, 107));
        panel.setPreferredSize(new Dimension(350, 70));

        //Setting up the place for logo or name of the service
        JLabel label = new JLabel();
        ImageIcon logo = new ImageIcon(getClass().getResource("logo1.png"));
        label.setIcon(logo);
        panel.add(label);


        JPanel midSectionPanel = new JPanel();
        //midSectionPanel.setLayout(new GridLayout(4, 1, 20, 100));
        //Testaan eri layoutteja napeille, alkuperäinen kommenteissa yläpuolella
        midSectionPanel.setLayout(new GridLayout(5, 1, 5, 30));
        midSectionPanel.setBackground(Color.LIGHT_GRAY);

        //Tervetuloteksti painikkeiden yläpuolella
        JLabel welcomeLabel = new JLabel("<html><center>Tervetuloa vaatekaappiisi!<br>Täällä sinä hallitset vaatteita eivätkä ne sinua.<br>Valitse alta haluttu toiminto.</center></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        midSectionPanel.add(welcomeLabel);

        Button button1 = new Button("Lisää vaate");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //sulkee nykyisen framen ennen uuden avaamista
                frame.dispose(); 
                new AddClotheFrame();
                }
            });
        midSectionPanel.add(button1);
        button1.setFont(new Font("Arial", Font.PLAIN, 20));
        
        Button button2 = new Button("Katso vaatekaappi");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //sulkee nykyisen framen ennen uuden avaamista
                frame.dispose(); 
                new SeeWardrobeFrame();
                }
            });
        midSectionPanel.add(button2);
        button2.setFont(new Font("Arial", Font.PLAIN, 20));

        //Vaatteidenhakukenttä
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 35));
        JTextField searchField = new JTextField("Hae vaatekaapista");
        searchField.setForeground(Color.GRAY);

        //Harmaa teksti tekstiboxissa, joka katoaa kun käyttäjä alkaa kirjoittamaan
        searchField.addFocusListener(new FocusListener() {
           
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Hae vaatekaapista")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Hae vaatekaapista");
                }
            }
        });

        //"Hae" nappi "Hae vaatekaapista" ominaisuudelle
        searchField.setPreferredSize(new Dimension(200, 25));
        searchPanel.add(searchField);
        Button searchButton = new Button("Hae");
        searchPanel.add(searchButton);
        searchPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        midSectionPanel.add(searchPanel);
               
        frame.add(panel, BorderLayout.NORTH);
        frame.add(midSectionPanel, BorderLayout.CENTER);
        
        this.frame.setVisible(true);
    }

}
