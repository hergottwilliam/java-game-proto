package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/resources/maps/map.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassy_tile.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/regular_tree_tile.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/water_tile.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wood_plank_tile.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/xmas_tree_tile.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for(int i = 0; i < gp.maxWorldCol; i++) {
            for(int j = 0; j < gp.maxWorldRow; j++) {
                int tileNum = mapTileNum[i][j];

                int worldX = i * gp.tileSize;
                int worldY = j * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int i = 0; i < gp.maxWorldRow; i++) {
                String line = br.readLine();
                for(int j = 0; j < gp.maxWorldCol; j++) {

                    String numbers[] = line.split(" ");
                    mapTileNum[j][i] = Integer.parseInt(numbers[j]);
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 }
