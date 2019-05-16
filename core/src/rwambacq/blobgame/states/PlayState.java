package rwambacq.blobgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

import rwambacq.blobgame.sprites.Mol;
import rwambacq.blobgame.sprites.Tunnel;
import rwambacq.blobgame.sprites.TunnelPart;

public class PlayState extends State {

    private Mol mol;
    private Tunnel tunnel;

    private Texture tunnelPixel;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        mol = new Mol(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        tunnel = new Tunnel();
        tunnelPixel = new Texture("TunnelPixel.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() < Gdx.graphics.getWidth()/2){
                mol.jump(true);
            } else {
                mol.jump(false);
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        mol.update(dt);
        tunnel.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(mol.getTexture(), mol.getPosition().x, mol.getPosition().y,
                mol.getTexture().getWidth()/2, mol.getTexture().getHeight()/2,
                mol.getTexture().getWidth(), mol.getTexture().getHeight(),
                1, 1,
                mol.getRotation(),
                0,0,
                mol.getTexture().getWidth(), mol.getTexture().getHeight(),
                false, false);

        TunnelPart next = tunnel.getTop();
        int currentHeight = Gdx.graphics.getHeight();

        while(next != null){
            sb.draw(tunnelPixel, 0, currentHeight,
                    next.getHoleX(), Tunnel.TUNNELPART_HEIGHT);
            sb.draw(tunnelPixel, next.getHoleX()+next.getHoleWidth(), currentHeight,
                    Gdx.graphics.getWidth() - next.getHoleWidth() - next.getHoleX(), Tunnel.TUNNELPART_HEIGHT);
            next = next.getNext();
            currentHeight -= Tunnel.TUNNELPART_HEIGHT;
        }
        sb.end();
    }

    @Override
    public void dispose() {
        mol.getTexture().dispose();
    }
}
