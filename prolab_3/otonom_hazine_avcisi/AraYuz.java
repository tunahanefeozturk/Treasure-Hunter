package prolab_3.otonom_hazine_avcisi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author oztur
 */
public class AraYuz {

    Oyun oyun;
    private int cnt = 0;
    boolean v;
    private boolean isfinish = false;
    String str;

    public AraYuz(Oyun oyun) {
        this.oyun = oyun;

    }

    public void show(String str) {
        this.str = str;
        v = true;
    }

    public void draw(Graphics2D g2) {

        if (isfinish == true) {

            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRoundRect(630, 0, 200, 330, 10, 10);
            g2.setFont(new Font("Arial", Font.PLAIN, 15));
            g2.setColor(Color.white);

            for (int i = 0, j = 20; i < oyun.chests.length; i++, j += 16) {
                g2.drawString(oyun.keyH.chestsQueue[i], 640, j);

            }

            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRoundRect(500, 0, 130, 30, 10, 10);
            g2.setFont(new Font("Arial", Font.PLAIN, 15));
            g2.setColor(Color.white);
            g2.drawString("Adım sayısı:" + oyun.keyH.getStep_cnt(), 510, 20);

            
            oyun.thr = null;

        } else {
            if (v == true) {

                if (str.endsWith("bulundu.")) {
                    g2.setColor(new Color(0, 0, 0, 150));
                    g2.fillRoundRect(430, 0, 390, 30, 10, 10);
                    g2.setFont(new Font("Arial", Font.PLAIN, 15));
                    g2.setColor(Color.white);
                    g2.drawString(str, 440, 20);

                } else {
                    g2.setColor(new Color(0, 0, 0, 150));
                    g2.fillRoundRect(620, 0, 200, 30, 10, 10);
                    g2.setFont(new Font("Arial", Font.ITALIC, 15));
                    g2.setColor(Color.white);
                    g2.drawString(str, 630, 20);
                }

                cnt++;
                if (cnt > 100) {
                    cnt = 0;
                    v = false;
                }
            }
        }

    }

    /**
     * @return the isfinish
     */
    public boolean isIsfinish() {
        return isfinish;
    }

    /**
     * @param isfinish the isfinish to set
     */
    public void setIsfinish(boolean isfinish) {
        this.isfinish = isfinish;
    }

}
