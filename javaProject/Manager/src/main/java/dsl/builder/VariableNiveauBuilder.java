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

    public VariableNiveauBuilder AjoutColonneUtilisateur(String name, String type){
        niveau.addVariable(new ColonneUtilisateur(name,type));
        return this;

    }

    public VariableNiveauBuilder AjoutConditionExperimental(String name, String type){
        niveau.addVariable(new ConditionsExperimentale(name,type));
        return this;
    }
    public NiveauBuilder EndAddVariable(){
        return parent;
    }

}
