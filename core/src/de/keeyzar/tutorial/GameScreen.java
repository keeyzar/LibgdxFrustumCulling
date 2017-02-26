package de.keeyzar.tutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import de.keeyzar.tutorial.entities.*;

public class GameScreen extends ApplicationAdapter {
	private World world;
	private float fov_width = 20, fov_height = 13;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
    private Player player;
    private Entitiy entityA;
    private Entitiy entitiyB;

    @Override
	public void create () {
		world = new World(new Vector2(0, -10), true);
		camera = new OrthographicCamera(fov_width, fov_height); //your viewport 20x13 in game units
		renderer = new Box2DDebugRenderer();

        new Ground(world);//so we don't fall to the ground
        entityA = new Entitiy(world, new Vector2(47, 6));
        entitiyB = new Entitiy(world, new Vector2(44, 6));

        player = new Player(world);
        new CullingBody(player, world);
        world.setContactListener(new CustomContactListener());
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.move();
		centerCameraOnPlayer();
		renderer.render(world, camera.combined);
		entityA.update();
		entitiyB.update();
        world.step(1/60f, 6, 2);
	}

    private void centerCameraOnPlayer() {
        camera.position.set(player.body.getPosition().x, player.body.getPosition().y, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        renderer.dispose();
    }
}
