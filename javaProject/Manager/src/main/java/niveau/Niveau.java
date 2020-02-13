package niveau;

import niveau.tools.*;
import variable.Variable;

import java.util.ArrayList;

public class Niveau {

    private ArrayList < Presentation > presentations ;
    private ArrayList < Variable > variables ;
    private ArrayList < Etiquette > etiquettes ;
    private ArrayList < Formulaire > formulaires ;
    private ArrayList < Interface > interfaces ;
    private ArrayList < InterfaceMatricielle > interfaceMatricielles ;
    private ArrayList < PresentationMatricielle > presentationMatricielles ;
    private ArrayList <Rapport> rapports ;

    private int id;

    public Niveau(int id){
        this.id = id;
        etiquettes = new ArrayList<>();
        rapports = new ArrayList<>();
        variables = new ArrayList<Variable>();
        presentations = new ArrayList<Presentation>();
    }

    public void addVariable(Variable variable){
        this.variables.add(variable);
    }

    public void addPresentation(Presentation presentation){
        this.presentations.add(presentation);
    }

    public void addRapport(Rapport rapport){
        this.rapports.add(rapport);
    }

    public void addEtiquette(Etiquette etiquette){
        this.etiquettes.add(etiquette);
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList variables) {
        this.variables = variables;
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

    public ArrayList<Etiquette> getEtiquettes() {
        return etiquettes;
    }

    public ArrayList<Rapport> getRapports() {
        return rapports;
    }

    @Override
    public String toString(){
        String res = "Variables : [ ";
        for(int i = 0 ; i< variables.size();i++){
            res = res + "[ "+variables.get(i)+"], ";
        }
        res = res + "], \nPresentation : ";
        for(int i = 0 ; i< presentations.size();i++){
            res = res + "nom : "+presentations.get(i).getNom()+" " +presentations.get(i)+", ";
        }
        return res;
    }




}
