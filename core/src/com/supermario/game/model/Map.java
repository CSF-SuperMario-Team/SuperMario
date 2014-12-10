package com.supermario.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.supermario.game.enemy.EnemyWalker;
import com.supermario.game.enemy.IEnemy;

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
    public boolean isDrawing = false;
    private SpriteBatch batchMap;
    private String[] linesArray; //схема карты
    public char[][] charMapArray;
    private int height, width; //высота и длина карты
    public final int cellSize = 30;//размер клетки игрового поля
    private Sprite wallSprite, bonusSprite, enemySprite;
    public Player player; //ссылка на персонажа
    public ArrayList<EnemyWalker> enemies = new ArrayList<EnemyWalker>();

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Map(SpriteBatch batch) {
        height = 0;
        width = 0;
        batchMap = batch;
        wallSprite = new Sprite(new Texture("assets/wall.png"));
        bonusSprite = new Sprite(new Texture("assets/bonus.png"));
        enemySprite = new Sprite(new Texture("assets/enemy.png"));
        player = new Player(this, 31, 31);
        loadMap();
    }

    private void loadMap() {
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
        charMapArray = new char[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                charMapArray[i][j] = linesArray[i].charAt(j);
            }
    }

    public void drawMap() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                switch (charMapArray[i][j]) {
                    case 'B':
                        setSprite(wallSprite, j, i);
                        break;
                    case 'V':
                        if (!isDrawing)
                            enemies.add(new EnemyWalker(this, j * 30, (height - i - 1) * 30));
                        break;
                    case 'D':
                        setSprite(bonusSprite, j, i);
                        break;
                    default:
                        break;
                }
            }
        isDrawing = true;
    }

    private void setSprite(Sprite sprite, int x, int y) {
        sprite.setX(x * cellSize);
        sprite.setY((height - 1 - y) * cellSize);
        batchMap.begin();
        sprite.draw(batchMap);
        batchMap.end();
    }

}