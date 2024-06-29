package prolab_3.otonom_hazine_avcisi;

import Chests.Chest;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 *
 * @author oztur
 */
public class Karakter extends IOException {

    private Lokasyon WorldLoc;
    private Oyun oyun;
    private Lokasyon ScreenLoc;
    private Uygulama uyg;
    public int spriteCnt = 0;
    public int spriteNo = 1;
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;
    public ArrayList<Chest> chestQ = new ArrayList<Chest>();
    private String yon;
    Random r = new Random();
    int charSpeed;
    public boolean cyolda = true;
    int nx, ny, cnt = 0;
    public int sisx, cx;
    public int sisy, cy;

    public Karakter(Oyun oyun, Uygulama uyg) {
        this.oyun = oyun;
        charSpeed = 4;
        this.uyg = uyg;
        ScreenLoc = new Lokasyon((oyun.ekranEni - oyun.kareBoy) / 2, (oyun.ekranBoy - oyun.kareBoy) / 2);
        int x = r.nextInt(oyun.maxWorldX - 1);
        int y = r.nextInt(oyun.maxWorldX - 1);

        int[] a = isSpace(x, y);
        nx = a[0];
        ny = a[1];
        oyun.map[nx][ny] = -1;

        for (int i = nx - 3; i < nx + 4; i++) {
            for (int j = ny - 3; j < ny + 4; j++) {
                if (i >= 0 && i < oyun.maxWorldX && j >= 0 && j < oyun.maxWorldX) {
                    oyun.ys.Msis[i][j] = 1;
                }

            }

        }

        int[] b = goFog();
        sisx = b[0];
        sisy = b[1];
        this.WorldLoc = new Lokasyon(nx * oyun.kareBoy, ny * oyun.kareBoy);

        this.yon = "down";
        getImage();
    }

    public int[] isSpace(int x, int y) {

        boolean bos = true;

        if (oyun.map[x][y] != 0) {
            bos = false;
        }

        if (bos == false) {
            int nx = r.nextInt(oyun.maxWorldX - 1);
            int ny = r.nextInt(oyun.maxWorldX - 1);

            return isSpace(nx, ny);

        } else {
            int[] a = {x, y};
            return a;
        }

    }

    public void getImage() {
        try {
            

            
            up1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-up1.png"));
            up2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-up2.png"));
            down1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-down1.png"));
            down2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-down2.png"));
            left1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-left1.png"));
            left2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-left2.png"));
            right1 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-right1.png"));
            right2 = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteKArakter\\pixil-right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (this.getYon()) {
            case "up":
                if (this.spriteNo == 1) {
                    image = up1;
                } else if (this.spriteNo == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (this.spriteNo == 1) {
                    image = down1;
                } else if (this.spriteNo == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (this.spriteNo == 1) {
                    image = left1;
                } else if (this.spriteNo == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (this.spriteNo == 1) {
                    image = right1;
                } else if (this.spriteNo == 2) {
                    image = right2;
                }
                break;
        }
        if (oyun.ui.isIsfinish() == false) {
            g2.drawImage(image, this.ScreenLoc.getX(), this.ScreenLoc.getY(), oyun.kareBoy, oyun.kareBoy, null);
        } else {
            g2.drawImage(image, nx * oyun.kareBoy, ny * oyun.kareBoy, oyun.kareBoy, oyun.kareBoy, null);
        }

    }

    public void update() {

        pathDirection();
        int oldx = this.WorldLoc.getX() / oyun.kareBoy;
        int oldy = this.WorldLoc.getY() / oyun.kareBoy;
        switch (yon) {
            case "up":
                this.WorldLoc.setY(this.WorldLoc.getY() - charSpeed);
                break;
            case "down":
                this.WorldLoc.setY(this.WorldLoc.getY() + charSpeed);
                break;
            case "left":
                this.WorldLoc.setX(this.WorldLoc.getX() - charSpeed);
                break;
            case "right":
                this.WorldLoc.setX(this.WorldLoc.getX() + charSpeed);
                break;
        }
        cnt++;
        if (oldx == this.WorldLoc.getX() / oyun.kareBoy && oldy == this.WorldLoc.getY() / oyun.kareBoy || cnt == 30) {
            goFog();
        }

        try {

            oyun.map[(this.getWorldLoc().getX() + (oyun.kareBoy / 2)) / oyun.kareBoy][(this.getWorldLoc().getY() + (oyun.kareBoy / 2)) / oyun.kareBoy] = -2;
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        for (int i = this.getWorldLoc().getX() / oyun.kareBoy - 3; i < this.getWorldLoc().getX() / oyun.kareBoy + 4; i++) {
            for (int j = this.getWorldLoc().getY() / oyun.kareBoy - 3; j < this.getWorldLoc().getY() / oyun.kareBoy + 4; j++) {
                if (i >= 0 && i < oyun.maxWorldX && j >= 0 && j < oyun.maxWorldX) {
                    oyun.ys.Msis[i][j] = 1;

                }

            }

        }

        spriteCnt++;
        if (spriteCnt > 10) {
            if (spriteNo == 1) {
                spriteNo = 2;
            } else if (spriteNo == 2) {
                spriteNo = 1;
            }
            spriteCnt = 0;
        }

    }

    public void pathDirection() {

        if (!chestQ.isEmpty()) {

            Chest c = chestQ.get(0);

            if (c != null) {
                cx = c.getLok().getX() / oyun.kareBoy;
                cy = c.getLok().getY() / oyun.kareBoy;

                yolAra(cx, cy);
            }

        } else {
            if (cyolda == true) {

                sisAra(sisx, sisy);
            } else {
                int[] a = goFog();
                sisx = a[0];
                sisy = a[1];
                cyolda = true;
            }

        }

    }

    private int[] goFog() {

        int nx = 0, ny = 0;
        if (getWorldLoc() != null) {
            int kx = getWorldLoc().getX() / oyun.kareBoy;
            int ky = getWorldLoc().getY() / oyun.kareBoy;

            float maliyet = Float.MAX_VALUE;
            for (int j = 0; j < oyun.ys.Msis.length; j++) {
                for (int i = 0; i < oyun.ys.Msis.length; i++) {
                    if (oyun.ys.Msis[i][j] != 1 && oyun.map[i][j] != 1 && oyun.map[i][j] != -2) {
                        float maliyet2 = (float) Math.sqrt(Math.pow(i - kx, 2) + Math.pow(j - ky, 2));
                        if (maliyet2 < maliyet) {
                            maliyet = maliyet2;
                            nx = i;
                            ny = j;
                        }
                    }

                }

            }
        }
        int[] a = {nx, ny};
        return a;

    }

    private int[] goFog2() {

        int nx = 0, ny = 0;

        boolean bulundu = false;
        for (int j = 0; j < oyun.ys.Msis.length; j++) {
            for (int i = 0; i < oyun.ys.Msis.length; i++) {
                if (oyun.ys.Msis[i][j] != 1 && oyun.map[i][j] != 1 && oyun.map[i][j] != -2) {

                    if (this.getWorldLoc() != null) {
                        if (j > (this.getWorldLoc().getY() / oyun.kareBoy)) {
                            nx = i;
                            ny = j;
                            bulundu = true;
                            break;
                        }
                    }

                }

            }
            if (bulundu) {
                break;
            }
        }
        int[] a = {nx, ny};
        return a;

    }

    private void yolAra(int finishx, int finishy) {

        int startx = (WorldLoc.getX() + (oyun.kareBoy / 2)) / oyun.kareBoy;
        int starty = (WorldLoc.getY() + (oyun.kareBoy / 2)) / oyun.kareBoy;

        if (startx != finishx || starty != finishy) {

            oyun.sp.setNode(startx, starty, finishx, finishy, this);

            if (oyun.sp.ara() == true) {

                int nextNx = oyun.kareBoy * oyun.sp.yolList.get(0).getX();
                int nextNy = oyun.kareBoy * oyun.sp.yolList.get(0).getY();

                int lwx = getWorldLoc().getX() + 4;
                int rwx = getWorldLoc().getX() - 4 + oyun.kareBoy;
                int twy = getWorldLoc().getY() + 4;
                int dwy = getWorldLoc().getY() - 4 + oyun.kareBoy;

                if (twy > nextNy && lwx >= nextNx && rwx < nextNx + oyun.kareBoy) {
                    yon = "up";
                } else if (twy < nextNy && lwx >= nextNx && rwx < nextNx + oyun.kareBoy) {
                    yon = "down";
                } else if (twy >= nextNy && dwy < nextNy + oyun.kareBoy && rwx < nextNx + oyun.kareBoy && lwx > nextNx) {
                    yon = "left";
                } else if (twy >= nextNy && dwy < nextNy + oyun.kareBoy && rwx < nextNx + oyun.kareBoy && rwx < nextNx) {
                    yon = "right";
                }
                if (twy > nextNy && lwx > nextNx) {
                    yon = "up";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "left";
                    }
                } else if (twy > nextNy && lwx < nextNx) {
                    yon = "up";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "right";
                    }
                } else if (twy < nextNy && lwx > nextNx) {
                    yon = "down";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "left";
                    }
                } else if (twy < nextNy && lwx < nextNx) {
                    yon = "down";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "right";
                    }
                } else if (twy < nextNy && lwx < nextNx) {
                    yon = "right";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "down";
                    }
                } else if (twy >= nextNy && lwx < nextNx) {
                    yon = "right";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "up";
                    }
                } else if (twy < nextNy && lwx > nextNx) {
                    yon = "left";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "down";
                    }
                } else if (twy >= nextNy && lwx > nextNx) {
                    yon = "left";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "up";
                    }
                }
                if (nextNx == finishx && nextNy == finishy && chestQ.size() == 1) {
                    chestQ.remove(0);
                    cyolda = true;
                }
            }

        } else {

            if (chestQ.size() - 1 <= 1) {
                chestQ.remove(0);
                cyolda = true;
            }
        }
    }

    private void sisAra(int finishx, int finishy) {

        int startx = (WorldLoc.getX() + (oyun.kareBoy / 2)) / oyun.kareBoy;
        int starty = (WorldLoc.getY() + (oyun.kareBoy / 2)) / oyun.kareBoy;

        if (startx != finishx || starty != finishy) {

            oyun.sp.setNode(startx, starty, finishx, finishy, this);

            if (oyun.sp.ara() == true) {

                int nextNx = oyun.kareBoy * oyun.sp.yolList.get(0).getX();
                int nextNy = oyun.kareBoy * oyun.sp.yolList.get(0).getY();

                int lwx = getWorldLoc().getX() + 4;
                int rwx = getWorldLoc().getX() - 4 + oyun.kareBoy;
                int twy = getWorldLoc().getY() + 4;
                int dwy = getWorldLoc().getY() - 4 + oyun.kareBoy;

                if (twy > nextNy && lwx >= nextNx && rwx < nextNx + oyun.kareBoy) {
                    yon = "up";
                } else if (twy < nextNy && lwx >= nextNx && rwx < nextNx + oyun.kareBoy) {
                    yon = "down";
                } else if (twy >= nextNy && dwy < nextNy + oyun.kareBoy && rwx < nextNx + oyun.kareBoy && lwx > nextNx) {
                    yon = "left";
                } else if (twy >= nextNy && dwy < nextNy + oyun.kareBoy && rwx < nextNx + oyun.kareBoy && rwx < nextNx) {
                    yon = "right";
                }
                if (twy > nextNy && lwx > nextNx) {
                    yon = "up";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "left";
                    }
                } else if (twy > nextNy && lwx < nextNx) {
                    yon = "up";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "right";
                    }
                } else if (twy < nextNy && lwx > nextNx) {
                    yon = "down";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "left";
                    }
                } else if (twy < nextNy && lwx < nextNx) {
                    yon = "down";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "right";
                    }
                } else if (twy < nextNy && lwx < nextNx) {
                    yon = "right";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "down";
                    }
                } else if (twy >= nextNy && lwx < nextNx) {
                    yon = "right";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "up";
                    }
                } else if (twy < nextNy && lwx > nextNx) {
                    yon = "left";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "down";
                    }
                } else if (twy >= nextNy && lwx > nextNx) {
                    yon = "left";
                    uyg.col = false;
                    oyun.colKont.yerKont(uyg);
                    if (uyg.col == true) {
                        yon = "up";
                    }
                }

            }
        } else {
            cyolda = false;
        }
    }

    /**
     * @param ScreenLoc the ScreenLoc to set
     */
    public void setScreenLoc(Lokasyon ScreenLoc) {
        this.ScreenLoc = ScreenLoc;
    }

    /**
     * @return the WorldLoc
     */
    public Lokasyon getWorldLoc() {
        return WorldLoc;
    }

    /**
     * @param WorldLoc the WorldLoc to set
     */
    public void setWorldLoc(Lokasyon WorldLoc) {
        this.WorldLoc = WorldLoc;
    }

    /**
     * @return the ScreenLoc
     */
    public Lokasyon getScreenLoc() {
        return ScreenLoc;
    }

    /**
     * @return the yon
     */
    public String getYon() {
        return yon;
    }

    /**
     * @param yon the yon to set
     */
    public void setYon(String yon) {
        this.yon = yon;
    }

    /**
     * @return the oyun
     */
    public Oyun getOyun() {
        return oyun;
    }

    /**
     * @param panel the panel to set
     */
    public void setOyun(Oyun oyun) {
        this.oyun = oyun;
    }

    /**
     * @return the uyg
     */
    public Uygulama getUyg() {
        return uyg;
    }

    /**
     * @param uyg the uyg to set
     */
    public void setUyg(Uygulama uyg) {
        this.uyg = uyg;
    }

    /**
     * @return the chestQ
     */
    public ArrayList<Chest> getChestQ() {
        return chestQ;
    }

    /**
     * @param chestQ the chestQ to set
     */
    public void setChestQ(ArrayList<Chest> chestQ) {
        this.chestQ = chestQ;
    }
}
