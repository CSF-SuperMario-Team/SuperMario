package com.supermario.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by Анна on 30.11.2014.
 */

//методы движение, анимация, взаимодействие с картой, взаимодействие с врагами
public class Player {

    public int dx, dy; // ускорение по оси Х и Y
    Map map; //карта
    public Vector2 point; //вектор, хранит текущее положение игрока
    public boolean grounded = true; // положение игрока: true - на земле, false - в воздухе
    public int countLife = 3; //количество жизней
    public int count = 0;
    public Sprite playerSprite;
    Texture texture = new Texture(Gdx.files.internal("assets/player.png"));

    public Player(Map map, final float x, float y) {
        dx = 0;
        dy = 0;
        this.map = map;
        point = new Vector2(x, y);
        playerSprite = new Sprite(texture) {{
            setX(point.x);
            setY(point.y);
        }};
        //отрисовка игрока в координатах х,у
    }


    public void left() {
    }

    public void right() {
    }

    public void up(int f) {
    }

    float Collision(char dir, float x, float y) {
        for (int i = (int) (18 - (y + 54) / 30); i <= (int) (18 - y / 30); i++)
            for (int j = (int) x / 30; j <= (int) (x + 54) / 30; j++) {
                if (map.linesArray[i].charAt(j) == 'B') {
                    if (dx > 0 && dir == 'x') x = j * 30 - 54 - 1;
                    if (dx < 0 && dir == 'x') x = (j + 1) * 30 + 1;
                    if (dy > 0 && dir == 'y') {
                        y = (18 - i - 1) * 30 - 54 - 1;
                        dy = 0;
                    }
                    if (dy < 0 && dir == 'y') {
                        grounded = true;
                        dy = 0;
                        y = (18 - i) * 30 + 1;
                    }
                }
            }
        if (dir == 'x') return x;
        else return y;
    }

    public void playerMove() {
        point.x = Collision('x', point.x + dx * Gdx.graphics.getDeltaTime(), point.y);
        playerSprite.setX(point.x);
        if (grounded && (map.linesArray[(int) ((18 - point.y / 30) + 1)]).charAt((int) point.x / 30) == ' ') {
            dy = -400;
            grounded = false;
        }
        dx = 0;
        if (!grounded) {
            point.y = Collision('y', point.x, point.y + dy * Gdx.graphics.getDeltaTime());
            playerSprite.setY(point.y);
            dy -= 20;
        }
    }
}