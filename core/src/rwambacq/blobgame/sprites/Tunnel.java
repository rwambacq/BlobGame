package rwambacq.blobgame.sprites;

import com.badlogic.gdx.Gdx;

import rwambacq.blobgame.var.Noise;

public class Tunnel {
    private final static int TUNNELPART_AMOUNT = 100;
    public final static int TUNNELPART_HEIGHT = Gdx.graphics.getHeight()/TUNNELPART_AMOUNT;
    private TunnelPart top;
    private int newTunnelPartCounter;

    Noise noise;

    private double noiseX = 0;
    private double noiseY = 0;

    public Tunnel(){
        newTunnelPartCounter = 0;
        noise = new Noise(1337);
        noiseX = noise.eval(noiseX, noiseY);
        noiseY +=0.01;
        top = new TunnelPart(100 + Math.abs((int)Math.round(noiseX * 500)), 350 + (int)Math.round(noiseX * 150), null); // Eerste deel tunnel wordt in het midden bovenaan getekend met breedte 500
    }

    public void update(float dt){
        newTunnelPartCounter = (newTunnelPartCounter + 1)%2; // Als de tunnel trager moet gaan, zet hier een grotere modulo
        if(newTunnelPartCounter == 0){
            noiseX = noise.eval(noiseX, noiseY);
            noiseY +=0.01;
            TunnelPart newTop = new TunnelPart(100 + Math.abs((int)Math.round(noiseX * 500)), 350 + (int)Math.round(noiseX * 150), top); // Pas hier de argumenten aan om variatie te krijgen bij de tunnel
            top = newTop;
        }
    }

    public TunnelPart getTop() {
        return top;
    }
}
