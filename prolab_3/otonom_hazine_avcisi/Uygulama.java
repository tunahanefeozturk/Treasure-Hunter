
package prolab_3.otonom_hazine_avcisi;

import Chests.Chest;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.imageio.ImageIO;

/**
 *
 * @author oztur
 */
public class Uygulama {

    private int step_cnt = 0;
    private PriorityQueue<Chest> items = new PriorityQueue<Chest>();
    public boolean upP, downP, leftP, rightP;
    public boolean col = false;
    BufferedImage trace, startpnt;
    Oyun oyun;
    String[] chestsQueue = new String[20];
    public ArrayList<String> dicovered = new ArrayList<>();
    private Karakter krktr;
    public ArrayList<Lokasyon> traceLok;

    public Uygulama(Oyun oyun) {
        this.traceLok = new ArrayList<Lokasyon>();
        this.oyun = oyun;
        this.krktr = oyun.krktr;

        items = new PriorityQueue<>(new Comparator<Chest>() {
            @Override
            public int compare(Chest c1, Chest c2) {
                return c2.getPriority() - c1.getPriority();
            }
        });

        getTraecImage();
    }

    /**
     * @return the step_cnt
     */
    public int getStep_cnt() {
        return step_cnt;
    }

    /**
     * @param step_cnt the step_cnt to set
     */
    public void setStep_cnt(int step_cnt) {
        this.step_cnt = step_cnt;
    }

    /**
     * @return the krktr
     */
    public Karakter getKrktr() {
        return krktr;
    }

    /**
     * @param krktr the krktr to set
     */
    public void setKrktr(Karakter krktr) {
        this.krktr = krktr;
        traceLok.add(new Lokasyon(this.krktr.getWorldLoc().getX(), this.krktr.getWorldLoc().getY()));
    }

    public void getTraecImage() {
        try {
            trace = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\iz.png"));
            startpnt = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteZemin\\redTrace.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void drawTrace(Graphics2D g2) {

        for (int i = 0; i < oyun.map.length; i++) {
            for (int j = 0; j < oyun.map.length; j++) {
                if (oyun.map[i][j] == -2) {
                    if (oyun.ui.isIsfinish() == false) {
                        int sx = (i * oyun.kareBoy) - oyun.krktr.getWorldLoc().getX() + oyun.krktr.getScreenLoc().getX();
                        int sy = (j * oyun.kareBoy) - oyun.krktr.getWorldLoc().getY() + oyun.krktr.getScreenLoc().getY();
                        if (traceLok.get(0).getX() == (i * oyun.kareBoy) && traceLok.get(0).getY() == (j * oyun.kareBoy)
                                || traceLok.get(traceLok.size() - 1).getX() == (i * oyun.kareBoy) && traceLok.get(traceLok.size() - 1).getY() == (j * oyun.kareBoy)) {
                            g2.drawImage(startpnt, sx, sy, oyun.kareBoy, oyun.kareBoy, null);
                        } else {
                            g2.drawImage(trace, sx, sy, oyun.kareBoy, oyun.kareBoy, null);
                        }
                    } else {
                        if (traceLok.get(0).getX() == (i * 32) && traceLok.get(0).getY() == (j * 32)
                                || traceLok.get(traceLok.size() - 1).getX() == (i * 32) && traceLok.get(traceLok.size() - 1).getY() == (j * 32)) {
                            g2.drawImage(startpnt, i * oyun.kareBoy, j * oyun.kareBoy, oyun.kareBoy, oyun.kareBoy, null);

                        } else {
                            g2.drawImage(trace, i * oyun.kareBoy, j * oyun.kareBoy, oyun.kareBoy, oyun.kareBoy, null);
                        }
                    }

                }

            }

        }

    }

    public void listChest(int i, int j) {

        for (int k = 0; k < oyun.chests.length; k++) {
            if (oyun.chests[k].getLok().getX() / oyun.kareBoy == i && oyun.chests[k].getLok().getY() / oyun.kareBoy == j) {
                boolean a = false;
                for (Chest c : getItems()) {
                    if (c.equals(oyun.chests[k])) {
                        a = true;
                    }
                }
                if (a == false) {
                    getItems().add(oyun.chests[k]);
                    oyun.map[oyun.chests[k].getLok().getX() / oyun.kareBoy][oyun.chests[k].getLok().getY() / oyun.kareBoy] = 0;
                    oyun.chests[k].setIsFind(true);
                    oyun.ui.show(oyun.chests[k].getClass().getSimpleName() + " " + " Sandık toplandı! " + oyun.chests[k].getLok().getX() / oyun.kareBoy + " " + oyun.chests[k].getLok().getY() / oyun.kareBoy + " konumunda bulundu.");

                    for (int l = 0; l < oyun.krktr.chestQ.size(); l++) {
                        Chest chest = oyun.krktr.chestQ.get(l);

                        if (oyun.chests[k].equals(chest)) {
                            oyun.krktr.chestQ.remove(l--);
                            if (oyun.krktr.chestQ.isEmpty()) {
                                oyun.krktr.cyolda = true;
                            }
                        }

                    }
                }

            }

        }

        if (getItems().size() == oyun.chests.length) {

            for (int k = 0; k < oyun.chests.length; k++) {

                chestsQueue[k] = oyun.keyH.getItems().poll().toString();

            }

            for (int k = 0; k < oyun.maxWorldX; k++) {
                for (int l = 0; l < oyun.maxWorldX; l++) {
                    if (oyun.map[k][l] == -2) {
                        step_cnt++;
                    }
                }

            }

            oyun.ui.setIsfinish(true);

        }
    }

    /**
     * @return the items
     */
    public PriorityQueue<Chest> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(PriorityQueue<Chest> items) {
        this.items = items;
    }

}
