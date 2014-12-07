package com.supermario.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Анна on 30.11.2014.
 */

//загрузка карты уровня из файла, движение карты, музыка
public class Map {

    List<String> lines = new ArrayList<String>();
    Scanner scanner;
    public String[] linesArray;
    int h, w;
    Sprite wallSprite, bonusSprite, enemySprite;
    public Player player; //ссылка на персонажа

    public Map() {
        h = 0;
        w = 0;
        wallSprite = new Sprite(new Texture("assets/wall.png"));
        player = new Player(this, 50, 31);
        try {
            scanner = new Scanner(new File("maps/level1.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            h++;
            lines.add(scanner.nextLine());
        }
        linesArray = lines.toArray(new String[0]);
        w = linesArray[0].length();
    }

    public void drawMap(SpriteBatch batch) {
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++) {
                if (linesArray[i].charAt(j) == 'B') {
                    wallSprite.setX(j * 30);
                    wallSprite.setY((h-1-i) * 30);
                    batch.begin();
                    wallSprite.draw(batch);
                    batch.end();
                }
            }
    }


}
