package prolab_3.otonom_hazine_avcisi;

/**
 *
 * @author oztur
 */
public class ColKont {

    Oyun oyun;

    public ColKont(Oyun oyun) {
        this.oyun = oyun;
    }

    public void yerKont(Uygulama uyg) {

        int lwx = oyun.krktr.getWorldLoc().getX() + 4;
        int rwx = oyun.krktr.getWorldLoc().getX() - 4 + oyun.kareBoy;
        int twy = oyun.krktr.getWorldLoc().getY() + 4;
        int dwy = oyun.krktr.getWorldLoc().getY() - 4 + oyun.kareBoy;

        int lc = lwx / oyun.kareBoy;
        int rc = rwx / oyun.kareBoy;
        int tr = twy / oyun.kareBoy;
        int dr = dwy / oyun.kareBoy;

        int tn1, tn2 = 0;
        int tn3 = oyun.map[(oyun.krktr.getWorldLoc().getX() + (oyun.kareBoy / 2)) / oyun.kareBoy][(oyun.krktr.getWorldLoc().getY() + (oyun.kareBoy / 2)) / oyun.kareBoy];

        switch (oyun.krktr.getYon()) {
            case "up":
                tr = (twy - 4) / oyun.kareBoy;

                if ((twy - 4) < 0) {
                    tn1 = 1;
                } else {
                    tn1 = oyun.map[lc][tr];
                    tn2 = oyun.map[rc][tr];

                }
                if (tn1 == 1 || tn2 == 1) {
                    uyg.col = true;
                } else if (tn1 == 2) {
                    uyg.listChest(lc, tr);

                } else if (tn2 == 2) {
                    uyg.listChest(rc, tr);

                }

                break;
            case "down":
                dr = (dwy + 4) / oyun.kareBoy;
                if (dr >= oyun.maxWorldX) {
                    tn1 = 1;
                } else {
                    tn1 = oyun.map[lc][dr];
                    tn2 = oyun.map[rc][dr];
                }
                if (tn1 == 1 || tn2 == 1) {
                    uyg.col = true;
                } else if (tn1 == 2) {
                    uyg.listChest(lc, dr);

                } else if (tn2 == 2) {
                    uyg.listChest(rc, dr);

                }

                break;
            case "left":
                lc = (lwx - 4) / oyun.kareBoy;
                if ((lwx - 4) < 0) {
                    tn1 = 1;
                } else {
                    tn1 = oyun.map[lc][tr];
                    tn2 = oyun.map[lc][dr];
                }
                if (tn1 == 1 || tn2 == 1) {
                    uyg.col = true;
                } else if (tn1 == 2) {
                    uyg.listChest(lc, tr);

                } else if (tn2 == 2 || tn2 == -3) {
                    uyg.listChest(lc, dr);

                }
                break;
            case "right":
                rc = (rwx + 4) / oyun.kareBoy;
                if (rc >= oyun.maxWorldX) {
                    tn1 = 1;
                } else {
                    tn1 = oyun.map[rc][tr];
                    tn2 = oyun.map[rc][dr];
                }
                if (tn1 == 1 || tn2 == 1) {
                    uyg.col = true;
                } else if (tn1 == 2) {
                    uyg.listChest(rc, tr);

                } else if (tn2 == 2) {
                    uyg.listChest(rc, dr);

                }
                break;

        }

    }
}
