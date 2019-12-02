package niveau;

import variable.Variable;

import java.util.ArrayList;

public class Niveau {

    private ArrayList < Presentation > presentations;
    private ArrayList < Variable > variables;

    private int id;

    public Niveau(int id){
        this.id = id;
        variables = new ArrayList<Variable>();
        presentations = new ArrayList<Presentation>();
    }

    public void addVariable(Variable variable){
        this.variables.add(variable);
    }

    public void addPresentation(Presentation presentation){
        this.presentations.add(presentation);
    }
    public ArrayList getVariables() {
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



}
