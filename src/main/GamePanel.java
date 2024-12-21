package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTING

    final int originalTileSize = 16; // pixels
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // tiles on x axis
    final int maxScreenRow = 12; // tiles on y axis
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // GAME VARIABLES
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    // GAME SETTINGS
    final int FPS = 60;

    // PLAYER SETTING
    int playerX = 100; // ref top left corner
    int playerY = 100; // ref top left corner
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //  100,000,000 nano sec (1 second) / 60 FPS = 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) { // game loop
            update();
            repaint();   

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // convert to milisec
                
                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(keyHandler.upPressed == true) {
            playerY -= playerSpeed;
        }
        if(keyHandler.downPressed == true) {
            playerY += playerSpeed;
        }
        if(keyHandler.leftPressed == true) {
            playerX -= playerSpeed;
        }
        if(keyHandler.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.GREEN);
        g2.fillRect(playerX, playerY, tileSize , tileSize);
        g2.dispose();
    }
}
