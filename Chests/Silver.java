package Chests;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import prolab_3.otonom_hazine_avcisi.Oyun;

/**
 *
 * @author oztur
 */
public class Silver extends Chest {

    public Silver(Oyun oyun) {
        super(oyun);
        priority = 3;
    }

    @Override
    public void getChestImg() {
        try {
            image = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteChest\\silverChest.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
