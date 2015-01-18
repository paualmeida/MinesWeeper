package View_Swing;

import Model.Image;
import UI.ImageViewer;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageViewer {

    private Image image;
    private int offset;
    private int xPos;

    public ImagePanel() {
        this.offset = 0;
        this.hook();
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void showNextImage() {
        image = image.getNext();
        repaint();
    }

    @Override
    public void showPrevImage() {
        image = image.getPrev();
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        if (image != null) {
            super.paint(graphics);
            graphics.drawImage(getBufferedImage(image), offset, 0, null);
            if (offset == 0) {
                return;
            }
            if (offset > 0) {
                graphics.drawImage(getBufferedImage(image.getPrev()), offset - image.getBitmap().getWidth(), 0, null);
            }
            if (offset < 0) {
                graphics.drawImage(getBufferedImage(image.getNext()), image.getBitmap().getWidth() + offset, 0, null);
            }
        }
    }

    private BufferedImage getBufferedImage(Image image) {
        SwingBitmap bitmap = (SwingBitmap) image.getBitmap();
        return bitmap.getBufferedImage();
    }

    private void hook() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                xPos = me.getX();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (offset > image.getBitmap().getWidth() / 2) {
                    showPrevImage();
                } else if (offset < -image.getBitmap().getWidth() / 2) {
                    showNextImage();
                }
                offset = 0;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                offset = me.getX() - xPos;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
    }
}