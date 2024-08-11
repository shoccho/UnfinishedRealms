package tile;

import main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class TileManager {
    GamePanel gamePanel;
    int[][] map;
    Tile[] tiles;
    String[] tileTypes;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tileTypes = new String[]{"grass", "water", "earth", "sand", "wall", "tree"};
        int totalTiles = gamePanel.maxWorldCol * gamePanel.maxWorldRow;
        this.tiles = new Tile[totalTiles];
        this.map = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        loadMap("/maps/map02.txt");
        loadTiles();
    }

    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = null;
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            int col = 0;
            int row = 0;
            while (row < gamePanel.maxWorldRow) {
                String line = reader.readLine();
                String[] numbers = line.split(" ");
                while (col < gamePanel.maxWorldCol) {
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
            int x = i % gamePanel.maxWorldCol;
            int y = i / gamePanel.maxWorldRow;
            tiles[i] = new Tile(tileTypes[this.map[x][y]], x, y, gamePanel.tileSize);
        }
    }
    public Tile getTile(int index){

        return this.tiles[index];
    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < tiles.length; i++) {
            int worldX = tiles[i].x * gamePanel.tileSize;
            int worldY = tiles[i].y * gamePanel.tileSize;
            if(gamePanel.inPlayerView(worldX, worldY)){
                int[] position = gamePanel.translateToScreenView(worldX , worldY);
                tiles[i].draw(graphics2D, position[0], position[1] );
            }
      }
    }
}
