package Engeller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import prolab_3.otonom_hazine_avcisi.Lokasyon;
import prolab_3.otonom_hazine_avcisi.Oyun;

/**
 *
 * @author oztur
 */
public class Kayalar extends Engel {

    BufferedImage rock;

    int size;

    public Kayalar(Oyun oyun) {
        super(oyun);
        size = r.nextInt(2, 4);
        int x = r.nextInt(oyun.maxWorldX - size);
        int y = r.nextInt(oyun.maxWorldX - size);

        int[] a = isSpace(x, y);
        nx = a[0];
        ny = a[1];

        for (int j = ny; j < ny + size; j++) {
            for (int i = nx; i < nx + size; i++) {
                if (oyun.map[i][j] == 0) {
                    oyun.map[i][j] = 1;
                }

            }
        }
        wlok = new Lokasyon(nx * oyun.kareBoy, ny * oyun.kareBoy);

        setWorldLoc();
        getImage();
    }

    @Override
    public void getImage() {
        try {
            rock = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteRock\\kaya.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D g2) {

        super.draw(g2);

        for (Lokasyon l : oyun.keyH.traceLok) {

            if ((wlok.getX() > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() < l.getY() + 3 * oyun.kareBoy)
                    || (wlok.getX() + (size * oyun.kareBoy) > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() + (size * oyun.kareBoy) < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() < l.getY() + 3 * oyun.kareBoy)
                    || (wlok.getX() > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() + (size * oyun.kareBoy) > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() + (size * oyun.kareBoy) < l.getY() + 3 * oyun.kareBoy)
                    || (wlok.getX() + (size * oyun.kareBoy) > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() + (size * oyun.kareBoy) < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() + (size * oyun.kareBoy) > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() + (size * oyun.kareBoy) < l.getY() + 3 * oyun.kareBoy)) {
                g2.drawImage(rock, slok.getX(), slok.getY(), oyun.kareBoy * size, oyun.kareBoy * size, null);
                if (isDisc == false) {
                    oyun.ui.show("Kaya keşfedildi.");
                    isDisc = true;
                }
            }
        }

    }

    @Override
    public int[] isSpace(int x, int y) {

        boolean bos = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= oyun.maxWorldX || j >= oyun.maxWorldX) {
                    bos = false;
                    break;
                } else if (oyun.map[i][j] != 0) {
                    bos = false;
                    break;
                }

            }

        }
        if (bos == false) {
            int nx = r.nextInt(oyun.maxWorldX - size + 1);
            int ny = r.nextInt(oyun.maxWorldX - size + 1);

            return isSpace(nx, ny);

        } else {
            int[] a = {x, y};
            return a;
        }

    }

}
