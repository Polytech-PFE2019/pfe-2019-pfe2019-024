package dsl.builder;

import niveau.Niveau;
import niveau.Presentation;
import variable.ColonneUtilisateur;

public class PresentationBuilder {

    private Presentation presentation;
    private NiveauBuilder parent;

    public PresentationBuilder(NiveauBuilder parent, Presentation presentation) {
        this.parent =parent;
        this.presentation = presentation;
    }

    public PresentationBuilder AjoutColonneUtilisateur(String name, String type){
        presentation.addVariable(new ColonneUtilisateur(name, type));
        return this;
    }

    public NiveauBuilder FinConfigurationPresentation(){

        return this.parent;
    }
}
