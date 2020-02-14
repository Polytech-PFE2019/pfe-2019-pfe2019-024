package dsl.builder;

import niveau.Presentation;
import niveau.tools.Etiquette;
import niveau.tools.Rapport;
import structure.Structure;

public class NiveauBuilder {
    private ScenarioBuilder parent;
    private int niveau;

    public NiveauBuilder(ScenarioBuilder parent,int niveau){
        this.parent = parent;
        this.niveau = niveau-1;
    }

    public VariableNiveauBuilder DebutAjoutVariables(){
        return new VariableNiveauBuilder(this, parent.getStructure().getNiveaux().get(niveau));
    }

    public PresentationBuilder ConfigurationPresentation(String name){
        Presentation presentation =new Presentation(name);
        parent.getStructure().getNiveaux().get(niveau).addPresentation(presentation);

        return new PresentationBuilder(this,presentation);
    }

    public NiveauBuilder ConfigurationRapport(){

        parent.getStructure().getNiveaux().get(niveau).addRapport(new Rapport());
        return this;
    }

    public NiveauBuilder ConfigurationEtiquette(){
        parent.getStructure().getNiveaux().get(niveau).addEtiquette(new Etiquette());
        return this;
    }

    public ScenarioBuilder FinConfigurationNiveau(){

        System.out.println(parent.getStructure().getNiveaux().get(niveau));
        return parent;
    }

    public Structure getStructure(){
        return this.parent.getStructure();
    }
}
