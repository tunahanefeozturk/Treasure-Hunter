package Chests;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import prolab_3.otonom_hazine_avcisi.Oyun;

/**
 *
 * @author oztur
 */
public class Bronze extends Chest {

    public Bronze(Oyun oyun) {
        super(oyun);
        priority = 1;
    }

    @Override
    public void getChestImg() {
        try {
            image = ImageIO.read(new File("C:\\Otonom_Hazine_Avcisi\\src\\main\\java\\SpriteChest\\bronzeChest.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
