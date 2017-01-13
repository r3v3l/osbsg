package controllers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import play.mvc.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by astolarski on 09.01.17.
 */
public class FileUploadController extends Controller {

    public boolean upload(String path, String filename, File file) throws IOException {

        File directory = new File(path);
        if(!directory.exists()){
            if(!directory.mkdirs()){
                return false;
            }
        }

        File currentFile = new File(path + "/" + filename);
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));
        FileUtils.writeByteArrayToFile(new File(path + "/" + filename), bytes);
        return currentFile.exists();

    }

    public boolean remove(String path, String filename) throws IOException {

        File directory = new File(path);
        if(!directory.exists()){
            return false;
        }

        File currentFile = new File(path + "/" + filename);
        currentFile.delete();
        return currentFile.exists();

    }


}
