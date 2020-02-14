package dsl.builder;

import dsl.kernel.ScenarioApp;
import structure.Selection;
import structure.Stock;
import structure.Structure;

import javax.security.auth.login.Configuration;

public class ScenarioBuilder {
    private ScenarioApp scenarioApp;

    public ScenarioBuilder(){

    }

    /**
     * On crée une instance du builder, et on lui ajoute une instance de l'app (point d'entrée
     * du code scenario)
     * @param name
     * @return
     */
    public static ScenarioBuilder scenario(String name){
        ScenarioBuilder scenarioBuilder = new ScenarioBuilder();
        scenarioBuilder.scenarioApp = new ScenarioApp();
        scenarioBuilder.scenarioApp.setName(name);
        return scenarioBuilder;
    }

    public ScenarioBuilder CreerStructureSelection(String name,int nblvl){
        scenarioApp.setStructure(new Selection(name,nblvl));
        return this;
    }
    public ScenarioBuilder CreerStructureStock(String name,int nblvl){
        scenarioApp.setStructure(new Stock(name,nblvl));
        return this;
    }

    public NiveauBuilder ConfigurationNiveau(int nblvl){

        return new NiveauBuilder(this , nblvl);
    }



    public ScenarioBuilder CreerPresentation(String name){
        return this;
    }

    public Structure getStructure(){
        return scenarioApp.getStructure();
    }
    public ScenarioApp build() {
        return scenarioApp;
    }


}
