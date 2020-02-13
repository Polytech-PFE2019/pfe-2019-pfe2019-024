package dsl.builder;

import niveau.Niveau;
import niveau.Presentation;
import variable.ColonneUtilisateur;
import variable.ConditionsExperimentale;

public class VariableNiveauBuilder {

    private Niveau niveau;
    private NiveauBuilder parent;

    public VariableNiveauBuilder(NiveauBuilder parent, Niveau niveau) {
        this.parent = parent;
        this.niveau = niveau;
    }

    public VariableNiveauBuilder AddUserColonne(String name, String type){
        niveau.addVariable(new ColonneUtilisateur(name,type));
        System.out.println(niveau.toString());
        return this;
    }

    public VariableNiveauBuilder AddConditionExp(String name, String type){
        niveau.addVariable(new ConditionsExperimentale(name,type));
        System.out.println(niveau.toString());
        return this;
    }
    public NiveauBuilder EndAddVariable(){
        return parent;
    }

}
