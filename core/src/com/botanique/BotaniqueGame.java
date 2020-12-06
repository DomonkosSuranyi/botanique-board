package com.botanique;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.botanique.component.CursorComponent;
import com.botanique.component.MovementComponent;
import com.botanique.component.PositionComponent;
import com.botanique.component.VisualComponent;
import com.botanique.entity.Tile;
import com.botanique.system.*;

public class BotaniqueGame extends ApplicationAdapter {
	private PooledEngine engine;
	private final int screenWidth;
	private final int screenHeight;

	public BotaniqueGame(final int width, final int height) {
		screenWidth = width;
		screenHeight = height;
	}

	@Override
	public void create() {
		OrthographicCamera camera = new OrthographicCamera(screenWidth, screenHeight);
		camera.position.set(screenWidth/2f, screenHeight/2f, 0);
		camera.update();

		engine = new PooledEngine();


		Entity cursor = engine.createEntity();
		cursor.add(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()));
		cursor.add(new CursorComponent());

		engine.addEntity(Tile.create(engine.createEntity(), 50f, 50f));
		engine.addEntity(cursor);

		engine.addSystem(new RenderSystem(camera));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new CursorUpdateSystem(screenWidth, screenHeight));
		engine.addSystem(new CollisionSystem());
		engine.addSystem(new DragSystem(cursor));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.6f, 0, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
	}
}
