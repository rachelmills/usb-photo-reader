package rachel.com.home.model;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Created by rachelmills on 20/05/2016.
 */
public class Photo {

    private LocalDateTime date;
    private String photoName;
    private String usbName;
    private long size;
    private boolean duplicate;

    public boolean isDupe() {
        return dupe;
    }

    private Path filePath;
    private boolean dupe;

    public void setDupe(boolean dupe) {
        this.dupe = dupe;
    }

    public Photo(String usbName, String photoName, LocalDateTime date, long size, boolean duplicate, Path filePath) {
        this.usbName = usbName;
        this.photoName = photoName;
        this.date = date;
        this.size = size;
        this.duplicate = duplicate;

        this.filePath = filePath;
    }


    public Path getFilePath() {
        return filePath;
    }

    public long getSize() {
        return size;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPhotoName() {
        return photoName;
    }

    public String getUsbName() {
        return usbName;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    @Override
    public String toString() {
        return "Photo details:  USB name:  " + usbName + "\tPhoto name:  "
                + photoName + "\tCreatedDate" + date + "\tPhoto size:  " + size + "\tFile path:  " + filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Photo)) {
            return false;
        }
        Photo photo = (Photo) o;

        if (photo.getSize() == this.getSize()) {
            return true;
        }
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

//            Path file1 = Paths.get(photo.getFilePath().toString());

//            byte[] data1 = Files.readAllBytes(file1);
//            byte[] hashBytes1 = messageDigest.digest(data1);

//            Path file2 = Paths.get(this.getFilePath().toString());
//            byte[] data2 = Files.readAllBytes(file2);
//            byte[] hashBytes2 = messageDigest.digest(data2);

//            if (Arrays.equals(hashBytes1, hashBytes2)) {
//                return true;
//            }
//            return file1.equals(file2.toFile().getAbsoluteFile());
//        } catch (NoSuchAlgorithmException e) {
//            System.out.println("Wrong hashing algorithm");
//        } catch (IOException e) {
//            System.out.println("Exception thrown when reading bytes from file");
//        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) this.getSize();
    }

}
