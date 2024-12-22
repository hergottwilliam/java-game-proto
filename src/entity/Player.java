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
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/lennon/lennon_down1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHandler.upPressed == true) {
            direction = EntityDirectionEnum.UP;
            y -= speed;
        }
        if(keyHandler.downPressed == true) {
            direction = EntityDirectionEnum.DOWN;
            y += speed;
        }
        if(keyHandler.leftPressed == true) {
            direction = EntityDirectionEnum.LEFT;
            x -= speed;
        }
        if(keyHandler.rightPressed == true) {
            direction = EntityDirectionEnum.RIGHT;
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        
        if(direction.equals(EntityDirectionEnum.UP)) {
            image = up1;
        } 
        
        if(direction.equals(EntityDirectionEnum.DOWN)) {
            image = down1;
        } 
        
        if(direction.equals(EntityDirectionEnum.LEFT)) {
            image = up1;
        } 
        
        if(direction.equals(EntityDirectionEnum.RIGHT)) {
            image = down1;
        } 

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
