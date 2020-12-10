package com.botanique.component;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.Optional;

public class Cursor {
    private Vector2 scrPosition = new Vector2(0,0);
    public boolean leftButtonPressed = false;
    public boolean leftButtonJustPressed = false;
    private Entity dragEntity = null;
    private Vector2 dragOffset = Vector2.Zero.cpy();

    public void updatePosition(final float x, final float y) {
        this.scrPosition = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return scrPosition.cpy();
    }

    public void dragEntity(final Entity entity, final Vector2 dragOffset) {
        dragEntity = entity;
        this.dragOffset = dragOffset;
    }

    public void dropEntity() {
        dragEntity = null;
        dragOffset = Vector2.Zero.cpy();
    }

    public Optional<Entity> getDraggedEntity() {
        return Optional.ofNullable(dragEntity);
    }

    public Vector2 getDragOffset() {
        return dragOffset.cpy();
    }
}
