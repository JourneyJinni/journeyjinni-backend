package com.ssafy.util;
import org.opencv.core.*;
import org.opencv.core.Mat;
import org.opencv.features2d.BFMatcher;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.osgi.OpenCVNativeLoader;

import java.util.Arrays;
import java.util.List;

import static org.opencv.imgcodecs.Imgcodecs.imdecode;

public class ImageSimilarity {

    public static double calculateSimilarity(byte[] imageData1, byte[] imageData2) {

        new OpenCVNativeLoader().init();
        // Convert byte arrays to Mat objects
        Mat image1 = imdecode(new MatOfByte(imageData1), Imgcodecs.IMREAD_COLOR);
        Mat image2 = imdecode(new MatOfByte(imageData2), Imgcodecs.IMREAD_COLOR);

        // Check if images are loaded properly
        if (image1.empty() || image2.empty()) {
            System.out.println("Error: One or both images could not be loaded.");
            return 0.0;
        }

        // Convert images to HSV color space
        Mat hsvImage1 = new Mat();
        Mat hsvImage2 = new Mat();
        Imgproc.cvtColor(image1, hsvImage1, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(image2, hsvImage2, Imgproc.COLOR_BGR2HSV);

        // Calculate histograms
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        MatOfInt histSize = new MatOfInt(50, 60); // H and S bins
        MatOfFloat ranges = new MatOfFloat(0f, 180f, 0f, 256f); // H and S ranges
        MatOfInt channels = new MatOfInt(0, 1); // Use H and S channels

        Imgproc.calcHist(List.of(hsvImage1), channels, new Mat(), hist1, histSize, ranges);
        Imgproc.calcHist(List.of(hsvImage2), channels, new Mat(), hist2, histSize, ranges);

        // Normalize histograms
        Core.normalize(hist1, hist1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(hist2, hist2, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // Compare histograms
        double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.HISTCMP_CORREL);

        System.out.println("Histogram Similarity: " + similarity);

        return similarity;
    }


    private static boolean isLibraryLoaded() {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            return true;
        } catch (UnsatisfiedLinkError e) {
            return false;
        }
    }
}
