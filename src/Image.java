import java.awt.image.BufferedImage;

public class Image {
    private final AveragePixel[][] pixels;
    int width;
    int height;

    Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new AveragePixel[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.pixels[x][y] = new AveragePixel(0, 0, 0);
            }
        }
    }

    void writePixel(int x, int y, Color color) {
        this.pixels[x][y].addPixel(color);
    }

    BufferedImage getImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixels[x][y].asIntegerColor());
            }
        }
        return image;
    }
}
