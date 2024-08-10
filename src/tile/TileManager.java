package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TileManager {
    GamePanel gamePanel;
    int[][] map;
    Tile[] tiles;
    String[] tileTypes;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tileTypes = new String[]{"grass", "water", "sand", "wall", "tree", "earth"};
        int totalTiles = gamePanel.maxScreenCol * gamePanel.maxScreenRow;
        this.tiles = new Tile[totalTiles];
        this.map = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        loadMap("/maps/map01.txt");
        loadTiles();
    }

    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = null;
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            // TODO: read the whole file instead of 16x16
            int col = 0;
            int row = 0;
            while (row < gamePanel.maxScreenRow) {
                String line = reader.readLine();
                String[] numbers = line.split(" ");
                while (col < gamePanel.maxScreenCol) {
                    int num = Integer.parseInt(numbers[col]);
                    map[col][row] = num;
                    col++;
                }
                row++;
                col = 0;
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTiles() {
        for (int i = 0; i < tiles.length; i++) {
            int x = i % gamePanel.maxScreenCol;
            int y = i / gamePanel.maxScreenRow;
            tiles[i] = new Tile(tileTypes[this.map[x][y]], x, y, gamePanel.tileSize);
        }
    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < tiles.length; i++) {

            tiles[i].draw(graphics2D);
//            int x = i % this.gamePanel.maxScreenCol;
//            int y = i / this.gamePanel.maxScreenRow;
//            graphics2D.drawImage(tiles[i].image, x * gamePanel.tileSize, y * gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
