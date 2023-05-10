/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author Nour Benkairia
 */
import com.codename1.ui.Image;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

////import java.util.EnumMap;
import java.util.Map;

public class QRCodeGenerator {
//    private static final int QR_CODE_SIZE = 200;
//
//    public static Image generateQRCode(String data) {
//        try {
//            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
//            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//
//            QRCodeWriter writer = new QRCodeWriter();
//            BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
//
//            int width = matrix.getWidth();
//            int height = matrix.getHeight();
//            int[] pixels = new int[width * height];
//
//            for (int y = 0; y < height; y++) {
//                int offset = y * width;
//                for (int x = 0; x < width; x++) {
//                    pixels[offset + x] = matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
//                }
//            }
//
//            return Image.createRGBImage(pixels, width, height, true);
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
