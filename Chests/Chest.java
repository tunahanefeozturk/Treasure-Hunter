package Chests;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import prolab_3.otonom_hazine_avcisi.Lokasyon;
import prolab_3.otonom_hazine_avcisi.Oyun;

/**
 *
 * @author oztur
 */
public abstract class Chest implements Comparable<Chest> {

    private Lokasyon lok;
    int priority;
    BufferedImage image;
    Oyun oyun;
    Random r = new Random();
    private boolean isFind = false;

    public Chest(Oyun oyun) {
        this.oyun = oyun;
        int x = r.nextInt(oyun.maxWorldX - 1);
        int y = r.nextInt(oyun.maxWorldX - 1);

        int[] a = isSpace(x, y);

        oyun.map[a[0]][a[1]] = 2;

        lok = new Lokasyon(a[0] * oyun.kareBoy, a[1] * oyun.kareBoy);
        getChestImg();
    }

    public void getChestImg() {
    }

    public void drawChest(Graphics2D g2) {
        if (this.isFind == false) {

            int sx = getLok().getX() - oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX();
            int sy = getLok().getY() - oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY();

            int lc = 0;
            for (Lokasyon l : oyun.keyH.traceLok) {

                if (getLok().getX()+oyun.kareBoy > l.getX() - 3 * oyun.kareBoy
                        && getLok().getX() -oyun.kareBoy< l.getX() + 3 * oyun.kareBoy
                        && getLok().getY() +oyun.kareBoy> l.getY() - 3 * oyun.kareBoy
                        && getLok().getY() -oyun.kareBoy< l.getY() + 3 * oyun.kareBoy) {
                    g2.drawImage(image, sx, sy, oyun.kareBoy, oyun.kareBoy, null);
                    if (!oyun.krktr.getChestQ().contains(this)) {
                        oyun.krktr.getChestQ().add(this);
                        oyun.krktr.cyolda=false;
                    }
                    

                }

            }
        }

    }

    public int[] isSpace(int x, int y) {

        boolean bos = true;

        if (oyun.map[x][y] == 1) {
            bos = false;
        }

        if (bos == false) {
            int nx = r.nextInt(oyun.maxWorldX - 1);
            int ny = r.nextInt(oyun.maxWorldX - 1);

            return isSpace(nx, ny);

        } else {
            int[] a = {x, y};
            return a;
        }
    }

    @Override
    public int compareTo(Chest o) {
        return Integer.compare(this.priority, o.priority);
    }

    /**
     * @return the lok
     */
    public Lokasyon getLok() {
        return lok;
    }

    /**
     * @param lok the lok to set
     */
    public void setLok(Lokasyon lok) {
        this.lok = lok;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setIsFind(boolean isFind) {
        this.isFind = isFind;
    }

    public boolean getIsFind() {
        return this.isFind;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "  " + this.lok.getX() / oyun.kareBoy + " - " + this.lok.getY() / oyun.kareBoy;
    }

}
