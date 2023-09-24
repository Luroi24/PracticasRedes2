import javax.swing.JFileChooser;
import java.io.File;
import java.util.ArrayList;

public class FileManagement {
    private String nombre;
    private String path;
    private long tam;
    private File route;

    public FileManagement() {
        route = new File("");
    }

    public String getNombre() {
        return nombre;
    }

    public String getPath() {
        return path;
    }

    public long getTam() {
        return tam;
    }

    public String setupDirectory(){
        String directory = "archivos";
        String dir_files = route.getAbsolutePath() + "\\" + directory + "\\";
        File f2 = new File(dir_files);
        f2.mkdirs();
        f2.setWritable(true);
        System.out.println("Ruta: " + dir_files);
        return dir_files;
    }
    public void chooseFile(){
        JFileChooser jf = new JFileChooser();
        //jf.setMultiSelectionEnabled(true);
        int r = jf.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            /*
            File f2 = new File("");
            String z = f2.getAbsolutePath() + "\\";
            System.out.println("Ruta: " + z);
            File f3 = new File(z);
            File[] archivos = f3.listFiles();
            System.out.println("Hay " + archivos.length + " archivos");
            for (int i = 0; i < archivos.length; i++) {
                String xx = archivos[i].getAbsolutePath();
                xx = (archivos[i].isDirectory()) ? xx + "/" : xx;
                System.out.println(xx);
            } */
            String nombre = f.getName();
            String path = f.getAbsolutePath();
            long tam = f.length();
            System.out.println("Archivo por enviar: " + path + " de " + tam + " bytes\n\r");
            this.nombre = nombre;
            this.tam = tam;
            this.path = path;
        }
    }

}
