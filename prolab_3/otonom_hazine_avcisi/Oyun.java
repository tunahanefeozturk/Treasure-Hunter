package prolab_3.otonom_hazine_avcisi;

import Chests.Bronze;
import Chests.Chest;
import Chests.Emerald;
import Chests.Gold;
import Chests.Silver;
import Engeller.Agaclar;
import Engeller.Arilar;
import Engeller.Daglar;
import Engeller.Duvarlar;
import Engeller.Kayalar;
import Engeller.Kuslar;
import algoritma.ShortPath;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Oyun extends JPanel implements Runnable {

    public int kareBoy = 32;
    public int ekranSize = 25;
    public int ekranEni = 820;
    public int ekranBoy = 850;

    public int maxWorldX;
    public int[][] map;
    public int worldSize;
    public int worldEn;
    public int worldBoy;
    public AraYuz ui = new AraYuz(this);
    public Uygulama keyH = new Uygulama(this);
    public ShortPath sp;
    Thread thr;
    ColKont colKont;
    public Karakter krktr;
    YerSec ys;
    Arilar[] bee;
    Duvarlar[] duvar;
    Agaclar[] tree;
    Daglar[] dag;
    Kayalar[] kaya;
    Kuslar kus;
    Chest[] chests;

    public Oyun(int ws) {
        maxWorldX = ws;
        worldSize = maxWorldX * kareBoy;
        worldEn = kareBoy * maxWorldX;
        worldBoy = kareBoy * maxWorldX;

        map = new int[maxWorldX][maxWorldX];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {

                map[i][j] = 0;

            }

        }
        this.setPreferredSize(new Dimension(ekranEni, ekranBoy));
        this.setDoubleBuffered(true);

        this.setFocusable(true);

        ys = new YerSec(this);
        colKont = new ColKont(this);
        sp = new ShortPath(this);
        krktr = new Karakter(this, keyH);
        this.keyH.setKrktr(krktr);

        tree = new Agaclar[8];
        dag = new Daglar[4];
        duvar = new Duvarlar[2];
        kaya = new Kayalar[6];
        bee = new Arilar[2];

        for (int i = 0; i < dag.length; i++) {
            dag[i] = new Daglar(this);
        }
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Agaclar(this);
        }
        for (int i = 0; i < kaya.length; i++) {
            kaya[i] = new Kayalar(this);
        }
        for (int i = 0; i < duvar.length; i++) {
            duvar[i] = new Duvarlar(this);
            bee[i] = new Arilar(this);
        }

        kus = new Kuslar(this);
        chests = new Chest[20];

        for (int i = 0; i < chests.length; i += 4) {
            chests[i] = new Gold(this);
            chests[i + 1] = new Silver(this);
            chests[i + 2] = new Emerald(this);
            chests[i + 3] = new Bronze(this);

        }

    }

    public void startThread() {
        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
       
        double di = 1000000000 / 300;
        double delta = 0;
        long lastT = System.nanoTime();
        long cTime;

        while (thr != null) {
            cTime = System.nanoTime();
            delta += (cTime - lastT) / di;
            lastT = cTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
        if(ui.isIsfinish()){
            ZoomOut();
        }
    }

    public void ZoomOut() {

        if (maxWorldX > 800) {
            kareBoy = 1;
        } else {
            this.kareBoy = 800 / maxWorldX;
        }
        

    }

    public void ZoomIn() {

        kareBoy = 32;
        krktr.setScreenLoc(new Lokasyon((ekranEni - kareBoy) / 2, (ekranBoy - kareBoy) / 2));

    }

    public void update() {

        krktr.update();
        bee[0].update();
        bee[1].update();
        kus.update();
    }

    public void paint(Graphics g) {
        
     
        super.paintComponent(g);
        keyH.setKrktr(krktr);
        Graphics2D g2 = (Graphics2D) g;

        ys.draw(g2);
        for (int i = 0; i < dag.length; i++) {
            dag[i].draw(g2);

        }
        for (int i = 0; i < tree.length; i++) {
            tree[i].draw(g2);

        }
        for (int i = 0; i < kaya.length; i++) {
            kaya[i].draw(g2);

        }
        for (int i = 0; i < duvar.length; i++) {
            duvar[i].draw(g2);
            bee[i].draw(g2);
        }

        kus.draw(g2);

        for (int i = 0; i < chests.length; i++) {
            chests[i].drawChest(g2);

        }
        keyH.drawTrace(g2);
        
    
        ys.drawSis(g2);
        
        krktr.draw(g2);
        ui.draw(g2);
        g2.dispose();

    }

}
