package trycloud.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.Arrays;

public class HandleDataToList implements Serializable {
    private File files;
    private byte FileDatas[];
    private byte FileReturnData[][];
    private FileInputStream FileSteam;
    private String FileName;
    private static final long FILE_LONG = 0;


    public HandleDataToList(String FileURL) throws IOException {

        this.files = new File(FileURL);
        if(this.files.exists()){
            this.FileDatas =new byte[(int)this.files.length()];
            this.FileName = this.files.getName();
            try {
                //this.FileData = new DataOutputStream(SocketData.getOutputStream());
                this.FileSteam = new FileInputStream(this.files);
                FileSteam.read(FileDatas);
                this.FileReturnData =  splitBytes(FileDatas,1024);

                if (FileDatas.length == FILE_LONG)
                {
                    this.FileSteam.close();
                  System.out.println("\n" +"Client file data exception");
              }
             System.out.println("\n" +"Had Send Data "+FileDatas.length+"\n" );

            }catch(IOException e){
                e.printStackTrace();
              System.out.println("Client file conversion exception");
            }finally{
                this.FileSteam.close();
            }
        }else
        {
            this.FileReturnData = null;
        }

    }

    public byte[][] getData()
    {
        return this.FileReturnData;
    }
    public String getName()
    {
        return this.FileName;
    }

    private byte[][] splitBytes(byte[] bytes, int size) {
        double splitLength = Double.parseDouble(size + "");
        int arrayLength = (int) Math.ceil(bytes.length / splitLength);
        byte[][] result = new byte[arrayLength][];
        int from, to;
        for (int i = 0; i < arrayLength; i++) {

            from = (int) (i * splitLength);
            to = (int) (from + splitLength);
            if (to > bytes.length)
                to = bytes.length;
            result[i] = Arrays.copyOfRange(bytes, from, to);
        }
        return result;
    }
}
