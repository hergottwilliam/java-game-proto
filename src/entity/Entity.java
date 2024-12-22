package entity;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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

    Map<EntityDirectionEnum, BufferedImage> directionMap1;
    Map<EntityDirectionEnum, BufferedImage> directionMap2;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public void buildDirectionMaps() {
        this.directionMap1 = new HashMap<>();
        this.directionMap1.put(EntityDirectionEnum.UP, up1);
        this.directionMap1.put(EntityDirectionEnum.DOWN, down1);
        this.directionMap1.put(EntityDirectionEnum.LEFT, left1);
        this.directionMap1.put(EntityDirectionEnum.RIGHT, right1);

        this.directionMap2 = new HashMap<>();
        this.directionMap2.put(EntityDirectionEnum.UP, up2);
        this.directionMap2.put(EntityDirectionEnum.DOWN, down2);
        this.directionMap2.put(EntityDirectionEnum.LEFT, left2);
        this.directionMap2.put(EntityDirectionEnum.RIGHT, right2);
    }

}
