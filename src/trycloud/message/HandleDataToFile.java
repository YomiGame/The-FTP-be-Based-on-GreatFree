package trycloud.message;

import java.io.*;

public class HandleDataToFile implements Serializable {
    public HandleDataToFile(byte[] dataByte,String FileName,String FileInUrl) throws IOException {
        File directory = new File(FileInUrl+FileName);
        File Folder = new File(FileInUrl);
        System.out.println(FileInUrl);
        if(!Folder.exists())
        {
            Folder.mkdir();
        }

        if(!directory.exists()) {


            File file = new File(FileInUrl + File.separatorChar + FileName);

            OutputStream output = new FileOutputStream(file);

            output.write(dataByte);

            System.out.println("\n Save File Succeed");

            output.close();

        }else
        {
            System.out.println("You had the file in client");
        }

    }

}
