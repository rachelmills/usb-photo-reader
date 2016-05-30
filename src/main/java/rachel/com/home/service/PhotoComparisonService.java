package rachel.com.home.service;

import org.springframework.stereotype.Service;
import rachel.com.home.model.Photo;

import java.util.Comparator;
import java.util.List;

/**
 * Created by rachelmills on 20/05/2016.
 */
@Service
public class PhotoComparisonService {

    public void orderPhotosBySize(List<Photo> photos) {
        photos.sort(new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                return (o1.getSize() > o2.getSize() ? 1 :
                        o1.getSize() < o2.getSize() ? -1 :
                                0);
            }
        });
    }

    public void markDuplicatesBySize(List<Photo> photos) {
        int countDuplicate = 0;

        for (int i = 1; i < photos.size(); i++) {
            if (photos.get(i).getSize() == photos.get(i - 1).getSize()) {
                photos.get(i).setDuplicate(true);
                photos.get(i - 1).setDuplicate(true);
                countDuplicate++;
            }
        }
        System.out.println(String.format("There are %d size matches", countDuplicate));
    }
}
