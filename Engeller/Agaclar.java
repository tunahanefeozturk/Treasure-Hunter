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
public class Agaclar extends Engel {

    BufferedImage yaz1;
    BufferedImage yaz2;
    BufferedImage yaz3;
    BufferedImage yaz4;
    BufferedImage kis1;
    BufferedImage kis2;
    BufferedImage kis3;
    BufferedImage kis4;
    int size;
    boolean area;

    public Agaclar(Oyun oyun) {
        super(oyun);
        size = r.nextInt(2, 6);
        int x = r.nextInt(oyun.maxWorldX - size + 1);
        int y = r.nextInt(oyun.maxWorldX - size + 1);

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
        if (nx >= oyun.maxWorldX / 2) {
            area = true;
        } else {
            area = false;
        }

        getImage();
    }

    @Override
    public void getImage() {
        try {
            yaz1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeYaz.png"));
            yaz2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeYaz3x3.png"));
            yaz3 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeYaz4x4.png"));
            yaz4 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeYaz5x5.png"));
            kis1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeKıs.png"));
            kis2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeKıs3x3.png"));
            kis3 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeKıs4x4.png"));
            kis4 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteTree\\treeKıs5x5.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        super.draw(g2);

        BufferedImage image = null;
        switch (size) {
            case 2:
                if (area == true) {
                    image = yaz1;
                } else {
                    image = kis1;
                }
                break;
            case 3:
                if (area == true) {
                    image = yaz2;
                } else {
                    image = kis2;
                }
                break;
            case 4:
                if (area == true) {
                    image = yaz3;
                } else {
                    image = kis3;
                }
                break;
            case 5:
                if (area == true) {
                    image = yaz4;
                } else {
                    image = kis4;
                }
                break;
        }

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
                g2.drawImage(image, slok.getX(), slok.getY(), oyun.kareBoy * size, oyun.kareBoy * size, null);
                if (isDisc == false) {
                    oyun.ui.show("Ağaç keşfedildi.");
                    isDisc = true;
                }
            }

        }

    }

    public int[] isSpace(int x, int y) {
        boolean bos = true;
        for (int i = x; i < x + size + 1; i++) {
            for (int j = y; j < y + size + 1; j++) {
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
