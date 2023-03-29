package dao;

public class Utilisateur {
    protected Long id;
    protected String login,mdp,nom,prenom;
    protected Role role;


    public String nomComplet()
    {
        return nom + " " + prenom;
    }

    public void setNomcomplet(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
