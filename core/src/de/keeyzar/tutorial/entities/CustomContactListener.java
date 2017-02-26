package de.keeyzar.tutorial.entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * @author = Keeyzar on 26.02.2017.
 */
public class CustomContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

    @Override
    public void beginContact(Contact contact) {
        culling(contact, true);
    }

    @Override
    public void endContact(Contact contact) {
        culling(contact, false);
    }

    //here's the magic.
    private void culling(Contact contact, boolean isBeginContact) {
        final Object userDataA = contact.getFixtureA().getUserData();
        final Object userDataB = contact.getFixtureB().getUserData();

        //if one userData is null, we do not need to cull anything. Explanation:
        //a = culling, b = null: where to set the inView variable?
        //a = object, b = null: it's not the culling which is colliding
        if(userDataA != null && userDataB != null){
            //we can cast, because we do not set any other userdata.
            final Userdata userDataACast = (Userdata) userDataA;
            final Userdata userDataBCast = (Userdata) userDataB;

            //now only find out, which one is the CullingBody. There are not two CullingBodys, so the other must
            //be the item, which we set visible
            if(userDataACast.isCullingBody){
                ((Userdata)userDataB).isInView = isBeginContact;
            } else if(userDataBCast.isCullingBody){
                ((Userdata)userDataA).isInView = isBeginContact;
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //not needed in this tutorial
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //not needed in this tutorial
    }
}
