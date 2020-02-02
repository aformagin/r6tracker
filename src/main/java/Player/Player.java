package Player;

import java.awt.image.BufferedImage;

public class Player {
    private String name;
    private String UUID;
    private String p_user;
    private int mmr;
    private String playerRank;
    private BufferedImage img;

    public Player() {
        this.name = "Test Player.Player";
        this.UUID = "ThIs1s-AuUId-f0rt3st1n6";
        this.mmr = 2500;
        this.playerRank = "Silver 1";
    }

    public Player(String name, String UUID, int mmr, String p_user, BufferedImage img) {
        this.name = name;
        this.UUID = UUID;
        this.mmr = mmr;
        this.p_user = p_user;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getMmr() {
        return mmr;
    }

    public void setMmr(int mmr) {
        this.mmr = mmr;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public String getP_user() {
        return p_user;
    }

    public void setP_user(String p_user) {
        this.p_user = p_user;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
