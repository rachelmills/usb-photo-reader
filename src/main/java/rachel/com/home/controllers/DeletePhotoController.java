package rachel.com.home.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rachel.com.home.service.FileFinderService;
import rachel.com.home.service.PhotoComparisonService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
class DeletePhotoController {

    @RequestMapping(value = "/deletefile", method = RequestMethod.GET)
    private String deletePhoto(@RequestParam(value = "file") String file, Model model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("deleting file" + file);
        return "redirect:" + request.getHeader("Referer");
    }
}
