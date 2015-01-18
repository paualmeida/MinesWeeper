package Controller;

import UI.ImageViewer;


public abstract class ImageCommand implements Command {
    private ImageViewer viewer;

    public ImageCommand(ImageViewer viewer) {
        this.viewer = viewer;
    }
    
    public ImageViewer getViewer(){
        return viewer;
    }
}