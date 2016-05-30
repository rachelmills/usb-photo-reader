package rachel.com.home.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rachel.com.Application;
import rachel.com.home.model.Photo;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

/**
 * Created by rachelmills on 20/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FileFinderServiceTest {

    @Autowired
    FileFinderService fileFinderService;

    @Test
    public void shouldFindAllPhotoFiles() throws IOException {
        List<Photo> photos = fileFinderService.findAllPhotoFiles();
        assertThat(photos, is(not(empty())));
    }
}
