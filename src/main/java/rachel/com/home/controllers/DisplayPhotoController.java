package rachel.com.home.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
class DisplayPhotoController {

    @RequestMapping(value = "/openfile", method = RequestMethod.GET)
    private ResponseEntity displayPhotos(@RequestParam(value = "file") String file, Model model, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "image/png");
            FileInputStream fin = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();

            IOUtils.copy(fin, out);
            IOUtils.closeQuietly(fin);

            response.flushBuffer();

            return new ResponseEntity(HttpStatus.OK);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file " + file);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception processing file " + file);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
