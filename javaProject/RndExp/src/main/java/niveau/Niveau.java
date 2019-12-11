package niveau;

import niveau.tools.*;

import java.util.ArrayList;

public class Niveau {

    private ArrayList < Presentation > presentations ;
    private ArrayList < Colonne > colonnes ;
    private ArrayList < Etiquette > etiquettes ;
    private ArrayList < Formulaire > formulaires ;
    private ArrayList < Interface > interfaces ;
    private ArrayList < InterfaceMatricielle > interfaceMatricielles ;
    private ArrayList < PresentationMatricielle > presentationMatricielles ;
    private ArrayList < Rapports > rapports ;

    private int id;

    public Niveau(int id){
        this.id = id;
        colonnes = new ArrayList< Colonne >();
        presentations = new ArrayList<Presentation>();
    }

    public void addVariable(Colonne colonne){
        this.colonnes.add(colonne);
    }

    public void addPresentation(Presentation presentation){
        this.presentations.add(presentation);
    }
    public ArrayList getVariables() {
        return colonnes;
    }

    public void setVariables(ArrayList colonnes) {
        this.colonnes = colonnes;
    }
    public ArrayList<Presentation> getPresentations() {
        return presentations;
    }
    public Presentation getPresentation(String nom) {
        for(int i = 0; i < presentations.size();i++){

                if(presentations.get(i).getNom().equals(nom)){
                    return presentations.get(i);
                }
        }
        return null;
    }
    public void setPresentations(ArrayList<Presentation> presentations) {
        this.presentations = presentations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        String res = "Variables : [ ";
        for(int i = 0 ; i< colonnes.size();i++){
            res = res + "[ "+colonnes.get(i)+"], ";
        }
        res = res + "], \nPresentation : ";
        for(int i = 0 ; i< presentations.size();i++){
            res = res + "nom : "+presentations.get(i).getNom()+" " +presentations.get(i)+", ";
        }
        return res;
    }

}
