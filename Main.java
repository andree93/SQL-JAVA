import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    //PLEASE INSERT HERE YOUR DB CONNECTION PARAMETERS, OR TYPE AT RUNTIME, UNCOMMENTING INPUT LINES (22 -31)
    public static String nome = "";
    public static String host = "localhost";
    public static String username = "root";
    public static String password = "";
    public static int port = 3306;

    public static String QUERY_SELECT_NOME = "SELECT * FROM persone WHERE nome = ";
    public static String QUERY_DELETE_NUMERO = "DELETE FROM persone WHERE numero = ";
    public static String QUERY_SELECT_ALL = "SELECT * FROM persone";


    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        /**System.out.println ("Nome db: ");
        nome = in.nextLine ( );
        System.out.println ("Hostname: ");
        host = in.nextLine ( );
        System.out.println ("Username: ");
        username = in.nextLine ( );
        System.out.println ("Password: ");
        password = in.nextLine ( );
        System.out.println ("Porta: ");
        port = in.nextInt ( ); */
        DBConnection dbConnection = new DBConnection (nome, host, username, password, port);
        Connection connection = null;
        List<Persona> persone = null;
        try {
            connection = dbConnection.getConnection ( );
        } catch (SQLException e) {
            //e.printStackTrace ( );
            System.out.println ("ERRORE CONNESSIONE AL DB!!");
        }
        DBQuery dbQuery = new DBQuery (connection);

        while (true) {
            System.out.println ("Seleziona azione da eseguire:");
            System.out.println ("1 - Query al DB di tutte le persone presenti\n2 - Inserisci nuova persona\n3 - Cancella persona\n4 - Cerca persona per nome\n5 - Esci");
            int scelta = in.nextInt ( );
            in.nextLine (); //pulizia buffer
            switch (scelta) {
                case 1: {
                    try {
                        persone = dbQuery.select (QUERY_SELECT_ALL);
                    } catch (SQLException e) {
                        e.printStackTrace ( );
                    }
                    if (persone != null) {
                        for (Persona p :
                                persone) {
                            System.out.println (p);
                        }
                    } else {
                        System.out.println ("Lista vuota!");
                    }
                    break;
                }
                case 2: {
                    System.out.println ("Inserisci Nome: ");
                    String tmpnome = in.nextLine ( );
                    System.out.println ("Inserisci Cognome: ");
                    String tmpcognome = in.nextLine ( );
                    System.out.println ("Inserisci Citta: ");
                    String tmpcitta = in.nextLine ( );
                    System.out.println ("Inserisci Numero: ");
                    String tmpnumero = in.nextLine ( );
                    try {
                        dbQuery.inserisci (tmpnome, tmpcognome, tmpcitta, tmpnumero);
                    } catch (SQLException e) {
                        e.printStackTrace ( );
                        System.out.println ("Errore aggiunta! PK gia esistente?");
                    }

                } break;

                case 3: {
                    System.out.println ("Inserisci Numero di telefono da cancellare: ");
                    String tmpnumero = in.nextLine ( );
                    try {
                        dbQuery.cancellaNumero (QUERY_DELETE_NUMERO, tmpnumero);
                        System.out.println ("Cancellazione eseguita!");
                    } catch (SQLException e) {
                        e.printStackTrace ( );
                        System.out.println ("Errore cancellazione!");
                    }
                } break;
                case 4:{
                    System.out.println ("Inserisci Nome: ");
                    String tmpnome = in.nextLine ();
                    String q = QUERY_SELECT_NOME + "'"+tmpnome+"'";
                    try {
                        persone = dbQuery.select (q);
                    } catch (SQLException e) {
                        e.printStackTrace ( );
                    }
                    if (persone != null) {
                        for (Persona p :
                                persone) {
                            System.out.println (p);
                        }
                    } else {
                        System.out.println ("Lista vuota!");
                    }
                    break;

                }
                case 5:
                    System.exit (0);
                default:
                    System.out.println("Scelta non valida, riprova");
                    break;
            }


        }
    }


}
