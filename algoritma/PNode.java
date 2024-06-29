package algoritma;

/**
 *
 * @author oztur
 */
public class PNode {

    private PNode ust;
    private int x;
    private int y;
    private int g;
    private int f;
    private int h;
    private boolean coll;
    private boolean bos;
    private boolean kont;

    public PNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the ust
     */
    public PNode getUst() {
        return ust;
    }

    /**
     * @param ust the ust to set
     */
    public void setUst(PNode ust) {
        this.ust = ust;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the g
     */
    public int getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * @return the f
     */
    public int getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(int f) {
        this.f = f;
    }

    /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * @return the coll
     */
    public boolean isColl() {
        return coll;
    }

    /**
     * @param coll the coll to set
     */
    public void setColl(boolean coll) {
        this.coll = coll;
    }

    /**
     * @return the bos
     */
    public boolean isBos() {
        return bos;
    }

    /**
     * @param bos the bos to set
     */
    public void setBos(boolean bos) {
        this.bos = bos;
    }

    /**
     * @return the kont
     */
    public boolean isKont() {
        return kont;
    }

    /**
     * @param kont the kont to set
     */
    public void setKont(boolean kont) {
        this.kont = kont;
    }

}
