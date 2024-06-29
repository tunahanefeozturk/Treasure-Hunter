package prolab_3.otonom_hazine_avcisi;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class OyunEkranı extends JFrame {

   
    public Oyun oyun;

    public OyunEkranı(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        OyunEkranı ekran = new OyunEkranı("Hazine Avi");

        ekran.setFocusable(false);
        ekran.setResizable(true);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Start start = new Start(ekran);

        ekran.add(start);
        ekran.pack();

        ekran.setLayout(null);
        ekran.setLocationRelativeTo(null);
        ekran.setVisible(true);

    }

    public void crtOyun(OyunEkranı ekran, int ws) {

        Oyun oyun = new Oyun(ws);
        oyun.setBackground(Color.GRAY);
        oyun.setSize(oyun.ekranEni, oyun.ekranBoy);
        ekran.add(oyun);
        ekran.pack();
        ekran.setSize(oyun.ekranEni, oyun.ekranBoy);
        ekran.setLocationRelativeTo(null);
        oyun.startThread();

    }

}
