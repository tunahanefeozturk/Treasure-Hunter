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
public class Duvarlar extends Engel {

    BufferedImage image;

    public Duvarlar(Oyun oyun) {
        super(oyun);
        int x = r.nextInt(oyun.maxWorldX - 10);
        int y = r.nextInt(oyun.maxWorldX - 1);

        int[] a = isSpace(x, y);
        nx = a[0];
        ny = a[1];

        for (int i = nx; i < nx + 10; i++) {
            if (oyun.map[i][ny] == 0) {
                oyun.map[i][ny] = 1;
            }

        }

        wlok = new Lokasyon(nx * oyun.kareBoy, ny * oyun.kareBoy);
        setWorldLoc();
        getImage();
    }

    @Override
    public void getImage() {
        try {
            image = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteZemin\\duvar.png"));
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
                    || (wlok.getX() + (9 * oyun.kareBoy) > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() + (9 * oyun.kareBoy) < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() < l.getY() + 3 * oyun.kareBoy)
                    || (wlok.getX() > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() + oyun.kareBoy > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() + oyun.kareBoy < l.getY() + 3 * oyun.kareBoy)
                    || (wlok.getX() + (9 * oyun.kareBoy) > l.getX() - 3 * oyun.kareBoy
                    && wlok.getX() + (9 * oyun.kareBoy) < l.getX() + 3 * oyun.kareBoy
                    && wlok.getY() + oyun.kareBoy > l.getY() - 3 * oyun.kareBoy
                    && wlok.getY() + oyun.kareBoy < l.getY() + 3 * oyun.kareBoy)) {
                g2.drawImage(image, slok.getX(), slok.getY(), oyun.kareBoy * 10, oyun.kareBoy, null);
                if (isDisc == false) {
                    oyun.ui.show("Duvar keşfedildi.");
                    isDisc = true;
                }
            }

        }

    }

    @Override
    public int[] isSpace(int x, int y) {

        boolean bos = true;
        for (int i = x - 1; i < x + 12; i++) {
            for (int j = y; j < y + 1; j++) {
                if (i >= oyun.maxWorldX || y >= oyun.maxWorldX || i < 0) {
                    bos = false;
                    break;
                } else if (oyun.map[i][y] != 0) {
                    bos = false;
                    break;
                }

            }

        }
        if (bos == false) {
            int nx = r.nextInt(oyun.maxWorldX - 9);
            int ny = r.nextInt(oyun.maxWorldX - 1);

            return isSpace(nx, ny);

        } else {
            int[] a = {x, y};
            return a;
        }

    }

}
