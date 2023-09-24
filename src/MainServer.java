import java.io.File;

public class MainServer {
    public static void main(String[] args) {
        int pto = 8000;
        Servidor metaInfoSv = new Servidor(pto);
        //Servidor infoSv = new Servidor(pto+1);
        FileManagement dir = new FileManagement();
        String dir_files = dir.setupDirectory();
        while(true){
            metaInfoSv.acceptCon();
            //metaInfoSv.awaitMessage();
            metaInfoSv.awaitFile(dir_files);
            metaInfoSv.closeAll();
        }
    }
}
