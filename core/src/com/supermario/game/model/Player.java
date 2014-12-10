package com.supermario.game.model;

import com.badlogic.gdx.Gdx;
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
    public final Vector2 playerSize = new Vector2(54,54);//Размер спрайта персонажа
    public final int Gravity = 20;//Влияние гравитации
    public final int SpeedX = 300;//Скорость движения по оси Х
    public final int SpeedY = 400;//Скорость движения по оси Y
    Texture texture = new Texture(Gdx.files.internal("assets/player.png"));

    public Player(Map map, float x, float y) {
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

    float Collision(char dir, float x, float y) {//проверка на столкновение с объектами
        for (int i = (int) (map.getHeight() - (y + playerSize.y) / map.cellSize); i <= (int) (map.getHeight() - y / map.cellSize); i++)
            for (int j = (int) x / map.cellSize; j <= (int) (x + playerSize.x) / map.cellSize; j++) {
                if (map.charMapArray[i][j] == 'B') {// Стены
                    if (dx > 0 && dir == 'x') x = j * map.cellSize - playerSize.x - 1;
                    if (dx < 0 && dir == 'x') x = (j + 1) * map.cellSize + 1;
                    if (dy > 0 && dir == 'y') {
                        y = (map.getHeight() - i - 1) * map.cellSize - playerSize.y - 1;
                        dy = 0;
                    }
                    if (dy < 0 && dir == 'y') {
                        grounded = true;
                        dy = 0;
                        y = (map.getHeight() - i) * map.cellSize + 1;
                    }
                }
                if (map.charMapArray[i][j] == 'D') { // Бонусы
                    map.charMapArray[i][j] = ' ';
                }
            }
        if (dir == 'x') return x;
        else return y;
    }

    public void playerMove() {
        point.x = Collision('x', point.x + dx * Gdx.graphics.getDeltaTime(), point.y);
        playerSprite.setX(point.x);
        if (grounded && (map.charMapArray[(int) ((map.getHeight() - point.y / map.cellSize) + 1)][(int) point.x / map.cellSize]) == ' ') {
            dy = -100;
            grounded = false;
        }
        dx = 0;
        if (!grounded) {
            point.y = Collision('y', point.x, point.y + dy * Gdx.graphics.getDeltaTime());
            playerSprite.setY(point.y);
            dy -= Gravity;
        }
    }
}