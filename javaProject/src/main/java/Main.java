import niveau.Presentation;
import structure.factory.StructureFactory;
import structure.Structure;
import variable.*;
import variable.factory.*;
import structure.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        try {
            GeneratePlantUml test = new GeneratePlantUml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VariableFactory factoryColonnesUtilisateur = new VariableFactoryColonnesUtilisateur();
        StructureFactory factoryStructure = new StructureFactory();
        Structure contact = factoryStructure.getStructure("contact",2,"Stock");
        ColonneUtilisateur dColonneUser = (ColonneUtilisateur)factoryColonnesUtilisateur.getVariable("date", "DATE");
        System.out.println("name : "+dColonneUser.getNom()+ " type : "+dColonneUser.getType());
        Presentation general = new Presentation("general");
        contact.getNiveau(1).addPresentation(general);
        contact.getNiveau(1).getPresentation("general").addVariable(dColonneUser);

        System.out.println(""+contact.getNiveau(1).getPresentation("general").getVariables().get(0));
    }
}
