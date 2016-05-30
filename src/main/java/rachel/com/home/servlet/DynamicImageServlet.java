//package rachel.com.home.servlet;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
////import java.io.BufferedInputStream;
//
//public class DynamicImageServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Path filePath = null;
//        try {
//            filePath = Paths.get(request.getParameter("file"));
//
//            // Get image file.
//            String file = request.getParameter("file");
//
//            if (request.getRequestURL().toString().contains("openfile")) {
//                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//
//                // Get image contents.
//                byte[] bytes = new byte[in.available()];
//
//                in.read(bytes);
//                in.close();
//
//                // Write image contents to response.
//                response.getOutputStream().write(bytes);
//                response.getOutputStream().close();
//            } else if (request.getRequestURL().toString().contains("deletefile")) {
//                System.out.println("deleting file" + filePath);
//                new File(file).delete();
//            }
//        } catch (IOException e) {
//            System.out.println("file causing exception = " + filePath);
//            e.printStackTrace();
//        }
//    }
//
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Path filePath = null;
//        try {
//            filePath = Paths.get(request.getParameter("file"));
//
//            // Get image file.
//            String file = request.getParameter("file");
//
//            if (request.getRequestURL().toString().contains("openfile")) {
//                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//
//                // Get image contents.
//                byte[] bytes = new byte[in.available()];
//
//                in.read(bytes);
//                in.close();
//
//                // Write image contents to response.
//                response.getOutputStream().write(bytes);
//                response.getOutputStream().close();
//            } else if (request.getRequestURL().toString().contains("deletefile")) {
//                System.out.println("deleting file" + filePath);
//                new File(file).delete();
//            }
//        } catch (IOException e) {
//            System.out.println("file causing exception = " + filePath);
//            e.printStackTrace();
//        }
//    }
//}
