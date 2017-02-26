package de.keeyzar.tutorial.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * @author = Keeyzar on 25.02.2017.
 */
public class Player {
    public Body body;
    float xSpeed, ySpeed, speed = 20;

    public Player(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(50, 3));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);
        body = world.createBody(bodyDef);
        final Fixture fixture = body.createFixture(shape, 1f);
        fixture.setFriction(0);
        shape.dispose();
    }

    public void move(){
        xSpeed = 0;
        ySpeed = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            ySpeed = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            ySpeed = -speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            xSpeed = -speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            xSpeed = speed;
        }
        body.setLinearVelocity(xSpeed, ySpeed);
    }
}

