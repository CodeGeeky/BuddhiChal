package com.dontmindgames.buddhichal.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hemant on 4/21/15.
 */
public class Stick {
   /* private Texture stickImage  = new Texture(Gdx.files.internal("stick.png"));*/

    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle(;
    static final float SIZE = 0.5f;

    public Stick(Vector2 position) {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }
}
