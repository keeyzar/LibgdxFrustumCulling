package de.keeyzar.tutorial.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * @author = Keeyzar on 26.02.2017.
 */
public class Entitiy {
    private final Userdata userdata; // to update our entity
    private final Body body;

    public Entitiy(World world, Vector2 pos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(pos));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);
        body = world.createBody(bodyDef);
        final Fixture fixture = body.createFixture(shape, 1f);
        userdata = new Userdata();
        fixture.setUserData(userdata); //must be set on fixture's, because contact listener is fixture based
        shape.dispose();
    }

    public void update(){
        if(userdata.isInView){
            body.setAngularVelocity(1);
        } else {
            body.setAngularVelocity(0);
        }
    }
}
