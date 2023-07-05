package com.example.ONDC.sellerApp.ONDCSellerApp.util;

import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException.IMAGE_DOWNLOAD_FAILED;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException.SOMETHING_WENT_WRONG;

@Slf4j
public class FileUtil {

  private static final String LOCAL_STORAGE_DIR = "/tmp/ondc";

  public static String saveFileLocallyFromURL(String url, String title) throws ONDCProductException {
    try {
      InputStream inputStream = new URL("http://www.somewebsite.com/a.txt").openStream();
      return saveFile(inputStream, title);
    } catch (ONDCProductException ex) {
      throw ex;
    } catch (Exception ex) {
      log.error("[getFileStreamFromUrl] Image download failed | url: {} | error: ", url, ex);
      throw new ONDCProductException(IMAGE_DOWNLOAD_FAILED);
    }
  }

  public static String saveFile(InputStream inputStream , String title) throws ONDCProductException {
   try {
    String filepath =  getFileNameWithPath(title);
    File targetFile = new File(filepath);
    OutputStream outStream = new FileOutputStream(targetFile);
    byte[] buffer = new byte[8 * 1024];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, bytesRead);
    }
    IOUtils.closeQuietly(inputStream);
    IOUtils.closeQuietly(outStream);
    return filepath;
    } catch (Exception ex) {
     log.error("[saveFile] Error while saving image locally | title: {} | error: ", title, ex);
     throw new ONDCProductException(SOMETHING_WENT_WRONG);
   }
  }

  public static String getFileNameWithPath(String title) throws ONDCProductException {
    var now = LocalDateTime.now();
    String directoryLocation =
        LOCAL_STORAGE_DIR
            + "/"
            + now.getYear()
            + "-"
            + now.getMonthValue()
            + "-"
            + now.getDayOfMonth();

    var directoryPath = Paths.get(directoryLocation);
    String fileName = System.currentTimeMillis() + "_" + title + ".jpeg";

    String fileWithPath = directoryLocation + "/" + fileName;
    log.info("New filepath {}", fileWithPath);

    try {
      if (Files.notExists(directoryPath)) {
        Files.createDirectories(directoryPath);
      }

    } catch (IOException e) {
      log.error("error while trying to create a new directory");
      throw new ONDCProductException("Unable to save file");
    }
    return fileWithPath;
  }
}
