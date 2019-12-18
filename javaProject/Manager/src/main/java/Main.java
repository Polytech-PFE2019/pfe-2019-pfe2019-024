import businessLevel.UserVariable;
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
        //Ajout d'une variable dans un niveau d'une structure


        Structure contact = Scenario.scenarioCreerStructureStockage("contact",2);
        Presentation general = Scenario.scenarioCreerPresentationEtAjouter(contact,1,"general");
        UserVariable variable = Scenario.scenarioCreerColonneUtilisateur("date","DATE");
        Scenario.scenarioAjoutVariableAPresentation(general,variable);
        System.out.println("Niveau 1 contact : "+contact.getNiveau(1).toString());

        //Scenario de creation du materiel pour les semences à partir d'un fichier excel dans READY
        //@param = fichier excel + niveau et il vaut mieux le mettre dans le niveau le plus bas.
        //implementation:  se placer dans la structure GENOTYPE dans le niveau demandé et on importe si le fichier est conforme à la structure attendue
        //Variation : au lieu de semence on est sur des produits
        // dans ce cas se placer dans une autre structure mais tout pareil..

        //--- Ajout des variables indispenable au matériel de type semence
        Structure genotype = Scenario.scenarioCreerStructureStockage("genotype",3);
        Presentation all = Scenario.scenarioCreerPresentationEtAjouter(genotype,1,"all");
        Scenario.scenarioSetupMaterielsSemenceDansStructure(genotype);
        System.out.println("Niveau 1 genotype : "+genotype.getNiveau(1).toString());

        //--- Création d'une experience
        Structure cross = Scenario.scenarioCreerStructureSelection("cross",2,genotype.getNiveau(1));
        Scenario.scenarioConfigurationNiveau(cross);
        Scenario.scenarioConfigureNiveauCovariable(cross);
        Scenario.scenarioConfigureProfilDeCroisement(cross);
        Scenario.scenarioConfigureObservationMultiple(cross);
        //Sceanrio par extrapolation : creer une structure important du materiel de semanece par défaut
        // il faudrait creer toutes les colonnes propres à l'application... et suggérer celles qui se trouvent dans READY

        ///- scenraio de creation d'une structure relative à des experimentations de croisement
        // @param : nom de la structure resultante, nombre de Niveaux, reference vers le materiel
    }


}
