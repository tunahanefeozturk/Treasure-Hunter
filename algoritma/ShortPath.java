
package algoritma;

import java.util.ArrayList;
import prolab_3.otonom_hazine_avcisi.Karakter;
import prolab_3.otonom_hazine_avcisi.Oyun;

/**
 *
 * @author oztur
 */
public class ShortPath {

    Oyun oyun;
    PNode[][] node;
    ArrayList<PNode> bosList = new ArrayList<PNode>();
    public ArrayList<PNode> yolList = new ArrayList<PNode>();
    PNode start;
    PNode finish, betw;
    boolean arrived = false;
    int step = 0;

    public ShortPath(Oyun oyun) {
        this.oyun = oyun;
        node = new PNode[oyun.maxWorldX][oyun.maxWorldX];
        for (int i = 0; i < oyun.maxWorldX; i++) {
            for (int j = 0; j < oyun.maxWorldX; j++) {
                node[i][j] = new PNode(i, j);

            }

        }
    }

    public void resetNode() {
        for (int i = 0; i < oyun.maxWorldX; i++) {
            for (int j = 0; j < oyun.maxWorldX; j++) {
                node[i][j].setColl(false);
                node[i][j].setKont(false);
                node[i][j].setBos(false);

            }

        }

        bosList.clear();
        yolList.clear();
        arrived = false;
        step = 0;
    }

    public void setNode(int startx, int starty, int finishx, int finishy, Karakter krktr) {
        resetNode();

        if (startx < oyun.maxWorldX && startx >= 0 && starty < oyun.maxWorldX && starty >= 0) {
            start = node[startx][starty];
            betw = start;
            finish = node[finishx][finishy];
            bosList.add(betw);

            for (int i = 0; i < oyun.maxWorldX; i++) {
                for (int j = 0; j < oyun.maxWorldX; j++) {
                    if (oyun.map[i][j] == 1) {
                        node[i][j].setColl(true);
                    }

                    getMal(node[i][j]);
                }

            }
        }
        if (startx >= oyun.maxWorldX) {
            startx--;
        } else if (starty >= oyun.maxWorldX) {
            starty--;
        } else if (startx < 0) {
            startx++;
        } else if (starty < 0) {
            starty++;
        }
    }

    private void getMal(PNode node) {

        //G
        int uzaklıkx = Math.abs(node.getX() - start.getX());
        int uzaklıky = Math.abs(node.getY() - start.getY());
        node.setG(uzaklıkx + uzaklıky);

        //H
        uzaklıkx = Math.abs(node.getX() - finish.getX());
        uzaklıky = Math.abs(node.getY() - finish.getY());
        node.setG(uzaklıkx + uzaklıky);

        //F
        node.setF(node.getG() + node.getH());

    }

    public boolean ara() {
        while (arrived == false && step < 999 && betw != null) {
            if (!bosList.isEmpty()) {
                int x = betw.getX();
                int y = betw.getY();

                betw.setKont(true);

                bosList.remove(betw);

                if (y - 1 >= 0) {
                    bosNode(node[x][y - 1]);
                }
                if (x - 1 >= 0) {
                    bosNode(node[x - 1][y]);
                }
                if (y + 1 < oyun.maxWorldX) {
                    bosNode(node[x][y + 1]);
                }
                if (x + 1 < oyun.maxWorldX) {
                    bosNode(node[x + 1][y]);
                }

                int bestNodeindx = 0;
                int bestNodef = 999;

                try {
                    if (!bosList.isEmpty()) {
                        for (int i = 0; i < bosList.size(); i++) {
                            if (bosList.get(i) != null && bosList.get(bestNodeindx) != null) {
                                if (bosList.get(i).getF() < bestNodef) {
                                    bestNodeindx = i;
                                    bestNodef = bosList.get(i).getF();
                                } else if (bosList.get(i).getF() == bestNodef && bosList.get(i).getG() < bosList.get(bestNodeindx).getG()) {
                                    bestNodeindx = i;
                                }
                            }
                        }
                    } else if (bosList.size() == 0) {
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                if (bestNodeindx <= bosList.size()) {
                    betw = bosList.get(bestNodeindx);
                }

                if (betw == finish) {
                    arrived = true;
                    yolTakip();
                }
                step++;
            }
        }
        return arrived;
    }

    private void bosNode(PNode node) {
        if (node.isBos() == false && node.isKont() == false && node.isColl() == false) {
            node.setBos(true);
            node.setUst(betw);
            bosList.add(node);
        }

    }

    private void yolTakip() {

        PNode btw = finish;

        while (btw != start) {
            yolList.add(0, btw);
            btw = btw.getUst();
        }

    }

}
