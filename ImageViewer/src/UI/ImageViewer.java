package UI;

import Model.Image;

public interface ImageViewer {

    public void setImage(Image image);

    public void showPrevImage();

    public void showNextImage();

    public Image getImage();
}
