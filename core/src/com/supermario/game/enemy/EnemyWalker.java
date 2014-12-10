package com.supermario.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.supermario.game.model.Map;

/**
 * Created by Анна on 06.12.2014.
 */
public class EnemyWalker implements IEnemy {
    public Vector2 point, size;
    int dx = -200;
    Map map;
    public Sprite sprite;
    Texture texture = new Texture(Gdx.files.internal("assets/enemy.png"));

    public EnemyWalker(Map m, int x, int y) {
        map = m;
        point = new Vector2(x, y);
        sprite = new Sprite(texture);
        sprite.setX(x);
        sprite.setY(y);
        size = new Vector2(30, 30);
    }

    void Collision(float x, float y) {//проверка на столкновение с объектами
        for (int i = (int) (map.getHeight() - (y + size.y) / map.cellSize); i <= (int) (map.getHeight() - y / map.cellSize); i++)
            for (int j = (int) x / map.cellSize; j <= (int) (x + size.x) / map.cellSize; j++) {
                if (map.charMapArray[i][j] == 'B') {// Стены
                    dx *= -1;
                }
            }
    }

    @Override
    public void moving() {
        Collision(point.x += dx * Gdx.graphics.getDeltaTime(), point.y);
        sprite.setX(point.x);
    }
}
