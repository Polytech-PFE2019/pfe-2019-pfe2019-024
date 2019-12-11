package niveau;

import variable.Variable;

import java.util.ArrayList;

public class Presentation {

    private String nom ;
    private ArrayList < Variable > variables ;

    public Presentation(String nom) {
        this.nom = nom;
        variables = new ArrayList<Variable>();
    }

    public Presentation(){

    }

    public void addVariable(Variable variable){
        variables.add(variable);
    }

    public ArrayList<Variable> getVariables() {
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
        String res = "Variables : [ ";
        for (int i = 0; i < variables.size(); i++) {
            res = res + variables.get(i) + ", ";
        }
        return res +" ]";
    }
}
