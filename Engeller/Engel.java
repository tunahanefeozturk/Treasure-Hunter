package Engeller;

import java.awt.Graphics2D;
import java.util.Random;
import prolab_3.otonom_hazine_avcisi.Lokasyon;
import prolab_3.otonom_hazine_avcisi.Oyun;

/**
 *
 * @author oztur
 */
public abstract class Engel {

    Oyun oyun;
    Lokasyon wlok;
    Lokasyon slok;
    Random r=new Random();
    boolean isDisc = false;
    int nx, ny;

    public Engel(Oyun oyun) {
        this.oyun = oyun;

        slok = new Lokasyon();

    }

    public void getImage() {
    }

    public void draw(Graphics2D g2) {
        if (oyun.ui.isIsfinish()) {
            setWorldLoc();
             reasetLok();
            
        } else  {
           setWorldLoc();
        }
    }

    public abstract int[] isSpace(int x, int y);

    public void setWorldLoc() {
        int x = wlok.getX();
        int y = wlok.getY();
        int ex = x - oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX();
        int ey = y - oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY();
        slok = new Lokasyon(ex, ey);
    }

    public void reasetLok() {
        System.out.println(getClass().toString()+nx+"   "+ny);
        slok.setX(nx*oyun.kareBoy );
        slok.setY(ny*oyun.kareBoy );
    }
}
