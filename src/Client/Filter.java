package Client;

import Server.InjuredPoliceman;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Filter extends JPanel{

    JButton bStart = new JButton("Start");
    JButton bStop = new JButton("Stop");
    JButton bFilter = new JButton ("Filter");
    JButton bReload = new JButton("Reload");
    Timer timer;
    final int timerDelay = 1000;
    final int growTime = 5000;
    final int shrinkTime = 3000;

    public Filter(){
        GridLayout gridLayout = new GridLayout(7,1);//todo всплывающие одсказки у объектов
        setLayout(gridLayout);
        add(bStart);
        add(bStop);
        add(bFilter);
        add(bReload);
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });
        bStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStopActionPerformed(evt);
            }
        });
        bFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFilterActionPerformed(evt);
            }
        });
        bReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReloadActionPerformed(evt);
            }
        });
    }

    boolean isActive = true;

    private void bStartActionPerformed(ActionEvent evt) {
        timer = new Timer(timerDelay, new ActionListener() {
            long startTime = System.currentTimeMillis();
            float shrinkPixels = 5;//(shrinkTime/timerDelay) / (Client.injuredPolicemen.get(0).size/2);
            float growPixels = 3;//(growTime/timerDelay) / (Client.injuredPolicemen.get(0).size/2);

            public void actionPerformed(ActionEvent ev) {
                for (InjuredPoliceman policeman : GUIFilter.filteredInjuredPolicemen) {
                    if (System.currentTimeMillis() - startTime < shrinkTime) {
                        policeman.size = policeman.size - shrinkPixels;
                    } else if (System.currentTimeMillis() - startTime > shrinkTime && System.currentTimeMillis() - startTime < shrinkTime + growTime) {
                        policeman.size = policeman.size + growPixels;
                    } else {
                        startTime = System.currentTimeMillis();
                    }
                }
            }
        });
        timer.start();
    }

    private void bStopActionPerformed(ActionEvent evt) {
        timer.stop();
        for (InjuredPoliceman injuredPoliceman : Client.injuredPolicemen        ) {
            injuredPoliceman.size = 75;
        }
    }

    private void bReloadActionPerformed(ActionEvent evt){

        //Client.UpdateGUI();

        Client.getCollection();
        Client.UpdateGUI();
        //Client.createGUI();
//        DataInputStream in = new DataInputStream(inputStream);
//        DataOutputStream out = new DataOutputStream(outputStream);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String enterStr = null;
//
//
//        Gson gson = new Gson();
//        //String str; // переменная для хранения того, что прислал сервер
//
//        Type type = new TypeToken<ArrayList<InjuredPoliceman>>() {
//        }.getType();
//
//        Client.str = in.readUTF();
//        Client.injuredPolicemen = gson.fromJson(str, type);
    }

    private void bFilterActionPerformed(ActionEvent evt) {
        GUIFilter.createGUI();
    }
}
