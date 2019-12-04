package variable;

public class ColonneUtilisateur extends Variable {

    private String type;

    public ColonneUtilisateur(){
        super();
    }

    public ColonneUtilisateur(String nom, String libelle, String description, String annotation, String type) {
        super(nom, libelle, description, annotation);
        this.type = type;

    }

    public ColonneUtilisateur(String name, String type) {

        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return "nom : " + getNom() + ", type : " + type;
    }
}
