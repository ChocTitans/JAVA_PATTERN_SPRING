package BD;

import dao.Client;
import dao.Credit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class TestJDBC {
    public static void main(String[] args) {

        Connection connection = null;
        var crédits = new ArrayList<Credit>();
        try {

          /*  Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("le driver de mysql a ete charge avec succes");
            DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7609632","sql7609632","j2AtLu7Uyl");
            System.out.println("cnx etablie");*/

           ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("application.properties");

            if(config == null) throw new IOException("Fichier properties introuvable");
            Properties propertiesFile = new Properties();
            propertiesFile.load(config);

            var url = propertiesFile.getProperty("URL");
            var user = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");

            connection = DriverManager.getConnection(url,user,pass);

            System.out.println("cnx établie avec succès");

            var statement = connection.createStatement();
            var rs = statement.executeQuery("SELECT cr.id, cr.capital, cr.nbrMois, cr.taux, cr.demandeur,cr.mensualite," +
                    "u.nom, u.prenom from client cl, credit cr, utilisateur u" );

            while(rs.next())
            {
                var id = rs.getLong("id");
                var capital = rs.getDouble("capital");
                var nbrMois = rs.getInt("nbrMois");
                var taux = rs.getDouble("taux");
                var nomd = rs.getString("nom");
                var prenomd = rs.getString("prenom");
                var mensualite = rs.getDouble("mensualite");

                var client = new Client();
                client.setNomcomplet(nomd,prenomd);
                crédits.add(new Credit(id, capital, nbrMois, taux, client, mensualite));

            }

            if(crédits.isEmpty()) throw new SQLException("Aucun crédit trouvé");
            else crédits.forEach(System.out::println);


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if(connection != null)
            {
                try {
                    connection.close();
                    System.out.println("Fermeture avec success");
                }
                catch (SQLException e)
                {
                    System.out.println("Fermeture echoue");

                }
            }
        }
        /*catch(ClassNotFoundException e)
        {
            System.out.println("Drover nn");
        }
        catch(SQLException e)
        {
            System.out.println("Cnx nn");
        }*/
    }
}
