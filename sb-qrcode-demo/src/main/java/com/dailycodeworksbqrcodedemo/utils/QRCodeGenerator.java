package com.dailycodeworksbqrcodedemo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dailycodeworksbqrcodedemo.model.Student;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

  private static Logger logger = LoggerFactory.getLogger(QRCodeGenerator.class);

  public static void generateQRCode(Student student) {
    String qrCodePath = "C:\\QRCode\\";  // the file's path you store the QRCodes
    String qrCodeName = qrCodePath + student.getFirstName() + student.getId() + "QRCODE.png";

    logger.info("qrCodeName: {}", qrCodeName);

    try {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID: " + student.getId() + "\n" + "Firstname: " + student.getFirstName() + "\n"
                        + "Lastname: " + student.getLastName() + "\n" + "Email: " + student.getEmail() + "\n"
                        + "Mobile: " + student.getMobile(),
                BarcodeFormat.QR_CODE, 400, 400);

        logger.info("bitMatrix: {}", bitMatrix);

        Path path = FileSystems.getDefault().getPath(qrCodeName);

        logger.info("path: {}", path);

        File file = path.toFile();
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdirs();
            if (!success) {
                logger.error("Failed to create directories for file: {}", qrCodeName);
                return;
            }
        }

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    } catch (WriterException e) {
        logger.error("Error generating QR code for student: {}", student.getId(), e);
    } catch (IOException e) {
        logger.error("Error writing QR code to file: {}", qrCodeName, e);
    }
}
}
