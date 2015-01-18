package View_Persistence_File;

import Model.Image;
import View_Persistence.ImageLoader;
import View_Persistence.ListLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileImageListLoader implements ListLoader {

    private String path;

    public FileImageListLoader(String path) {
        this.path = path;
    }

    @Override
    public List<Image> load() {
        return linkImages(loadImages());
    }

    private List<Image> loadImages() {
        List<Image> album = new ArrayList<>();
        for (String file : new File(path).list()) {
            album.add(new ProxyImage((ImageLoader) new FileImageLoader(path + "/" + file)));
        }
        return album;
    }

    private List<Image> linkImages(List<Image> album) {
        for (int i = 0; i < album.size(); i++) {
            Image image = album.get(i);
            image.setNext(album.get((i + 1) % album.size()));
            image.setPrev(album.get((i + album.size() - 1) % album.size()));
        }
        return album;
    }
}
