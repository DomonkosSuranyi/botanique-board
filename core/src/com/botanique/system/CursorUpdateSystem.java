package com.botanique.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.botanique.component.CursorComponent;
import com.botanique.component.PositionComponent;

public class CursorUpdateSystem extends IteratingSystem {
    private final int screenHeight;

    private final ComponentMapper<PositionComponent> positionCM = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<CursorComponent> cursorCM = ComponentMapper.getFor(CursorComponent.class);

    public CursorUpdateSystem(final int screenWidth, final int screenHeight) {
        super(Family.all(CursorComponent.class, PositionComponent.class).get());
        this.screenHeight = screenHeight;
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        PositionComponent pos = positionCM.get(entity);
        pos.setPosition(new Vector2(Gdx.input.getX(), screenHeight - Gdx.input.getY()));

        CursorComponent cur = cursorCM.get(entity);
        cur.leftButtonPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        cur.leftButtonJustPressed = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
    }
}
