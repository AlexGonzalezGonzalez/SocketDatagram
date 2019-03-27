
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agonzalezgonzalez
 */
public class Cliente1 implements Runnable {

    static boolean cli1 = false;
    DatagramSocket datagramSocket;
    DatagramPacket datagrama1, datagrama2;
    InetSocketAddress addr;
    InetAddress addr2;
    Socket clienteSocket;
    byte[] mensaje = new byte[1];

    Cliente1() {

        conexion();

        new Thread(this).start();
    }

   

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            escribirMensaje();

        }

        System.out.println("Cerrando el socket datagrama");
        datagramSocket.close();
        System.out.println("Terminado");

    }

    public void conexion() {
        try {
            System.out.println("Creando Socket Datagrama");
            addr = new InetSocketAddress("localhost", 5555);
            datagramSocket = new DatagramSocket(addr);

        } catch (SocketException ex) {
            Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String leerMensaje() {
        try {

            System.out.println("Recibiendo mensaje");
            byte[] mensaje = new byte[25];
            datagrama1 = new DatagramPacket(mensaje, 25);
            datagramSocket.receive(datagrama1);
            System.out.println("Mensaje recibido");

        } catch (IOException ex) {
            Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(mensaje);
    }

    public void escribirMensaje() {
        try {
            addr2 = InetAddress.getByName("localhost");
            System.out.println("Enviando mensaje");
            mensaje=new String("xD").getBytes();
            datagrama2 = new DatagramPacket(mensaje, mensaje.length, addr2, 5556);
            datagramSocket.send(datagrama2);
            System.out.println("Mensaje enviado");

        } catch (IOException ex) {
            Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
