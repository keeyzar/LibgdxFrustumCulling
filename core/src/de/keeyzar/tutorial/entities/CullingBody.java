package de.keeyzar.tutorial.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * @author = Keeyzar on 26.02.2017.
 */
public class CullingBody {
    public CullingBody(Player player, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(player.body.getPosition());
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(4, 4); //size of

        Body body = world.createBody(bodyDef);
        final Fixture fixture = body.createFixture(polygonShape, 1.2f);
        fixture.setSensor(true); //so it does not collide with any objects
        Userdata userdata = new Userdata();
        userdata.isCullingBody = true;
        fixture.setUserData(userdata);
        polygonShape.dispose();

        MassData massData = body.getMassData();
        massData.mass = 0.00000000001f; //so it wont increase the weight, and with that the falling speed of the player
        body.setMassData(massData);

        //with that joint we'll stick the CullingBody to the player
        RevoluteJointDef cullingJoint = new RevoluteJointDef();
        cullingJoint.type = JointDef.JointType.RevoluteJoint;
        cullingJoint.bodyA = player.body;
        cullingJoint.bodyB = body;
        cullingJoint.localAnchorA.set(0,0);
        cullingJoint.localAnchorB.set(0,0);
        world.createJoint(cullingJoint);

    }

}
