package entity;

import java.awt.image.BufferedImage;
import enums.EntityDirectionEnum;

public class Entity {

    public int x;
    public int y;
    public int speed;

    public BufferedImage up1, up2;
    public BufferedImage down1, down2;
    public BufferedImage left1, left2;
    public BufferedImage right1, right2;

    EntityDirectionEnum direction;

}