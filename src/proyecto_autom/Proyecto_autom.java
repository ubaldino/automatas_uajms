
package proyecto_autom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class Proyecto_autom  implements ActionListener{
    private Vista vista;
    private Modelo modelo;
    public Proyecto_autom(){
           
        //vista = new Vista();
        modelo = new Modelo();
        //vista.setVisible( true );
        
        //JButton btn_validar = vista.btn_validar;
        //btn_validar.addActionListener( this );
        /*
        JTextField estados = vista.txt_estados;
        JTextField alfabeto = vista.txt_alfabeto;
         */
        testModelo();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println( "bostom" );
    }
    
    public static void main( String[] args ) {
        Proyecto_autom proyecto_autom = new Proyecto_autom();
    }

    private void testModelo() {
        
        
        System.out.println("alfabeto");
        modelo.parseAlfabeto("ab").stream().forEach(System.out::println);
        System.out.println();
        
        System.out.println("Estados");
        modelo.parseEstados( "q1,q2,q3" ).stream().forEach(System.out::println);
        System.out.println();
        int tamanioFilas = modelo.getAlfabeto().size();
        int tamanioColumnas = modelo.getEstados().size();
        modelo.crearMatrizTransicion( tamanioFilas , tamanioColumnas );
        
        System.out.println();
        System.out.println( "Tamanio Filas: " + modelo.getMatrizTransicion()[0].length );
        System.out.println( "Tamanio Columnas: " + modelo.getMatrizTransicion().length );
        
    }
    
}
