package trycloud.message;

import java.io.File;
import java.util.ArrayList;


public class FindOutAllFile {

      private ArrayList<String> FileList;

      public FindOutAllFile(String pathURL)
      {
          this.FileList = new ArrayList<String>();
          File url = new File(pathURL);
          GetAllFilePath(url);
      }

      private void GetAllFilePath(File file) {
          File[] files = file.listFiles();
          for (File f : files) {

              if (f.isDirectory()) {
                  System.out.println("----------------" + f.getAbsolutePath()
                          + "-------------");
                  GetAllFilePath(f);
              } else {
                  this.FileList.add(f.getAbsolutePath());
                  System.out.println(f.getAbsolutePath());
              }
          }
      }

      public ArrayList<String> GetFilePathList() {
          return this.FileList;
        }



}

