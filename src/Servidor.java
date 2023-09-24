import java.net.*;
import java.io.*;
public class Servidor {
    public int pto;
    public ServerSocket sv;
    private Socket clA;
    private DataOutputStream dos;
    private DataInputStream dis;
    public Servidor(int pto){
        try{
            this.pto = pto;
            this.sv = new ServerSocket(pto);
            this.sv.setReuseAddress(true);
            System.out.println("Servidor inicializado ... Esperando conexión");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void closeCon(){
        try {
            this.clA.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeDos(){
        try {
            this.dos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeDis(){
        try {
            this.dis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeAll(){
        closeDis();
        closeDos();
        closeCon();
    }
    public void acceptCon(){
        try {
            this.clA = this.sv.accept();
            System.out.println("Cliente conectado desde " + this.clA.getInetAddress() + ":" + this.clA.getPort());
            this.dis = new DataInputStream(this.clA.getInputStream());
            //this.dos = new DataOutputStream(this.clA.getOutputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void awaitMessage(){
        try {
            String v = this.dis.readUTF();
            System.out.println("Mensaje recibido: " + v);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFileDos(String path){
        try {
            //closeDos();
            dos = new DataOutputStream(new FileOutputStream(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void awaitFile(String path){
        try {
            String name = this.dis.readUTF();
            System.out.println("Nombre: " + name);
            long tam = dis.readLong();
            System.out.println("Comienza descarga del archivo:  " + name + " de " + tam + " bytes.");
            openFileDos(path+name);
            long recibidos = 0;
            int l = 0, porcentaje = 0;
            while(recibidos < tam){
                byte[] b = new byte[1500];
                l = dis.read(b);
                System.out.println("Leidos: " + l);
                dos.write(b,0,l);
                dos.flush();
                recibidos = recibidos + l;
                porcentaje = (int) ((recibidos*100)/tam);
                System.out.println("\rRecibido el "+ porcentaje + "% del archivo.");
            }
            System.out.println("Archivo recibido");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
