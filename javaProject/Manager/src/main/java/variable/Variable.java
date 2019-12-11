package variable;

public abstract class Variable {

    private String nom;
    private String libelle;
    private String description;
    private String annotation;

    public Variable(){

    }

    public Variable(String nom, String libelle, String description, String annotation){
        this.nom = nom;
        this.libelle = libelle;
        this.description = description;
        this.annotation = annotation;
    }

    public Variable(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    @Override
    public String toString(){
        return "nom : " + nom;
    }
}
