package rachel.com.home.controllers;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rachel.com.home.model.Photo;
import rachel.com.home.service.FileFinderService;
import rachel.com.home.service.PhotoComparisonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
class LoadPhotoController {

    @Autowired
    private FileFinderService fileFinderService;

    @Autowired
    private PhotoComparisonService photoComparisonService;

    private final static int NUMBER_OF_PAGE_SELECTORS = 5;

    @RequestMapping("/photos")
    private String findAllPhotos(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("photos", null);
        return "redirect:photos/page/1";
    }

    @RequestMapping(value="/photos/page/{pageNumber}", method= RequestMethod.GET)
    private String showPagedPhotosPage(@PathVariable int pageNumber, HttpServletRequest request, Model model) {
        PagedListHolder<Photo> pagedListHolder = (PagedListHolder<Photo>) request.getSession().getAttribute("photos");
        if (pagedListHolder == null) {
            List<Photo> photos = fileFinderService.findAllPhotoFiles();
            photoComparisonService.orderPhotosBySize(photos);
            photoComparisonService.markDuplicatesBySize(photos);
            pagedListHolder = new PagedListHolder<>(photos);
            pagedListHolder.setPageSize(30);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
                pagedListHolder.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("photos", pagedListHolder);

        int current = pagedListHolder.getPage() + 1;
        int begin = Math.max(1, current - NUMBER_OF_PAGE_SELECTORS);
        int end = Math.min(begin + NUMBER_OF_PAGE_SELECTORS, pagedListHolder.getPageCount());
        int totalPageCount = pagedListHolder.getPageCount();
//        String baseUrl = "photos/page/";
        String baseUrl = "";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("photos", pagedListHolder);
        model.addAttribute("now", LocalDateTime.now());

        return "photoList";
    }
}
