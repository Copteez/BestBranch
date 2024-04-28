package Demo.model;

import edu.gemini.app.ocs.model.AstronomicalData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class OurAstronomicalData {
    private ArrayList<BufferedImage> images;
    private int UPPER_BOUND = 6;
    private int ADDITION = 4;
    private ArrayList<String> imgURL;

    public OurAstronomicalData() {
        this.images = new ArrayList();
    }

    public OurAstronomicalData(ArrayList<BufferedImage> images) {
        this.images = images;
    }

    public OurAstronomicalData getAstronomicalData(ArrayList<String> imgURL) throws IOException {
        this.imgURL = imgURL;
        this.getAllImages();
        return this;
    }

    public ArrayList<String> getAstronomicalDataLinks() throws IOException {
        return this.getAllImageLinks();
    }

    public ArrayList<BufferedImage> getAllImages() throws IOException {
        for(int i = 0; i < this.imgURL.size(); ++i) {
            String selectedImgLoc = (String)this.imgURL.get(i);
            BufferedImage img = (BufferedImage)this.downloadImage(selectedImgLoc);
            this.images.add(img);
        }

        return this.images;
    }

    private void retrieveAllImages() throws IOException {
        int noOfImages = this.randNum(this.UPPER_BOUND) + this.ADDITION;
        ArrayList<String> imageList = this.getListOfImages("references" + File.separator + "images.txt");

        for(int i = 0; i < noOfImages; ++i) {
            String selectedImgLoc = (String)imageList.remove(this.randNum(imageList.size()));
            BufferedImage img = (BufferedImage)this.downloadImage(selectedImgLoc);
            this.images.add(img);
        }

    }

    private ArrayList<String> getAllImageLinks() throws IOException {
        int noOfImages = this.randNum(this.UPPER_BOUND) + this.ADDITION;
        return this.getListOfImages("references" + File.separator + "images.txt");
    }

    private Image downloadImage(String loc) throws IOException {
        Image image = null;
        URL url = new URL(loc);
        image = ImageIO.read(url);
        System.out.println("Downloading image from " + loc);
        return image;
    }

    private int randNum(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    private ArrayList<String> getListOfImages(String imageListFileLoc) throws IOException {
        ArrayList<String> imagePaths = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(imageListFileLoc));

        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            imagePaths.add(line);
        }

        reader.close();
        return imagePaths;
    }
}
