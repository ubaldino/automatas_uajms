
package proyecto_autom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;


public class Proyecto_autom  implements ActionListener{
    private Vista vista;
    private Modelo modelo;
    @SuppressWarnings("LeakingThisInConstructor")
    public Proyecto_autom(){
           
        vista = new Vista();
        modelo = new Modelo();
        vista.setVisible( true );
        
        //JButton btn_validar = ;
        vista.btn_evaluar.addActionListener( this );
        
        vista.txt_alfabeto.setText( "a,b" );
        vista.txt_estados.setText( "q0,q1,q2" );
        vista.txt_estado_inicial.setText( "q0" );
        vista.txt_estados_finales.setText( "q2" );
        vista.txt_transiciones.setText( "a = q0->q1\na = q1->q1\nb = q1->q2\nb = q2->q2" );
        vista.txt_palabra.setText( "abbb" );
        vista.lbl_infomacion.setForeground( Color.DARK_GRAY );
        Font font_lbl = new Font( "Serif", Font.BOLD, 20 );
        vista.lbl_infomacion.setFont( font_lbl );
        //testModelo();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if ( e.getSource().equals( vista.btn_evaluar ) ) {
            do{
                Font font_lbl = new Font( "Serif", Font.BOLD, 20 );
                vista.lbl_infomacion.setFont( font_lbl );
                vista.lbl_infomacion.setForeground( Color.DARK_GRAY );
                vista.lbl_infomacion.setText( "Analizando Palabra" );
                modelo = new Modelo();

                modelo.parseAlfabeto( vista.txt_alfabeto.getText() );
                modelo.parseEstados( vista.txt_estados.getText() );

                modelo.setEstadoInicial( vista.txt_estado_inicial.getText() );

                modelo.setEstadosFinales( vista.txt_estados_finales.getText() );

                modelo.crearMatrizTransicion( modelo.getEstados().size() , modelo.getAlfabeto().size() );



                String lista_transiciones[] =  vista.txt_transiciones.getText().split( "\n" ) ;

                // implementar funciones de validacion

                boolean validar_palabra = modelo.verificarSimbolosAlfabeto( vista.txt_palabra.getText() );
                if ( ! validar_palabra ) {
                    font_lbl = new Font( "Serif", Font.BOLD, 17 );
                    vista.lbl_infomacion.setFont( font_lbl );
                    vista.lbl_infomacion.setForeground( Color.RED );
                    System.out.println( "Los simbolos de la palabra no coinciden con el alfabeto" );
                    vista.lbl_infomacion.setText( "Los simbolos de la palabra no coinciden con el alfabeto" );
                    break;
                }
                // Empezar con el analisis de la palabra
                @SuppressWarnings("UnusedAssignment")
                String simbolo = null;
                @SuppressWarnings("UnusedAssignment")
                String estadoInicial = null;
                @SuppressWarnings("UnusedAssignment")
                String estadoFinal = null;


                for ( String linea : lista_transiciones ) {
                    simbolo       = linea.split( "=" )[0].trim();
                    estadoInicial = linea.split( "=" )[1].trim().split( "->" )[ 0 ]  ;
                    estadoFinal   = linea.split( "=" )[1].trim().split( "->" )[ 1 ]  ;


                    int posSimbolo = -1 , posEstadoInicial = -1 , posEstadoFinal = -1 ;

                    for (int i = 0; i < modelo.getAlfabeto().size() ; i++) {
                        if ( modelo.getAlfabeto().get(i).equals( simbolo ) ) {
                            posSimbolo = i;
                            break;
                        }
                    }

                    for (int i = 0; i < modelo.getEstados().size() ; i++) {

                        if ( modelo.getEstados().get( i ).equals( estadoInicial ) ) {
                            posEstadoInicial = i;
                        }

                        if ( modelo.getEstados().get( i ).equals( estadoFinal ) ) {
                            posEstadoFinal = i;
                        }

                    }

                    modelo.getMatrizTransicion()[posEstadoInicial][posSimbolo] = posEstadoFinal ;

                    //System.out.println( "[" + modelo.getEstadoInicial() + "][" + posSimbolo + "] => " + posEstadoFinal );
                    //System.out.println( "[" + posEstadoInicial + "][" + posSimbolo + "] => " + posEstadoFinal );
                    //System.out.println( "posicionEstadoInicial: " + posEstadoInicial );
                    //System.out.println( "posicionEstadoFinal: " + posEstadoFinal );
                }

                /*
                System.out.println( );
                for ( int i = 0 ; i < modelo.getMatrizTransicion().length ; i++ ) {
                    for ( int j = 0 ; j < modelo.getMatrizTransicion()[0].length ; j++ ) {
                        System.out.print( "\t" + modelo.getMatrizTransicion()[i][j] );
                    }
                    System.out.println( );
                }
                */


                Queue<String> simbolos = new LinkedList();

                for ( String _simbolo : vista.txt_palabra.getText().trim().split("") ) {
                    simbolos.add(_simbolo);
                }

                /*
                System.out.println("Estado inicial");
                System.out.println( modelo.getEstadoInicial() );

                System.out.println("Estados finales");

                for( int a : modelo.getPosEstadosFinales() ){
                    System.out.println( a );
                }
                *

                /**
                 * comenzar a evaluar la palabra, primero buscar el 
                 * estado inicial para el primer simbolo
                */
                @SuppressWarnings("UnusedAssignment")
                String simboloActual = "";
                int posicionFila = modelo.getEstadoInicial();
                @SuppressWarnings("UnusedAssignment")
                int posicionColumna = -1;
                while( !simbolos.isEmpty() ){
                    simboloActual = simbolos.poll() ;
                    posicionColumna = modelo.getPosisionSimbolo( simboloActual );
                    if ( posicionFila != -1 ) {
                        posicionFila = modelo.getMatrizTransicion()[posicionFila][posicionColumna];
                        modelo.ACEPTADO = true;
                    }
                    else{
                        modelo.ACEPTADO = false;
                        break;
                    }
                }
                // comprobando que la palabra termino en un estado de aceptacion

                if ( modelo.ACEPTADO ) {
                    modelo.ACEPTADO = false;
                    for ( int i = 0; i < modelo.getPosEstadosFinales().size() ; i++ ) {
                        if ( modelo.getPosEstadosFinales().get(i) == posicionFila ) {
                            modelo.ACEPTADO = true;
                            break;
                        }
                    }
                }


                System.out.println( ( modelo.ACEPTADO ) ? "Aceptado" : "Rechazado" );

                vista.lbl_infomacion.setText( "Palabra " + ( ( modelo.ACEPTADO ) ? "Aceptada" : "Rechazada" ) );



                /*
                for( int i = 0; i < modelo.getEstados().size() ; i++ ) {
                    for( int j = 0; j < modelo.getAlfabeto().size() ; j++ ) {
                        System.out.println( i + " , " + j ); 
                    }
                }
                */
                
            }while(false);
        }
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main( String[] args ) {
        new Proyecto_autom();
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
        System.out.println();
        
        System.out.println( "Creando matriz de transicion" );
        modelo.cargarMatrizTransicion();
        
        
    }
    
}
