package rachel.com.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rachel.com.home.model.Photo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.time.LocalDateTime.now;

/**
 * Created by rachelmills on 20/05/2016.
 */
@Service
public class FileFinderService {

    @Autowired
    UsbFinderService usbFinderService;

    public List<Photo> findAllPhotoFiles() {
        int totalNumPhotos = 0;
        List<Photo> photos = new ArrayList<>();
        List<File> usbList = usbFinderService.findAllUsbs();
        for (File usb : usbList) {
            try (Stream<Path> paths = Files.walk(Paths.get(usb.getAbsolutePath()))) {
                paths.filter(p -> ((p.toFile().getName().endsWith(".JPG")
                        || p.toFile().getName().endsWith(".jpg")
                        || p.toFile().getName().endsWith(".JPEG")
                        || p.toFile().getName().endsWith(".jpeg")))
                        && !p.toFile().getName().startsWith("._"))
                        .forEach(p -> photos.add(createPhoto(p, usb)));
                System.out.println("Number of photos on " + usb.getName() + " is " + (photos.size() - totalNumPhotos));
                totalNumPhotos += photos.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return photos;
    }

    private Photo createPhoto(Path p, File usb) {
        File file = p.toFile();
        Photo photo;
        photo = new Photo(usb.getName(), file.getName(), getCreatedDateTime(file), file.length(), false, p);
        System.out.println(photo);

        return photo;
    }

    private LocalDateTime getCreatedDateTime(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        LocalDateTime createdDateTime = null;
        try {
            BasicFileAttributes view
                    = Files.getFileAttributeView(path, BasicFileAttributeView.class)
                    .readAttributes();
            FileTime fileTime = view.creationTime();
            Instant instant = fileTime.toInstant();
            createdDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (IOException ex) {
            System.out.println("IOException thrown trying to read file attributes:" + ex);
        }
        return createdDateTime;
    }
}
