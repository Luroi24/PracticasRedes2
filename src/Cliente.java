import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;

public class Cliente {
    public int puerto = 8000;
    public String dir;
    public Socket cl;
    private DataOutputStream dos;
    private DataInputStream dis;
    public Cliente(String dir, int puerto){
        this.puerto = puerto;
        this.dir = dir;
        try{
            this.cl = new Socket(this.dir,this.puerto);
            this.dos = new DataOutputStream(this.cl.getOutputStream());
            //this.dis = new DataInputStream(this.cl.getInputStream());
            System.out.println("Cliente inicializado ...");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void closeDos(){
        try{
            this.dos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeDis(){
        try{
            this.dis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeCl(){
        try{
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void closeAll(){
        closeDis();
        closeDos();
        closeCl();
    }
    public void sendMessage(){
        try{
            String v = "Hola :D";
            dos.writeUTF(v);
            dos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFileDis(String path){
        try {
            this.dis = new DataInputStream(new FileInputStream(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void uploadFile(){
        // Preparación del archivo. El usuario elige el archivo a enviar y obtenemos su meta información
        FileManagement jf = new FileManagement();
        jf.chooseFile();
        if(!jf.getNombre().isEmpty()){ // ¿Se eligió archivo?
            String name = jf.getNombre();
            long tam = jf.getTam();
            try {
                dos.writeUTF(name);
                dos.flush();
                dos.writeLong(tam);
                dos.flush();
                openFileDis(jf.getPath());
                long enviados = 0;
                int l=0, porcentaje = 0;
                while(enviados < tam){
                    byte[] b = new byte[1500];
                    l = dis.read(b);
                    System.out.println("Enviados: "+l);
                    dos.write(b,0,l);
                    dos.flush();
                    enviados = enviados + l;
                    porcentaje = (int) ((enviados*100)/tam);
                    System.out.println("\rEnviado el "+porcentaje+" % del archivo");
                }
                System.out.println("Archivo enviado exitosamente");
            }catch (Exception e){ e.printStackTrace();}
        }else{
            System.out.println("No se eligió ningún archivo.");
        }
    }
}
