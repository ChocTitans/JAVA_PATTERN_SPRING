package BD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestJDBC {
    public static void main(String[] args) {

        Connection connection = null;

        try {

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("application.properties");

            if(config == null) throw new IOException("Fichier properties introuvable");
            Properties propertiesFile = new Properties();
            propertiesFile.load(config);

            var url = propertiesFile.getProperty("URL");
            var user = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");

            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("le driver de mysql a ete charge avec succes");

            var statement = connection.createStatement();
            var rs = statement.executeQuery("SELECT cr.id, cr.capital, cr.nom," +
                    "cr.prenom from credits");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.err.println("Connexion echoue");
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
    }
}
