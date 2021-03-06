package ui.screens;

import model.User;
import ui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents a main screen for the GUI
public class MainScreen implements ActionListener {
    protected static final Color MAIN_CONTAINER_COLOR = new Color(42, 34, 74);
    protected static final Color TOP_PANEL_COLOR = new Color(255, 252, 171);
    protected static final Color LEFT_PANEL_COLOR = new Color(243, 100, 198);
    protected static final Color RIGHT_PANEL_COLOR = new Color(100, 220, 230);
    protected static final Color SIDE_PANEL_FONT_COLOR = MAIN_CONTAINER_COLOR;
    protected static final int TOP_PANEL_FONT_SIZE = 30;
    protected static final int BUTTON_FONT_SIZE = 20;
    protected static final int SIDE_PANEL_FONT_SIZE = 25;
    private static final int CENTRE_PANEL_FONT_SIZE = 30;

    private static final int HGAP = 8;
    private static final int VGAP = 6;
    protected Container mainContainer;
    private JPanel centrePanel;
    protected GUI gui;

    private String profilePicPath = "./data/image/elmo.jpg";

    // EFFECTS: initializes the MainScreen's GUI as the current GUI
    public MainScreen(GUI gui) {
        this.gui = gui;
    }

    // MODIFIES: this
    // EFFECTS: sets up components for the main screen.
    public void initialize() {
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        loadDataFromJson();

        setupMainContainer();
        setupTopPanel(mainContainer);
        setupLeftPanel(mainContainer);
        setupRightPanel(mainContainer);
        setupCentrePanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the value of the mainContainer and sets the layout and background
    private void setupMainContainer() {
        mainContainer = gui.getContentPane();
        mainContainer.setLayout(new BorderLayout(HGAP, VGAP));
        mainContainer.setBackground(MAIN_CONTAINER_COLOR);
    }

    // MODIFIES: mainContainer
    // EFFECTS: constructs the top panel of the main container passed in as param
    private void setupTopPanel(Container mainContainer) {
        JPanel topPanel = new JPanel();
        JPanel topLabelPanel = new JPanel();
        setupTopLabelPanel(topLabelPanel);
        setupSidePanel(topPanel, topLabelPanel, "top");
        mainContainer.add(topPanel, BorderLayout.NORTH);
    }

    // MODIFIES: panel
    // EFFECTS: creates a new label with the title and adds the label to the given panel
    private void setupTopLabelPanel(JPanel panel) {
        JLabel label = new JLabel("Favour for Favour", SwingConstants.CENTER);
        setLabelFont(label, label.getForeground(), TOP_PANEL_FONT_SIZE);
        label.setBackground(TOP_PANEL_COLOR);
        panel.setBackground(TOP_PANEL_COLOR);
        panel.add(label);
    }

    // MODIFIES: mainContainer
    // EFFECTS: constructs the left panel of the main container passed in as param
    private void setupLeftPanel(Container mainContainer) {
        JPanel leftPanel = new JPanel();
        JPanel leftLabelPanel = new JPanel();
        setupLeftPanelGrid(leftLabelPanel);
        setupSidePanel(leftPanel, leftLabelPanel, "left");
        mainContainer.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(LEFT_PANEL_COLOR);
    }

    // MODIFIES: panel
    // EFFECTS: constructs a grid panel to allow 2 sections (user profile, my requested favours)
    private void setupLeftPanelGrid(JPanel panel) {
        JPanel gridPanel = new JPanel(new GridLayout(2, 1));
        JPanel userProfile = new JPanel();
        JPanel myReq = new JPanel(new GridLayout(5, 1, 5, 5));
        setupUserProfile(userProfile);
        setupMyReq(myReq);
        userProfile.setVisible(true);
        myReq.setVisible(true);
        gridPanel.add(userProfile);
        gridPanel.add(myReq);
        panel.add(gridPanel);
        gridPanel.setBackground(LEFT_PANEL_COLOR);
        userProfile.setBackground(LEFT_PANEL_COLOR);
        myReq.setBackground(LEFT_PANEL_COLOR);
    }

    // MODIIFES: this
    // EFFECTS: constructs a user profile panel to allow user information
    private void setupUserProfile(JPanel panel) {
        ImageIcon icon;
        try {
            icon = new ImageIcon(ImageIO.read(new File(profilePicPath)));
            JLabel picLabel = new JLabel("John Doe", icon, JLabel.CENTER);
            // TODO: add a "interests" section under this
            setLabelFont(picLabel, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);
            picLabel.setVerticalTextPosition(JLabel.BOTTOM);
            picLabel.setHorizontalTextPosition(JLabel.CENTER);
            panel.add(picLabel);
            picLabel.setVisible(true);
        } catch (IOException e) {
            System.err.println("No image found at " + profilePicPath);
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs a user profile panel to allow user information
    private void setupMyReq(JPanel panel) {
        JLabel title = new JLabel("My Requested Favours", JLabel.CENTER);
        setLabelFont(title, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);
        title.setVisible(true);
        panel.add(title);

        JButton req1 = setupButton("Please pick up my groceries");
        JButton req2 = setupButton("Walk my dog");
        JButton req3 = setupButton("Tell me a story");

        panel.add(req1);
        panel.add(req2);
        panel.add(req3);
    }

    private JButton setupButton(String name) {
        JButton button = new JButton(name);
        String font = button.getFont().toString();
        button.setFont(new Font(font, Font.PLAIN, BUTTON_FONT_SIZE));
        button.setActionCommand(name);
        button.addActionListener(this);
        button.setVisible(true);
        return button;
    }

    // MODIFIES: mainContainer
    // EFFECTS: constructs the right panel of the mainContainer passed in as a parameter
    private void setupRightPanel(Container mainContainer) {
        JPanel rightPanel = new JPanel();
        JPanel rightLabelPanel = new JPanel();
        setupRightPanelGrid(rightLabelPanel);
        setupSidePanel(rightPanel, rightLabelPanel, "right");
//        setupPanelButtons(rightPanel, "right");
        mainContainer.add(rightPanel, BorderLayout.EAST);
    }

    // MODIFIES: panel
    // EFFECTS: constructs a grid panel to allow a list of buttons
    private void setupRightPanelGrid(JPanel panel) {
        JPanel gridPanel = new JPanel(new GridLayout(7, 1, 0, 5));
        JLabel title = new JLabel("Pinned Favours", JLabel.CENTER);
        setLabelFont(title, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);
        gridPanel.setBackground(RIGHT_PANEL_COLOR);
        gridPanel.add(title);

        JButton pin1 = setupButton("Help brainstorm my daughter's birthday party ideas");
        JButton pin2 = setupButton("Join HackCamp 2021 with me");
        JButton pin3 = setupButton("Play soccer with my friends");
        JButton pin4 = setupButton("Get groceries");
        JButton pin5 = setupButton("Give me a high five!");
        JButton pin6 = setupButton("Buy me cold medicine");

        gridPanel.add(pin1);
        gridPanel.add(pin2);
        gridPanel.add(pin3);
        gridPanel.add(pin4);
        gridPanel.add(pin5);
        gridPanel.add(pin6);

        panel.add(gridPanel);
    }


    // MODIFIES: panel, labelPanel
    // EFFECTS: sets up the panel and label's background colour to the default panel colour for the side (top or side)
    //          and adds the label to the panel
    private void setupSidePanel(JPanel panel, JPanel labelPanel, String side) {
        Color panelColor;
        if (side.equals("top")) {
            panelColor = TOP_PANEL_COLOR;
        } else if (side.equals("left")){
            panelColor = LEFT_PANEL_COLOR;
        } else { // "right"
            panelColor = RIGHT_PANEL_COLOR;
        }
        panel.setBackground(panelColor);
        labelPanel.setBackground(panelColor);
        panel.add(labelPanel);
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: constructs the centre panel of the mainContainer passed in as a parameter
    private void setupCentrePanel() {
        centrePanel = new JPanel();
        centrePanel.setBackground(MAIN_CONTAINER_COLOR);
        setupCentreLabelPanel(centrePanel);
        mainContainer.add(centrePanel, BorderLayout.CENTER);
    }

    private void setupCentreLabelPanel(JPanel centrePanel) {
        JPanel labelEncapsulator = new JPanel(new GridLayout(5,0));
        labelEncapsulator.setBackground(MAIN_CONTAINER_COLOR);

        JLabel centreLabel = new JLabel("Available Favours");
        setLabelFont(centreLabel, Color.white, CENTRE_PANEL_FONT_SIZE);;

        JTextArea textArea = new JTextArea("- Wash my car\n\n- Do my dishes\n\n- Lend me herbs and spices for my dinner party\n\n- Babysit my kids (4)");
        textArea.setFont(new Font(textArea.getFont().toString(), Font.PLAIN, CENTRE_PANEL_FONT_SIZE));
        textArea.setForeground(Color.white);
        textArea.setBackground(MAIN_CONTAINER_COLOR);
        textArea.setEditable(false);

        labelEncapsulator.add(centreLabel);
        labelEncapsulator.add(textArea);

        centrePanel.add(labelEncapsulator);
    }

    // MODIFIES: label
    // EFFECTS: changes the font colour and size of the given label to the given colour and size
    public void setLabelFont(JLabel label, Color color, int size) {
        label.setFont(new Font(label.getFont().toString(), Font.PLAIN, size));
        label.setForeground(color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Please pick up my groceries".equals(e.getActionCommand())) {
            PopUpScreen popUpScreen = new PopUpScreen("Please pick up my groceries");
            popUpScreen.loadReqFavour("Please pick up my groceries");
        } else if ("Walk my dog".equals(e.getActionCommand())) {
            PopUpScreen popUpScreen = new PopUpScreen("Walk my dog");
            popUpScreen.loadReqFavour("Walk my dog");
        } else if ("Tell me a story".equals(e.getActionCommand())) {
            PopUpScreen popUpScreen = new PopUpScreen("Tell me a story");
            popUpScreen.loadReqFavour("Tell me a story");
        }
    }
}
