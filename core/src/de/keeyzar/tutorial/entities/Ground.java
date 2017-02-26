package de.keeyzar.tutorial.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * @author = Keeyzar on 25.02.2017.
 */
public class Ground {
    private World world;


    public Ground(World world){
        this.world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(50, 0.5f));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(100, 1);
        body.createFixture(shape, 0);
    }
}
