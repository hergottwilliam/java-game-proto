package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import enums.EntityDirectionEnum;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
        super.buildDirectionMaps();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = EntityDirectionEnum.DOWN;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_right2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {
        if(keyHandler.upPressed == true) {
            direction = EntityDirectionEnum.UP;
            y -= speed;
            animateWalk();
        }
        if(keyHandler.downPressed == true) {
            direction = EntityDirectionEnum.DOWN;
            y += speed;
            animateWalk();
        }
        if(keyHandler.leftPressed == true) {
            direction = EntityDirectionEnum.LEFT;
            x -= speed;
            animateWalk();
        }
        if(keyHandler.rightPressed == true) {
            direction = EntityDirectionEnum.RIGHT;
            x += speed;
            animateWalk();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if(spriteNum == 1){
            image = directionMap1.get(direction);
        }
        if(spriteNum == 2) {
            image = directionMap2.get(direction);
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void animateWalk() {
        spriteCounter++;
        if(spriteCounter > 15) {
            spriteNum = 3 - spriteNum; // switch between 2 & 1 (for steps)
            spriteCounter = 0;
        }
    }
}
