package tile;

import main.Game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class TileManager {
    Game game;
    int[][] map;
    Tile[] tiles;
    String[] tileTypes;

    public TileManager(Game game) {
        this.game = game;
        tileTypes = new String[]{"grass", "water", "earth", "sand", "wall", "tree"};
        int totalTiles = game.maxWorldCol * game.maxWorldRow;
        this.tiles = new Tile[totalTiles];
        this.map = new int[game.maxWorldCol][game.maxWorldRow];
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
            while (row < game.maxWorldRow) {
                String line = reader.readLine();
                String[] numbers = line.split(" ");
                while (col < game.maxWorldCol) {
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
            int x = i % game.maxWorldCol;
            int y = i / game.maxWorldRow;
            tiles[i] = new Tile(tileTypes[this.map[x][y]], x, y, game.tileSize);
        }
    }
    public Tile getTile(int index){

        return this.tiles[index];
    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < tiles.length; i++) {
            int worldX = tiles[i].x * game.tileSize;
            int worldY = tiles[i].y * game.tileSize;
            if(game.inPlayerView(worldX, worldY)){
                int[] position = game.translateToScreenView(worldX , worldY);
                tiles[i].draw(graphics2D, position[0], position[1] );
            }
      }
    }
}
