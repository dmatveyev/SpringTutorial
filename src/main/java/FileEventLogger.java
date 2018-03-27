import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String fileName;


    public FileEventLogger(final String fileName) {
        this.fileName = fileName;
    }

    private void  init() throws IOException {
        File file = new File(fileName);
        if (!file.canWrite()) {
            throw new IOException();
        }
    }

    public void logEvent(final Event event) {
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file, event.getMsg(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}