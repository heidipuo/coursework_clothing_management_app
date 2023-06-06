import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SeeWardrobeFrame {
    
    private JFrame frame;
    private ArrayList<Product> productList;
    private Color backgroundColorGray = new Color(240, 240, 240);


    public SeeWardrobeFrame() {
        initialize();
    }
    

    private void initialize() {
        // Setting up the frame
        frame = new JFrame();
        this.frame.setTitle("Katso vaatekaappi");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(350, 700);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        // Ylävalikko
        JMenuBar menuBar = new JMenuBar();
        JMenu profileMenu = new JMenu("Menu");
        JMenuItem addClothes = new JMenuItem("Lisää vaate");
        JMenuItem wardrobe = new JMenuItem("Katso vaatekaappini");
        
        wardrobe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SeeWardrobeFrame();
                }
            });

        addClothes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddClotheFrame();
                }
            });

        JMenuItem settingsButton = new JMenuItem("Asetukset");
        JMenuItem logoutButton = new JMenuItem("Kirjaudu ulos");
        
        // Lisätään painikkeet valikkoon
        profileMenu.add(addClothes);
        profileMenu.add(wardrobe);       
        profileMenu.add(settingsButton);
        profileMenu.add(logoutButton);
        
        menuBar.add(profileMenu);
        frame.setJMenuBar(menuBar);
        
        // Asetetaan menu vasempaan yläreunaan
        menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
       
        //Setting up the upper panel
        JPanel panel = new JPanel();
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(new Color(60, 72, 107));
        panel.setPreferredSize(new Dimension(350, 70));

        //Setting up the place for logo or name of the service
        JLabel label = new JLabel();
        ImageIcon logo = new ImageIcon(getClass().getResource("logo1.png"));
        label.setIcon(logo);
        panel.add(label);

        // Setting up the midsection panel
        JPanel midSectionPanel = new JPanel();
        midSectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        midSectionPanel.setLayout(new BoxLayout(midSectionPanel, BoxLayout.PAGE_AXIS));
        midSectionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        midSectionPanel.setBackground(backgroundColorGray);
        
        // Setting up back buttom with an icon 
        ImageIcon backIcon = new ImageIcon(getClass().getResource("backbutton.png"));  
        JButton backButton = new JButton(backIcon);
        backButton.setBackground(backgroundColorGray);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setIcon(backIcon);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                frame.setVisible(false);
                }
            });

        midSectionPanel.add(backButton);

        //Setting up instruction text
        JLabel instruction = new JLabel("<html><right>Täältä näet listan vaatekaappisi sisällöstä  ja <br> pystyt halutessasi poistamaan  vaatekappaleita</right></html>");
        instruction.setBackground(backgroundColorGray);
        instruction.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        instruction.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
       
        midSectionPanel.add(instruction);
        
        //Setting up search panel for search field and button
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setMaximumSize(new Dimension(300, 30));
        
        JTextField searchField = new JTextField("Hae vaatekaapista");
        searchField.setPreferredSize(new Dimension(200, 25));
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
        searchPanel.add(searchField);
        searchPanel.add(Box.createRigidArea(new Dimension(7, 0)));
        Button searchButton = new Button("Hae");
        searchPanel.add(searchButton);
       
        midSectionPanel.add(searchPanel);

        // Setting up list of clothes in the wardrobe
        productList = Products.getProducts();
        arrangeAlphabethically();

        JPanel clothingList = new JPanel();
        clothingList.setBackground(backgroundColorGray);
        clothingList.setLayout(new BoxLayout(clothingList, BoxLayout.PAGE_AXIS));
        clothingList.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        
        JLabel yourClothes = new JLabel("Vaatekaappisi: ");
        yourClothes.setBorder(new EmptyBorder(0, 0, 10, 0));
        yourClothes.setFont(new Font("Sans-serif", Font.BOLD, 14));
        clothingList.add(yourClothes);

        JCheckBox [] checkBoxes = new JCheckBox[productList.size()];
        clothingList = createClothingList(checkBoxes, clothingList);
        if (productList.isEmpty()) {
            JLabel labelEmpty = new JLabel("Vaatekaappisi on tyhjä");
            labelEmpty.setFont(new Font("Sans-Serif", Font.ITALIC, 14));
            clothingList.add(labelEmpty);
        }
        
        //Setting up delete button
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.X_AXIS));
        deletePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        deletePanel.setMaximumSize(new Dimension(300, 30));
        
        JButton deleteButton = new JButton("Poista valitut");
        deleteButton.setMaximumSize(new Dimension(170, 30));
        deleteButton.setEnabled(false);
        deletePanel.add(Box.createRigidArea(new Dimension(60, 0)));
        deletePanel.add(deleteButton);
        
        // Checking if checkedboxes are checked and enabling or disabling the delete button accordingly
        for (int i = 0; i < checkBoxes.length; i++) {
        checkBoxes[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int selected = 0;
                    for (int i = 0; i < checkBoxes.length; i++) {
                        if (checkBoxes[i].isSelected()) {
                                selected++;
                        }
                    }
                    if (selected > 0) {
                        deleteButton.setEnabled(true);
                    }else{
                        deleteButton.setEnabled(false);
                    }
                }
        });
        }
               
        // Setting actions to the delete button
        deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {


                ArrayList<Product> itemsToKeep = new ArrayList<>();

                for (int i = 0; i < productList.size(); i++){
                    if (!checkBoxes[i].isSelected()){
                            itemsToKeep.add(productList.get(i));
                    }
                }
                
                if (itemsToKeep.size() != productList.size()){
                    JFrame popFrame = new JFrame();
                    popFrame.setSize(200, 150); 
                    popFrame.setLocationRelativeTo(null);
                   
                    
                    JPanel popPanel = new JPanel();
                    int toDeleteAmount = productList.size() - itemsToKeep.size();

                    JLabel label = new JLabel();
                    if (toDeleteAmount == 1){
                        label.setText("Poistetaanko " + toDeleteAmount + " tuote?");
                    }else{
                        label.setText("Poistetaanko " + toDeleteAmount + " tuotetta?");
                }
                    label.setBorder(new EmptyBorder(15, 10, 15, 10));
                    
                    JButton yesButton = new JButton("Kyllä");
                    JButton noButton = new JButton("Ei");

                    popPanel.add(label);
                    popPanel.add(yesButton);
                    popPanel.add(noButton);

                    popFrame.add(popPanel);
                    popFrame.setVisible(true);

                    yesButton.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                            
                            Products.setProducts(itemsToKeep);
                               
                            new SeeWardrobeFrame(); 
                            popFrame.dispose();
                            frame.dispose();
                            
                        }
                    });
                    
                    noButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            popFrame.dispose();
                        }
                    });
                }   
            }
        }); 
        

        JScrollPane scrollPane = new JScrollPane(midSectionPanel);

        midSectionPanel.add(clothingList);
        if (!productList.isEmpty()) {
        midSectionPanel.add(deletePanel);
        }

       
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane);
        this.frame.setVisible(true);
    }
    
    public JPanel createClothingList(JCheckBox[] checkBoxes, JPanel clothingList){

        
        ArrayList<String> typesOfProd = Products.getTypes();
        
        if (typesOfProd.contains("sisävaatteet")) {
            JLabel labelIn = new JLabel("SISÄVAATTEET");
            clothingList.add(labelIn);
        }
        for ( int j = 0; j < productList.size(); j++) {
            if (productList.get(j).getType().equalsIgnoreCase("sisävaatteet")) {    
                checkBoxes[j] = createCheckBox(j);
                clothingList.add(checkBoxes[j]);
            }
        }
        if (typesOfProd.contains("ulkovaatteet")) {
            JLabel labelOut = new JLabel("ULKOVAATTEET");
            labelOut.setBorder(new EmptyBorder(5, 0, 0, 0));
            clothingList.add(labelOut);
        }
        for ( int j = 0; j < productList.size(); j++) {
            if (productList.get(j).getType().equalsIgnoreCase("ulkovaatteet")) {
                checkBoxes[j] = createCheckBox(j);
                clothingList.add(checkBoxes[j]);
            }
        }

        if (typesOfProd.contains("muut")) {
            JLabel labelOthers = new JLabel("MUUT");
            labelOthers.setBorder(new EmptyBorder(5, 0, 0, 0));
            clothingList.add(labelOthers);
        }
        for ( int j = 0; j < productList.size(); j++) {
            if (productList.get(j).getType().equalsIgnoreCase("muut")) {
                checkBoxes[j] = createCheckBox(j);
                clothingList.add(checkBoxes[j]);
            }
        }

        return clothingList;
    }

    public JCheckBox createCheckBox (int j) {
        
        JCheckBox checkBox = new JCheckBox(productList.get(j).toString());
        checkBox.setBackground(backgroundColorGray);
        checkBox.setFont(new Font("Sans-serif", Font.PLAIN, 13));

        return checkBox;
    }

    public void arrangeAlphabethically(){

       
        for (int i = 1; i < productList.size(); i++){
            Product key = productList.get(i);
            int j = i - 1;
            
            while (j >= 0 && productList.get(j).getName().compareTo(key.getName()) > 0 ){
                productList.set((j+1), productList.get(j));
                j = j - 1;
            }
            productList.set((j+1), key);
        }
    }
}

