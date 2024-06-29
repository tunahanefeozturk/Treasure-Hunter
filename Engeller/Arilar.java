package Engeller;

import java.awt.image.BufferedImage;
import prolab_3.otonom_hazine_avcisi.Lokasyon;
import prolab_3.otonom_hazine_avcisi.Oyun;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author oztur
 */
public class Arilar extends Engel {


    public BufferedImage bee1;
    public BufferedImage bee2;
    public BufferedImage bee3;
    public BufferedImage bee4;
    BufferedImage yol;
    public int spriteCnt = 0;
    public int spriteNo = 1;
    int beeSpeed = 4;
    int wstartX;
    int sstartX;
    int wstartY;
    int sstartY;
    int yon = 1;

    public Arilar(Oyun oyun) {
        super(oyun);
        int x = r.nextInt(oyun.maxWorldX - 6) + 3;
        int y = r.nextInt(oyun.maxWorldX - 2);

        int[] a = isSpace(x, y);
        nx = a[0];
        ny = a[1];

        for (int j = ny; j < ny + 2; j++) {
            for (int i = nx; i < nx + 7; i++) {
                if (oyun.map[i][j] == 0) {
                    oyun.map[i][j] = 1;
                }

            }
        }

        wlok = new Lokasyon(nx * oyun.kareBoy, ny * oyun.kareBoy);
        this.wstartX = wlok.getX();
        this.wstartY = wlok.getY();
        setWorldLoc();
        getImage();

    }

    @Override
    public void getImage() {
        try {
            bee1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteOmer\\ari1.png"));
            bee2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteOmer\\ari2.png"));
            bee3 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteOmer\\ari3.png"));
            bee4 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteOmer\\ari4.png"));
            yol = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteZemin\\redTrace.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        super.draw(g2);
        setWorldLoc();
        BufferedImage image = null;

        if (this.spriteNo == 1 && yon == 0) {
            image = bee1;
        } else if (this.spriteNo == 2 && yon == 0) {
            image = bee2;
        }
        if (this.spriteNo == 1 && yon == 1) {
            image = bee3;
        } else if (this.spriteNo == 2 && yon == 1) {
            image = bee4;
        }
        int lc = 0;
        for (Lokasyon l : oyun.keyH.traceLok) {
           
                if ((wlok.getX() > l.getX() - 3 * oyun.kareBoy
                        && wlok.getX() < l.getX() + 3 * oyun.kareBoy
                        && wlok.getY() > l.getY() - 3 * oyun.kareBoy
                        && wlok.getY() < l.getY() + 3 * oyun.kareBoy)
                        || (wlok.getX() + (6 * oyun.kareBoy) > l.getX() - 3 * oyun.kareBoy
                        && wlok.getX() + (6 * oyun.kareBoy) < l.getX() + 3 * oyun.kareBoy
                        && wlok.getY() > l.getY() - 3 * oyun.kareBoy
                        && wlok.getY() < l.getY() + 3 * oyun.kareBoy)
                        || (wlok.getX() > l.getX() - 3 * oyun.kareBoy
                        && wlok.getX() < l.getX() + 3 * oyun.kareBoy
                        && wlok.getY() + oyun.kareBoy > l.getY() - 3 * oyun.kareBoy
                        && wlok.getY() + oyun.kareBoy < l.getY() + 3 * oyun.kareBoy)
                        || (wlok.getX() + (6 * oyun.kareBoy) > l.getX() - 3 * oyun.kareBoy
                        && wlok.getX() + (6 * oyun.kareBoy) < l.getX() + 3 * oyun.kareBoy
                        && wlok.getY() + oyun.kareBoy > l.getY() - 3 * oyun.kareBoy
                        && wlok.getY() + oyun.kareBoy < l.getY() + 3 * oyun.kareBoy)) {
                    g2.drawImage(yol, sstartX, sstartY, oyun.kareBoy * 7, oyun.kareBoy * 2, null);
                    g2.drawImage(image, slok.getX(), slok.getY(), oyun.kareBoy * 2, oyun.kareBoy * 2, null);
                    if (isDisc == false) {
                        oyun.ui.show("Arı keşfedildi.");
                        isDisc = true;
                    }
                }
        

        }

    }

    public void update() {

        if (yon == 0) {
            this.wlok.setX(this.wlok.getX() - beeSpeed);
            if (wlok.getX() == wstartX) {
                yon = 1;
            }
        } else if (yon == 1) {

            this.wlok.setX(this.wlok.getX() + beeSpeed);
            if (wlok.getX() == wstartX + (oyun.kareBoy * 6)) {
                yon = 0;
            }
        }

        spriteCnt++;
        if (spriteCnt > 5) {
            if (spriteNo == 1) {
                spriteNo = 2;
            } else if (spriteNo == 2) {
                spriteNo = 1;
            }
            spriteCnt = 0;
        }

    }

    @Override
    public void setWorldLoc() {
        if (oyun.ui.isIsfinish() == false) {
            super.setWorldLoc();

            sstartX = wstartX - oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX();

            sstartY = wstartY - oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY();

        } else {
            super.reasetLok();
            sstartX = slok.getX();
            sstartY = slok.getY();
        }
    }

    @Override
    public int[] isSpace(int x, int y
    ) {

        boolean bos = true;
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 3; j++) {
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
            int nx = r.nextInt(oyun.maxWorldX - 7);
            int ny = r.nextInt(oyun.maxWorldX - 2);

            return isSpace(nx, ny);

        } else {
            int[] a = {x, y};
            return a;
        }

    }

}
