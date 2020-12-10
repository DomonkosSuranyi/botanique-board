package com.botanique;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.botanique.component.Cursor;
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


		engine.addEntity(Tile.create(engine.createEntity(), 50f, 50f));
		engine.addEntity(Tile.create(engine.createEntity(), 200f, 200f));

		final TileMap tilemap = new TileMap(32, 24);

		final Cursor cursor = new Cursor();
		engine.addSystem(new RenderSystem(camera));
		engine.addSystem(new DragSystem(tilemap, cursor));

		Gdx.input.setInputProcessor(new InputProcessorImpl(screenHeight, cursor));
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
