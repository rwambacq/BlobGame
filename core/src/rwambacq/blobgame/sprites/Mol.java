package rwambacq.blobgame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Mol {
    private static final int GRAVITY = -15;
    private static final int HORIZONTAL_DECELLERATION = 3;
    private Vector3 position;
    private Vector3 velocity;
    private Texture mol;
    private int rotation;

    public Mol(int x, int y){
        mol = new Texture("minimol.png");
        position = new Vector3(x-mol.getWidth()/2, y-mol.getHeight()/2, 0);
        velocity = new Vector3(0,0,0);
        rotation = 0;
    }

    public void update(float dt){
        if(position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }
        if(position.x > 0 && position.x < Gdx.graphics.getWidth()){
            if(velocity.x < 15 && velocity.x > -15){
                velocity.x = 0;
            }
            if(velocity.x < 0){
                velocity.add(HORIZONTAL_DECELLERATION, 0, 0);
            } else if (velocity.x > 0){
                velocity.add(-HORIZONTAL_DECELLERATION, 0, 0);
            }
        }
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);

        if(position.y < 0){
            position.y = 0;
        }
        if(position.x < 0){
            position.x = 0;
        }
        if(position.x+mol.getWidth() > Gdx.graphics.getWidth()){
            position.x = Gdx.graphics.getWidth()-mol.getWidth();
        }

        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return mol;
    }

    public void jump(boolean left){
        if(left){
            velocity.x = -300;
            rotation = 20;
        } else {
            velocity.x = 300;
            rotation = -20;
        }
        velocity.y = 400;
    }

    public int getRotation() {
        return rotation;
    }
}
