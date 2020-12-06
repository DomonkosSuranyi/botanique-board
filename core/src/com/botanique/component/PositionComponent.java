package com.botanique.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    private Vector2 pos;

    public PositionComponent (float x, float y) {
        pos = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return pos.cpy();
    }

    public void setPosition(final Vector2 newPosition) {
        this.pos = newPosition.cpy();
    }
}
