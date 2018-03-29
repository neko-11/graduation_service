package cn.edu.ahut.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveImage {

    private static final Logger logger = LoggerFactory.getLogger(SaveImage.class);

    //save file
    public static List<Path> saveMultipleFiles(String folder, List<MultipartFile> files) throws IOException {

        List<Path> savedFiles = new ArrayList<Path>();
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            String fileName = originalFilename.split("\\.")[0] +
                    "_" + System.currentTimeMillis() + "." + getFileExtension(originalFilename);
            Path path = Paths.get(folder + File.separator + fileName);
            savedFiles.add(path);
            logger.debug("saving to : " + path.toAbsolutePath());
            Files.write(path, bytes);
        }
        return savedFiles;
    }


    //save file
    public static boolean saveSingleFile(String folder, MultipartFile file, String fileName) throws IOException {

            if (file.isEmpty()) {
                return false;
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + File.separator + fileName);
            logger.debug("saving to : " + path.toAbsolutePath());
            Files.write(path, bytes);
            return true;
    }

    //save file
    public static void saveSingleFile(Path fullPath, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return;
        }
        byte[] bytes = file.getBytes();
        logger.debug("saving to : " + fullPath);
        Files.write(fullPath, bytes);

    }

    public static String getFileExtension(String fname) {
        try {
            return fname.substring(fname.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
