import businessLevel.UserVariable;
import niveau.Presentation;
import structure.Structure;
import structure.factory.StructureFactory;
import variable.ColonneUtilisateur;
import variable.Variable;
import variable.factory.VariableFactoryColonnesUtilisateur;

import java.util.ArrayList;

public class Scenario {

    static final VariableFactoryColonnesUtilisateur factoryColonnesUtilisateur = new VariableFactoryColonnesUtilisateur();
    static final StructureFactory factoryStructure = new StructureFactory();

    //---------Scenario creer une structure
    public static Structure scenarioCreerStructureStockage(String nom, int nbLevel){
        Structure struct = creerStructureStockage(nom, nbLevel);
        return struct;
    }

    //-------- Scenario creer variable
    public static UserVariable scenarioCreerColonneUtilisateur(String nom,String type){
        UserVariable uservariable = createUserVariable(nom,type);
        ColonneUtilisateur dColonneUser =  factoryColonnesUtilisateur.createVariable(nom,type);
        return uservariable;
    }

    public static Presentation scenarioCreerPresentationEtAjouter(Structure structure,int level,String nom){
        Presentation presentation = creerPresentation(structure, level, nom);
        return presentation;
    }

    //-------- scenario : ajouter une variable a une présentation
    public static void scenarioAjoutVariableAPresentation(Presentation presentation, UserVariable variable){
        presentation.addVariable(factoryColonnesUtilisateur.createVariable(variable.getNom(),variable.getType()));
    }

    private static Presentation creerPresentation(Structure structure, int i, String name) {
        Presentation presentation = new Presentation(name);
        structure.getNiveau(i).addPresentation(presentation);
        return presentation;

    }

    public static void scenarioSetupMaterielsSemenceDansStructure(Structure genotype){
        ArrayList<Variable> variables = getMaterielsSemencesVariables();
        variables.forEach((n)->genotype.getNiveau(1).addVariable(n));
    }

    private static Structure creerStructureStockage(String name, int nbLevel) {
        // @doriane : ne faudrait-il pas dans tous les cas, créer une presentation par défaut?
        return factoryStructure.createStructure(name, nbLevel, "Stock");
    }

    private static ArrayList getMaterielsSemencesVariables(){
        ArrayList<Variable> variables = new ArrayList<>();
        variables.add(factoryColonnesUtilisateur.createVariable("Nom Materiel","String"));
        variables.add(factoryColonnesUtilisateur.createVariable("Semence type","String"));
        variables.add(factoryColonnesUtilisateur.createVariable("Parent mal","String"));
        variables.add(factoryColonnesUtilisateur.createVariable("Parent femelle","String"));
        return variables;

    }

    private static UserVariable createUserVariable(String nom, String type) {
        // Fabrique de Uservariable
        UserVariable va = new UserVariable(nom, type);
        //gestion de la BD
        createColonneVariable(nom,type);
        return va;
    }
    private static ColonneUtilisateur createColonneVariable(String nom, String type) {
        ColonneUtilisateur dColonneUser = (ColonneUtilisateur)factoryColonnesUtilisateur.createInstanceOfVariable(nom,type);
        return dColonneUser;
    }

    private static void addUserVariable(UserVariable variable){

    }
}
