package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    // SCREEN SETTING

    final int originalTileSize = 16; // pixels
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // tiles on x axis
    final int maxScreenRow = 12; // tiles on y axis
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
