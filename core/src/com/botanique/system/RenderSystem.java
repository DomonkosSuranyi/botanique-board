package com.botanique.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.botanique.component.PositionComponent;
import com.botanique.component.VisualComponent;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<VisualComponent> vm = ComponentMapper.getFor(VisualComponent.class);

    public RenderSystem (OrthographicCamera camera) {
        batch = new SpriteBatch();

        this.camera = camera;
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VisualComponent.class).get());
    }

    @Override
    public void removedFromEngine (Engine engine) {

    }

    @Override
    public void update (float deltaTime) {
        PositionComponent position;
        VisualComponent visual;

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        for (final Entity e : entities) {
            position = pm.get(e);
            visual = vm.get(e);

            batch.draw(visual.region, position.getPosition().x, position.getPosition().y, visual.width, visual.height);
        }

        batch.end();
    }
}

