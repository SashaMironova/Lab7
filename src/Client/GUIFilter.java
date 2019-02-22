package Client;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;

public class GUIFilter extends JFrame{
    JLabel lName;
    JFormattedTextField tName;
    JLabel lYearOfBirth;
    JFormattedTextField tYearOfBirth;
    JLabel lCoordinates;
    JSlider sX;
    JLabel lCoordinateX;
    JLabel lCoordinateY;
    JSlider sY;
    JLabel lState;
    JList<String> liState;
    JLabel lInjuredFacePart;
    JList<String> liInjuredFacePart;
    JLabel lInjuredBodyPart;
    JList<String> liInjuredBodyPart;
    JLabel lColour;
    JList<String> liColour;
    JLabel lError;
    JButton bFilter;
    String[] states = {"GOOD", "NAUSEA", "BAD", "SMOOTH"};
    String[] bodyParts = {"LEFT_HAND", "RIGHT_HAND", "LEFT_LEG", "RIGHT_LEG", "HEAD", "RIGHT_FEET", "LEFT_FEET"};
    String[] faceParts = {"FOREHEAD", "NOSE", "RIGHT_EYE", "LEFT_EYE", "MOUTH", "CHEEKS"};
    String[] colours = {"red", "blue", "orange", "green", "yellow", "white", "black"};


    public GUIFilter(){
        super("Фильтр");
    }

    public static void createGUI() {
        GUIFilter frame = new GUIFilter();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 800);
    }

    private void addComponents(final Container pane){
        Font font1 = new Font("SansSerif", Font.BOLD, 25);

        bFilter = new JButton("Set filter");

        lName = new JLabel("Name:");
        lName.setHorizontalAlignment(lName.CENTER);
        lName.setFont(font1);

        lYearOfBirth = new JLabel("Year of birth:");
        lYearOfBirth.setHorizontalAlignment(lYearOfBirth.CENTER);
        lYearOfBirth.setFont(font1);

        lState = new JLabel("State:");
        lState.setHorizontalAlignment(lState.CENTER);
        lState.setFont(font1);

        lCoordinates = new JLabel("  Coordinates:");
        lCoordinates.setHorizontalAlignment(lCoordinates.LEFT);
        lCoordinates.setFont(font1);

        lCoordinateX = new JLabel("X:");
        lCoordinateX.setHorizontalAlignment(lCoordinateX.CENTER);
        lCoordinateX.setFont(font1);

        lCoordinateY = new JLabel("Y:");
        lCoordinateY.setHorizontalAlignment(lCoordinateY.CENTER);
        lCoordinateY.setFont(font1);

        lInjuredFacePart = new JLabel("Injured face part:");
        lInjuredFacePart.setHorizontalAlignment(lInjuredFacePart.CENTER);
        lInjuredFacePart.setFont(font1);

        lInjuredBodyPart = new JLabel("Injured body part:");
        lInjuredBodyPart.setHorizontalAlignment(lInjuredBodyPart.CENTER);
        lInjuredBodyPart.setFont(font1);

        lColour = new JLabel("Colour:");
        lColour.setHorizontalAlignment(lColour.CENTER);
        lColour.setFont(font1);

        lError = new JLabel();
        lError.setForeground(new Color(255, 0, 51));
        lError.setHorizontalAlignment(lError.CENTER);
        lError.setFont(font1);

        tName = new JFormattedTextField();
        tYearOfBirth = new JFormattedTextField();
        sX = new JSlider(0,600);
        sY = new JSlider(0,400);
        liState = new JList<String>(states);
        liInjuredBodyPart = new JList<String>(bodyParts);
        liInjuredFacePart = new JList<String>(faceParts);
        liColour = new JList<String>(colours);
        JLabel empty = new JLabel("");

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(10,2, 5, 5);
        panel.setLayout(layout);
        panel.add(lName);
        panel.add(tName);
        panel.add(lYearOfBirth);
        panel.add(tYearOfBirth);
        panel.add(lCoordinates);
        panel.add(empty);
        panel.add(lCoordinateX);
        panel.add(sX);
        panel.add(lCoordinateY);
        panel.add(sY);
        panel.add(lState);
        panel.add(liState);
        panel.add(lInjuredFacePart);
        panel.add(liInjuredFacePart);
        panel.add(lInjuredBodyPart);
        panel.add(liInjuredBodyPart);
        panel.add(lColour);
        panel.add(liColour);
        panel.add(lError);
        panel.add(bFilter);
        pane.add(panel);
    }
}
