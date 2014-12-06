package com.supermario.game.model;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by Анна on 30.11.2014.
 */

//методы движение, анимация, взаимодействие с картой, взаимодействие с врагами
public class Player {

    Map map; //карта
    Vector2 point; //вектор, хранит текущее положение игрока
    boolean grounded = false; // положение игрока - на земле ли он
    public int countLife = 3; //количество жизней
    public int count = 0;

    public Player (Map map, float x, float y) {
        this.map = map;
        point = new Vector2(x, y);
        //отрисовка игрока в координатах х,у
    }

    public void left() {
        //движение влево - перемещение координат влево, анимация игрока
    }

    public void right() {
    }

    public void up() {
        //прыжок
    }
}
