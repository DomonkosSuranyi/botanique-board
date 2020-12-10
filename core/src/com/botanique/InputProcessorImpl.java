package com.botanique;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.botanique.component.Cursor;

public class InputProcessorImpl implements InputProcessor {
    private final Cursor cursor;
    private final int screenY;

    public InputProcessorImpl(final int screenHeight, final Cursor cursor) {
        this.cursor = cursor;
        this.screenY = screenHeight;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            cursor.leftButtonPressed = true;
            cursor.leftButtonJustPressed = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            cursor.leftButtonPressed = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        cursor.updatePosition(screenX, this.screenY - screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        cursor.updatePosition(screenX, this.screenY - screenY);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
