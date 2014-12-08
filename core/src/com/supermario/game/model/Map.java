package com.supermario.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Анна on 30.11.2014.
 */

//загрузка карты уровня из файла, движение карты, музыка
public class Map {


    Scanner scanner;
    public String[] linesArray; //схема карты
    private int height, width; //высота и длина карты
    public final int cellSize = 30;//размер клетки игрового поля
    private Sprite wallSprite, bonusSprite, enemySprite;
    public Player player; //ссылка на персонажа

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    public Map() {
        height = 0;
        width = 0;
        wallSprite = new Sprite(new Texture("assets/wall.png"));
        player = new Player(this, 31, 31);
        loadMap();
    }

    private void loadMap(){
        List<String> lines = new ArrayList<String>();
        try {
            scanner = new Scanner(new File("maps/level1.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            height++;
            lines.add(scanner.nextLine());
        }
        linesArray = lines.toArray(new String[0]);
        width = linesArray[0].length();
    }

    public void drawMap(SpriteBatch batch) {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                if (linesArray[i].charAt(j) == 'B') {
                    wallSprite.setX(j * cellSize);
                    wallSprite.setY((height - 1 - i) * cellSize);
                    batch.begin();
                    wallSprite.draw(batch);
                    batch.end();
                }
            }
    }


}
