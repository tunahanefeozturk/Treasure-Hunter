
package prolab_3.otonom_hazine_avcisi;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author oztur
 */
public class YerSec {

    BufferedImage yaz;
    BufferedImage kıs;
    BufferedImage sis;
    Oyun oyun;
    public int[][] Msis;

    public YerSec(Oyun oyun) {
        this.oyun = oyun;
        Msis = new int[oyun.maxWorldX][oyun.maxWorldX];
        for (int i = 0; i < Msis.length; i++) {
            for (int j = 0; j < Msis.length; j++) {
                Msis[i][j] = 0;

            }

        }
        getImage();
    }

    public void getImage() {

        try {
            yaz = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteZemin\\yazYer.png"));
            kıs = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteZemin\\kısYer.png"));
            sis = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteZemin\\fog.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < oyun.maxWorldX; i++) {
            for (int j = 0; j < oyun.maxWorldX; j++) {

                if (oyun.ui.isIsfinish() == false) {
                    int x = i * oyun.kareBoy;
                    int y = j * oyun.kareBoy;
                    int sx = x - oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX();
                    int sy = y - oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY();

                    if (x + oyun.kareBoy > oyun.krktr.getWorldLoc().getX() - oyun.krktr.getScreenLoc().getX()
                            && x - oyun.kareBoy < oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX()
                            && y + oyun.kareBoy > oyun.krktr.getWorldLoc().getY() - oyun.krktr.getScreenLoc().getY()
                            && y - oyun.kareBoy < oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY()) {

                        if (i >= oyun.maxWorldX / 2) {
                            g2.drawImage(yaz, sx, sy, oyun.kareBoy, oyun.kareBoy, null);
                        } else if (i < oyun.maxWorldX / 2) {
                            g2.drawImage(kıs, sx, sy, oyun.kareBoy, oyun.kareBoy, null);
                        }

                    }
                } else {
                    if (i >= oyun.maxWorldX / 2) {
                        g2.drawImage(yaz, i * oyun.kareBoy, j * oyun.kareBoy, oyun.kareBoy, oyun.kareBoy, null);
                    } else if (i < oyun.maxWorldX / 2) {
                        g2.drawImage(kıs, i * oyun.kareBoy, j * oyun.kareBoy, oyun.kareBoy, oyun.kareBoy, null);
                    }
                }
            }
        }

    }

    public void drawSis(Graphics2D g2) {
        for (int i = 0; i < oyun.maxWorldX; i++) {
            for (int j = 0; j < oyun.maxWorldX; j++) {

                if (Msis[i][j] != 1) {
                    if (oyun.ui.isIsfinish() == false) {
                        int x = i * oyun.kareBoy;
                        int y = j * oyun.kareBoy;
                        int sx = x - oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX();
                        int sy = y - oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY();

                        if (x + oyun.kareBoy > oyun.krktr.getWorldLoc().getX() - oyun.krktr.getScreenLoc().getX()
                                && x - oyun.kareBoy < oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX()
                                && y + oyun.kareBoy > oyun.krktr.getWorldLoc().getY() - oyun.krktr.getScreenLoc().getY()
                                && y - oyun.kareBoy < oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY()) {

                            g2.drawImage(sis, sx, sy, oyun.kareBoy, oyun.kareBoy, null);

                        }
                    } else {
                        g2.drawImage(sis, i * oyun.kareBoy, j * oyun.kareBoy, oyun.kareBoy, oyun.kareBoy, null);
                    }
                }
            }
        }
    }

}
