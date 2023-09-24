public class MainCliente {
    public static void main(String[] args) {
        int pto = 8000;
        Cliente metaInfoCl = new Cliente("127.0.0.1",pto);
        //Cliente infoCl = new Cliente("127.0.0.1",pto+1);
        //metaInfoCl.sendMessage();
        metaInfoCl.uploadFile();
        metaInfoCl.closeAll();
    }
}
