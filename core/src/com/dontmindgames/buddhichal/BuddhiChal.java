package com.dontmindgames.buddhichal;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import com.dontmindgames.buddhichal.screens.GameScreen;


public class BuddhiChal extends Game {
    private World world;


    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(200, 200, 200, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}

/*
public class BuddhiChal implements ApplicationListener, InputProcessor {
	private SpriteBatch batch;
    private ShapeRenderer line;
    private Texture stickImage;
    private Texture stoneImage;
    private Texture boardImage;
    private Texture bg;
    private BitmapFont font;
    private Matrix4 matrix4;
    private Array<Rectangle> sticks, stones;

    private OrthographicCamera camera;
    private MouseJoint mouseJoint = null;
    private Body hitBody = null;

    private String message = "Touch something";

    private int w, h;
    private java.util.Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();



	@Override
	public void create () {
        stickImage = new Texture(Gdx.files.internal("stick.png"));
        stoneImage = new Texture(Gdx.files.internal("stone.png"));
        bg = new Texture(Gdx.files.internal("bg-paper-text.jpg"));
        boardImage = new Texture(Gdx.files.internal("board1.png"));

		batch = new SpriteBatch();
        line = new ShapeRenderer();
        font = new BitmapFont();
        matrix4 = new Matrix4();

        sticks = new Array<Rectangle>();
        initializeSticks();
        stones = new Array<Rectangle>();
        initializeStones();

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
        for (int i = 0; i < 5; i++) {
            touches.put(i, new TouchInfo());
        }



	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(200, 200, 200, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawLine(0, 100, 480, 100);
        drawLine(0, 700, 480, 700);

        batch.begin();
        batch.draw(bg, 0, 0);
        batch.draw(boardImage, 40, 200);

        //writeText("Buddhichal", 20, 780);
        for (Rectangle stick: sticks) {
            batch.draw(stickImage, stick.x, stick.y);
        }
        for (Rectangle stone: stones) {
            batch.draw(stoneImage, stone.x, stone.y);
        }

        message = "";
        for (int i = 0; i < 5; i++) {
            if (touches.get(i).touched)
                message += "Finger: " + Integer.toString(i) + "touch at:" + Float.toString(touches.get(i).touchX) + "," + Float.toString(touches.get(i).touchY) + "\n";
        }
        BitmapFont.TextBounds tb = font.getBounds(message);
        float x = w/2 - tb.width/2;
        float y = h/2 + tb.height/2;
        font.drawMultiLine(batch, message, x, y);

        batch.end();
	}

    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }

    private void initializeSticks(){
        placeStick(40, 200);
        placeStick(140, 200);
        placeStick(240, 200);
        placeStick(340, 200);
        placeStick(440, 200);
    }

    private void initializeStones() {
        placeStone(40, 600);
        placeStone(140, 600);
        placeStone(240, 600);
        placeStone(340, 600);
        placeStone(440, 600);
    }

    private void rotate(){
        matrix4.setToRotation(new Vector3(100, 100, 0), 180);
        batch.setTransformMatrix(matrix4);
    }

    private void writeText(CharSequence str, float x, float y) {
        font.setColor(0, 0, 0, 1);
        font.draw(batch, str, x, y);
    }

    private void placeStick(float x, float y){
        Rectangle stick = new Rectangle();
        stick.x = x-20;
        stick.y = y-20;
        stick.width = 40;
        stick.height = 40;
        sticks.add(stick);
    }

    private void placeStone(float x, float y) {
        Rectangle stone = new Rectangle();
        stone.x = x-20;
        stone.y = y-20;
        stone.width = 40;
        stone.height = 40;
        stones.add(stone);
    }

    private void drawBoard() {

        drawLine(40, 200, 440, 200);
        drawLine(40, 300, 440, 300);
        drawLine(40, 400, 440, 400);
        drawLine(40, 500, 440, 500);
        drawLine(40, 600, 440, 600);
        drawLine(40, 200, 40, 600);
        drawLine(140, 200, 140, 600);
        drawLine(240, 200, 240, 600);
        drawLine(340, 200, 340, 600);
        drawLine(440, 200, 440, 600);
        drawLine(40, 200, 440, 600);
        drawLine(440, 200, 40, 600);
        drawLine(40, 400, 240, 200);
        drawLine(240, 200, 440, 400);
        drawLine(440, 400, 240, 600);
        drawLine(240, 600, 40, 400);
    }

    private void drawLine(float x1, float y1, float x2, float y2)
    {
        line.begin(ShapeRenderer.ShapeType.Line);
        line.line(x1, y1, x2, y2);
        line.setColor(0, 0, 0, 1);

        line.end();;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if (pointer < 5) {
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
        }
        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (pointer < 5) {
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = false;
        }
        return true;
    }

    Vector3 testPoint = new Vector3();
    QueryCallback callback = new QueryCallback() {
        @Override
        public boolean reportFixture(Fixture fixture) {
            if (fixture.testPoint(testPoint.x, testPoint.y)){
                hitBody = fixture.getBody();
                return false;
            }
            else
                return true;
        }
    };

    Vector2 target = new Vector2();
    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        if (mouseJoint != null) {
            camera.unproject(testPoint.set(x,y, 0));
            mouseJoint.setTarget(target.set(testPoint.x, testPoint.y));
        }
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }

    private int[] gridPositions(int position){
        int[] coordinates = new int[2];
        switch(position){
            case 1:
                coordinates[0] = 40;
                coordinates[1] = 200;
                break;
            case 2:
                coordinates[0] = 140;
                coordinates[1] = 200;
                break;
            case 3:
                coordinates[0] = 240;
                coordinates[1] = 200;
                break;
            case 4:
                coordinates[0] = 340;
                coordinates[1] = 200;
                break;
            case 5:
                coordinates[0] = 440;
                coordinates[1] = 200;
                break;
            case 6:
                coordinates[0] = 40;
                coordinates[1] = 300;
                break;
            case 7:
                coordinates[0] = 140;
                coordinates[1] = 300;
                break;
            case 8:
                coordinates[0] = 240;
                coordinates[1] = 300;
                break;
            case 9:
                coordinates[0] = 340;
                coordinates[1] = 300;
                break;
            case 10:
                coordinates[0] = 440;
                coordinates[1] = 300;
                break;
            case 11:
                coordinates[0] = 40;
                coordinates[1] = 400;
                break;
            case 12:
                coordinates[0] = 140;
                coordinates[1] = 400;
                break;
            case 13:
                coordinates[0] = 240;
                coordinates[1] = 400;
                break;
            case 14:
                coordinates[0] = 340;
                coordinates[1] = 400;
                break;
            case 15:
                coordinates[0] = 440;
                coordinates[1] = 400;
                break;
            case 16:
                coordinates[0] = 40;
                coordinates[1] = 500;
                break;
            case 17:
                coordinates[0] = 140;
                coordinates[1] = 500;
                break;
            case 18:
                coordinates[0] = 240;
                coordinates[1] = 500;
                break;
            case 19:
                coordinates[0] = 340;
                coordinates[1] = 500;
                break;
            case 20:
                coordinates[0] = 440;
                coordinates[1] = 500;
                break;
            case 21:
                coordinates[0] = 40;
                coordinates[1] = 600;
                break;
            case 22:
                coordinates[0] = 140;
                coordinates[1] = 600;
                break;
            case 23:
                coordinates[0] = 240;
                coordinates[1] = 600;
                break;
            case 24:
                coordinates[0] = 340;
                coordinates[1] = 600;
                break;
            case 25:
                coordinates[0] = 440;
                coordinates[1] = 800;
                break;
        }
        return coordinates;
    }


}
*/