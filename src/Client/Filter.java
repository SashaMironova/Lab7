package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Filter extends JPanel{

    JButton bStart = new JButton("Start");
    JButton bStop = new JButton("Stop");
    JButton bFilter = new JButton ("Filter");

    public Filter(){
        GridLayout gridLayout = new GridLayout(7,1);
        setLayout(gridLayout);
        add(bStart);
        add(bStop);
        add(bFilter);
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });
        bFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFilterActionPerformed(evt);
            }
        });
    }

    private void bStartActionPerformed(ActionEvent evt){

    }

    private void bFilterActionPerformed(ActionEvent evt){
       GUIFilter.createGUI();
    }
}
