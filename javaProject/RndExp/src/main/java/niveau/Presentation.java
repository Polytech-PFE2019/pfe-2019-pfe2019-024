package niveau;



import java.util.ArrayList;

public class Presentation {

    private String nom ;
    private ArrayList < Colonne > variables ;

    public Presentation(String nom) {
        this.nom = nom;
        variables = new ArrayList<Colonne>();
    }

    public Presentation(){

    }

    public void addColonne(Colonne variable){
        variables.add(variable);
    }

    public ArrayList<Colonne> getColonnes() {
        return variables;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        String res = "Colonnes : [ ";
        for (int i = 0; i < variables.size(); i++) {
            res = res + variables.get(i) + ", ";
        }
        return res +" ]";
    }
}
