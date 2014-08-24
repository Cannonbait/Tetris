package cb.pong;

import cb.pong.model.Board;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class main extends ApplicationAdapter {

    ShapeRenderer shapeRenderer;
    Board board;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        board = new Board();

    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        board.render();
        input();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {

        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            board.moveLeft();
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            board.moveRight();
        }

        if (Gdx.input.isKeyPressed(Keys.Z)) {
            board.rotateCounter();
        } else if (Gdx.input.isKeyPressed(Keys.X)) {
            board.rotateClock();
        }
    }

    private void update() {
        board.update();
    }
}
