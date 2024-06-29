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
public class Kuslar extends Engel {

    
    BufferedImage bird1;
    BufferedImage bird2;
    BufferedImage yol;
    public int spriteCnt = 0;
    public int spriteNo = 1;
    int birdSpeed = 4;
    int wstartY;
    int sstartY;
    int wstartX;
    int sstartX;
    int yon = 1;

    public Kuslar(Oyun oyun) {
        super(oyun);
        int x = r.nextInt(oyun.maxWorldX - 2);
        int y = r.nextInt(oyun.maxWorldX - 11);

        int[] a = isSpace(x, y);
        nx = a[0];
        ny = a[1];

        for (int j = ny; j < ny + 11; j++) {
            for (int i = nx; i < nx + 2; i++) {
                if (oyun.map[i][j] == 0) {
                    oyun.map[i][j] = 1;
                }

            }
        }

        wlok = new Lokasyon(nx * oyun.kareBoy, ny * oyun.kareBoy);
        this.wstartY = wlok.getY();
        this.wstartX = wlok.getX();
        setWorldLoc();
        getImage();

    }

    @Override
    public void getImage() {
        try {
            bird1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteOmer\\bird1.png"));
            bird2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteOmer\\bird2.png"));
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
        if (this.spriteNo == 1) {
            image = bird1;
        } else if (this.spriteNo == 2) {
            image = bird2;
        }

       
        for (Lokasyon l : oyun.keyH.traceLok) {
            
                if ((wstartX > l.getX() - 3 * oyun.kareBoy
                        && wstartX < l.getX() + 3 * oyun.kareBoy
                        && wstartY > l.getY() - 3 * oyun.kareBoy
                        && wstartY < l.getY() + 3 * oyun.kareBoy)
                        || (wstartX + oyun.kareBoy > l.getX() - 3 * oyun.kareBoy
                        && wstartX + oyun.kareBoy < l.getX() + 3 * oyun.kareBoy
                        && wstartY > l.getY() - 3 * oyun.kareBoy
                        && wstartY < l.getY() + 3 * oyun.kareBoy)
                        || (wstartX > l.getX() - 3 * oyun.kareBoy
                        && wstartX < l.getX() + 3 * oyun.kareBoy
                        && wstartY + (5 * oyun.kareBoy) > l.getY() - 3 * oyun.kareBoy
                        && wstartY + (5 * oyun.kareBoy) < l.getY() + 3 * oyun.kareBoy)
                        || (wstartX + oyun.kareBoy > l.getX() - 3 * oyun.kareBoy
                        && wstartX + oyun.kareBoy < l.getX() + 3 * oyun.kareBoy
                        && wstartY + (5 * oyun.kareBoy) > l.getY() - 3 * oyun.kareBoy
                        && wstartY + (5 * oyun.kareBoy) < l.getY() + 3 * oyun.kareBoy)) {
                    g2.drawImage(yol, sstartX, sstartY, oyun.kareBoy * 2, oyun.kareBoy * 11, null);
                    g2.drawImage(image, slok.getX(), slok.getY(), oyun.kareBoy * 2, oyun.kareBoy * 2, null);
                    if (isDisc == false) {
                        oyun.ui.show("Kuş keşfedildi.");
                        isDisc = true;
                    }
                }
          

        }

    }

    public void update() {

        if (yon == 0) {
            this.wlok.setY(this.wlok.getY() - birdSpeed);
            if (wlok.getY() == wstartY) {
                yon = 1;
            }
        } else if (yon == 1) {

            this.wlok.setY(this.wlok.getY() + birdSpeed);
            if (wlok.getY() == wstartY + (oyun.kareBoy * 10)) {
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
    public int[] isSpace(int x, int y) {

        boolean bos = true;
        for (int i = x; i < x + 2; i++) {
            for (int j = y; j < y + 10; j++) {
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
            int nx = r.nextInt(oyun.maxWorldX - 2);
            int ny = r.nextInt(oyun.maxWorldX - 10);

            return isSpace(nx, ny);

        } else {
            int[] a = {x, y};
            return a;
        }

    }

}
