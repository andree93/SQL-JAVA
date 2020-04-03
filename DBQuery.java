import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

/**

Il Db dovrà avere i seguenti campi:
nome (VARCHAR)
cognome (VARCHAR)
citta (VARCHAR)
numero (VARCHAR), PK
*/

public class DBQuery {
    Connection connection = null;
    public static String QUERY_SELECT_NOME = "FROM persone SELECT * WHERE nome = ";
    public static String QUERY_SELECT_COGNOME = "FROM persone SELECT * WHERE cognome = ";
    public static String QUERY_SELECT_CITTA = "FROM persone SELECT * WHERE citta = ";
    public static String QUERY_SELECT_NUMERO = "FROM persone SELECT * WHERE numero = ";

    public static String QUERY_DELETE_NOME = "DELETE FROM persone WHERE nome = ";
    public static String QUERY_DELETE_COGNOME = "DELETE FROM persone WHERE WHERE cognome = ";
    public static String QUERY_DELETE_CITTA = "DELETE FROM persone WHERE WHERE citta = ";
    public static String QUERY_DELETE_NUMERO = "DELETE FROM persone WHERE WHERE numero = ";

    public DBQuery(Connection connection) {
        this.connection =  connection;
    }

    public List<Persona> select(String query) throws SQLException {
        List<Persona> persone = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement (query);
        try (ResultSet resultSet = ps.executeQuery()) { 
          while (resultSet.next ()){
              String tmpnome = resultSet.getString (1);
              String tmpcognome = resultSet.getString (2);
              String tmpcitta= resultSet.getString (3);
              String tmpnumero= resultSet.getString (4);
              persone.add (new Persona (tmpnome, tmpcognome, tmpcitta, tmpnumero));
          }
        }
        return persone;
    }

    public void inserisci (String nome, String cognome, String citta, String numero) throws SQLException {
        String query = "INSERT INTO persone (nome, cognome, citta, numero) "+"VALUES("+"'"+nome+"'"+", "+"'"+cognome+"'"+", "+"'"+citta+"'"+", "+"'"+numero+"'"+")";
        PreparedStatement ps = connection.prepareStatement (query);
        ps.executeUpdate ();
    }

    public void cancellaNumero (String query, String numero) throws SQLException {
        query +="'"+numero+"'";
        PreparedStatement ps = connection.prepareStatement (query);
        ps.executeUpdate ();
    }

}
