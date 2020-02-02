import Player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WindowHandler extends JFrame {
    //JComponents
    JPanel searchPanel, mainPanel, listPanel;
    JLabel playerName, currentMMR;
    JTextField search;
    JButton searchPlayerBtn, savePlayerBtn;
    JLabel image;
    ImageIcon playerImage;

    //URL
    String r6URL = "https://r6tab.com/api/search.php?platform=uplay&search=";
    String idURL = "https://r6tab.com/api/player.php?p_id=";
    String imageURL;
    //TODO try out JTabbedPane
    ArrayList<Player> playerList;


    public WindowHandler(){

        playerList = new ArrayList<>();
        //Init components
        searchPanel = new JPanel();
        mainPanel = new JPanel();
        listPanel = new JPanel();
        search = new JTextField();
        searchPlayerBtn = new JButton("Search Player.Player");
        savePlayerBtn = new JButton("Save Player.Player to List");
        playerName = new JLabel(MagicNumbers.NAME);
        currentMMR = new JLabel(MagicNumbers.NA_MMR);
        playerImage = new ImageIcon();
        image = new JLabel(playerImage);

        //Configuring window
        setSize(800, 800);
        setVisible(true);
        setTitle("R6 Stats Tracker");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        setResizable(false);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //Configure components
        searchPanel.setPreferredSize(new Dimension(800, 50));
        mainPanel.setPreferredSize(new Dimension(800, 150));
        listPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setLayout(new GridLayout(0, 4));
        searchPanel.setBackground(Color.BLACK);
        search.setPreferredSize(new Dimension(0, 30));
        searchPlayerBtn.setPreferredSize(new Dimension(0, 30));

        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.add(search, c);
        c.gridx = 1;
        c.weightx = 0.25;
        searchPanel.add(searchPlayerBtn, c);
        mainPanel.add(image);
        mainPanel.add(playerName);
        mainPanel.add(currentMMR);
        listPanel.add(savePlayerBtn);
        add(searchPanel);
        add(mainPanel);
        add(listPanel);

        searchPanel.setVisible(true);

        /* Action Listeners*/
        searchPlayerBtn.addActionListener(e -> {
                String player = search.getText();
                ConnectionHandler searchCon = new ConnectionHandler(r6URL + player);
                try {
                    //Get the p_id, then use it to get other stats
                    String[] p_name_id = getPlayerID(searchCon.sendGET());
                    ConnectionHandler infoCon = new ConnectionHandler(idURL + p_name_id[1]);

                    //Create the player object based on this information
                    String[] playerInfoArray = createPlayer(infoCon.sendGET());

                    playerName.setText(MagicNumbers.NAME + p_name_id[0]);
                    currentMMR.setText(MagicNumbers.NA_MMR + playerInfoArray[0]);

                    //Creates player and adds player to list
                    URL temp = new URL("https://ubisoft-avatars.akamaized.net/" + p_name_id[2] +
                            "/default_146_146.png");
                    BufferedImage img = ImageIO.read(temp);
                    playerImage.setImage(img);
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

    }

    public String[] createPlayer(String jsonResponse){
        //Parses the HTTP request response into JSON and searches for our name and
        JSONObject responseObj = new JSONObject(jsonResponse);
        JSONObject results = responseObj.getJSONObject("ranked");
        System.out.println(results.get("NA_mmr"));
        String[] jsonResults = {
                String.valueOf(results.get("NA_mmr"))
        };
        return jsonResults;
    }

    public String[] getPlayerID(String jsonResponse){
        JSONObject responseObj = new JSONObject(jsonResponse);
        JSONArray results = responseObj.getJSONArray("results");
        JSONObject playerResults = results.getJSONObject(0);
        String[] jsonResults = {
                String.valueOf(playerResults.get("p_name")),
                String.valueOf(playerResults.get("p_id")),
                String.valueOf(playerResults.get("p_user"))
        };
        return jsonResults;
    }
}
