package tile;

import main.Game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Game game;
    int[][] map;
    Tile[][] tiles;
    String[] tileTypes;

    public TileManager(Game game) {
        this.game = game;
        tileTypes = new String[]{"grass", "water", "earth", "sand", "wall", "tree"};
        int totalTiles = game.maxWorldCol * game.maxWorldRow;
        this.tiles = new Tile[game.maxWorldRow][game.maxWorldCol];
        this.map = new int[game.maxWorldRow][game.maxWorldCol];
        loadMap("/maps/map03.txt");
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
            while (row < game.maxWorldRow) {
                String line = reader.readLine();
                String[] numbers = line.split(" ");
                while (col < game.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    map[row][col] = num;
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
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(tileTypes[this.map[i][j]], j, i, game.tileSize);
            }
        }
    }

    public Tile getTile(int row, int col) {
        return this.tiles[row][col];
    }

    public void draw(Graphics2D graphics2D) {

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                int worldX = tiles[i][j].x * game.tileSize;
                int worldY = tiles[i][j].y * game.tileSize;
                if (game.isInPlayerView(worldX, worldY)) {
                    int[] position = game.translateToScreenView(worldX, worldY);
                    tiles[i][j].draw(graphics2D, position[0], position[1]);
                }
            }
        }
    }
}
