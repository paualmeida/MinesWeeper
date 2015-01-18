package View_Persistence_File;

import Model.Image;
import Model.RealImage;
import View_Persistence.ImageLoader;
import View_Swing.SwingBitmap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FileImageLoader implements ImageLoader {

    private final String path;

    public FileImageLoader(String path) {
        this.path = path;
    }

    @Override
    public Image load() {
        return new RealImage(new SwingBitmap(loadImage()));
    }

    private BufferedImage loadImage() {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            return null;
        }
    }
}
