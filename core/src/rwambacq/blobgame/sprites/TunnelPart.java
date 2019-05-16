package rwambacq.blobgame.sprites;

public class TunnelPart {
    private int holeX;
    private int holeWidth;
    private TunnelPart next;

    public TunnelPart(int holeX, int holeWidth, TunnelPart next){
        this.holeX = holeX;
        this.holeWidth = holeWidth;
        this.next = next;
    }

    public int getHoleWidth() {
        return holeWidth;
    }

    public int getHoleX() {
        return holeX;
    }

    public TunnelPart getNext() {
        return next;
    }
}
