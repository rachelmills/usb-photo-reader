package rachel.com.home.service;

import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by rachelmills on 20/05/2016.
 */
@Service
public class UsbFinderService {

    public List<File> findAllUsbs() {
        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] files = File.listRoots();
        File file = files[0];
        Stream<File> drivesFoundStream = Arrays.stream(file.listFiles()).filter(f -> f.getName().equals("Volumes"));
        List<File> drivesFoundList = drivesFoundStream.collect(toList());
        Stream<File> usbsFoundStream = Arrays.stream(drivesFoundList.get(0).listFiles())
                .filter(f -> f.getName().contains("TSB") || f.getName().contains("Lexar"));
        List<File> usbsFound = usbsFoundStream.collect(toList());

        for (File usb : usbsFound) {
            String path = usb.getPath();
            String name = usb.getName();
            String systemDisplayName = fsv.getSystemDisplayName(usb);
            String type = fsv.getSystemTypeDescription(usb);
            boolean isDrive = fsv.isDrive(usb);
            boolean isFloppy = fsv.isFloppyDrive(usb);
            boolean canRead = usb.canRead();
            boolean canWrite = usb.canWrite();

            System.out.println("\nFile details: \n");
            System.out.println("path = " + path);
            System.out.println("name = " + name);
            System.out.println("systemDisplayName = " + systemDisplayName);
            System.out.println("type = " + type);
            System.out.println("isDrive = " + isDrive);
            System.out.println("isFloppy = " + isFloppy);
            System.out.println("canRead = " + canRead);
            System.out.println("canWrite = " + canWrite);
        }
        return usbsFound;
    }
}
