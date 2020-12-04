import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Services {
    private static final Logger logger = Logger.getLogger(Services.class.getName());
    private static Set<String> uniquePhotoList = new HashSet<>();

    public void getFilesFromFolder(final File folder) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                getFilesFromFolder(fileEntry);
            } else {
                logger.info("File " + fileEntry.getName() + " Scanned ");
                String photo = getFileChecksum(fileEntry);
                uniquePhotoList.add(photo);

            }
        }
        removeFilesFromFolder(folder);
    }


    private static String getFileChecksum(File file) {

        String checkSum = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] byteArray = new byte[1024];
            int bytesCount = 0;

            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }

            fis.close();

            byte[] bytes = digest.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            checkSum = sb.toString();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "FileInputStream exception", ex);
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, "MessageDigest exception", ex);
        }
        return checkSum;

    }

    public void removeFilesFromFolder(final File folder) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if(!fileEntry.isDirectory()) {
                if (uniquePhotoList.contains(getFileChecksum(fileEntry))) {
                    uniquePhotoList.remove(getFileChecksum(fileEntry));
                } else {
                    fileEntry.delete();
                    logger.info("File " + fileEntry.getName() + " Deleted ");
                }
            }
        }
    }
}










