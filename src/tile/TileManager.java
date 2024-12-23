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
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for(int i = 0; i < gp.maxScreenCol; i++) {
            for(int j = 0; j < gp.maxScreenRow; j++) {
                int tileNum = mapTileNum[i][j];
                g2.drawImage(tiles[tileNum].image, i * gp.tileSize, j * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int i = 0; i < gp.maxScreenRow; i++) {
                String line = br.readLine();
                for(int j = 0; j < gp.maxScreenCol; j++) {

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
