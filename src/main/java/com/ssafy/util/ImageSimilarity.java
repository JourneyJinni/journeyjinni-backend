package com.ssafy.util;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;

public class ImageSimilarity {

    public static double calculateSimilarity(byte[] imageData1, byte[] imageData2) {
        // OpenCV 라이브러리 로드
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 이미지 바이트 배열을 Mat 객체로 변환
        MatOfByte matOfByte1 = new MatOfByte(imageData1);
        MatOfByte matOfByte2 = new MatOfByte(imageData2);

        // 이미지 로드
        Mat image1 = Imgcodecs.imdecode(matOfByte1, Imgcodecs.IMREAD_COLOR);
        Mat image2 = Imgcodecs.imdecode(matOfByte2, Imgcodecs.IMREAD_COLOR);

        // 이미지를 그레이 스케일로 변환
        Mat gray1 = new Mat();
        Mat gray2 = new Mat();
        Imgproc.cvtColor(image1, gray1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, gray2, Imgproc.COLOR_BGR2GRAY);

        // 이미지 히스토그램 계산
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        MatOfInt histSize = new MatOfInt(256);
        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        MatOfInt channels = new MatOfInt(0);
        Imgproc.calcHist(Arrays.asList(new Mat[]{gray1}), channels, new Mat(), hist1, histSize, ranges);
        Imgproc.calcHist(Arrays.asList(new Mat[]{gray2}), channels, new Mat(), hist2, histSize, ranges);

        // 히스토그램 정규화
        Core.normalize(hist1, hist1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(hist2, hist2, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // 히스토그램 비교 (유사도 측정)

        return Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
    }
}
