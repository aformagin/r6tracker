package Panels;

import Player.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ListPanel extends JPanel {
    BufferedImage[]imgArray;
    String[] nameArray;
    int[] mmrArray;
    ArrayList<JLabel> labels;
    Dimension prefSize = new Dimension(800, 600);
    public ListPanel(ArrayList<Player> list){
        setPreferredSize(prefSize);
        setLayout(new GridLayout(5, 0));
        labels = new ArrayList<>();
        //Change the < 5 here to be < Y and X = Z
        for(int x = 0; x < 5; x++){
            labels.add(new JLabel((Icon) list.get(x).getImg()));
            add(labels.get(x));
        }
    }
}
