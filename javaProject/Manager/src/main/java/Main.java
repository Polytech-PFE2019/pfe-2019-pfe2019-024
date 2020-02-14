import dsl.kernel.ScenarioApp;
import dsl.kernel.generator.Generator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static dsl.builder.ScenarioBuilder.scenario;

public class Main {



    public static void main(String[] args) throws IOException {
        Scenario scenario = null;
        try {
            GeneratePlantUml test = new GeneratePlantUml();
            GenerateXmlFeatureModel test2 = new GenerateXmlFeatureModel();
            scenario = Scenario.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dsl();
        new DiffFile();
        }



    protected static void dsl(){

        HashMap<String, Generator> arduinoAppGenerated = new HashMap<>();

        ScenarioApp scenarioApp =
                scenario("scenario1")
                        .CreerStructureSelection("cross",2)
                        .ConfigurationNiveau(1)
                            .DebutAjoutVariables()
                                .AjoutColonneUtilisateur("Name","Int")
                            .EndAddVariable()
                            .ConfigurationEtiquette()
                            .ConfigurationRapport()
                            .ConfigurationPresentation("General")
                            .FinConfigurationPresentation()
                            .ConfigurationPresentation("All")
                            .FinConfigurationPresentation()
                        .FinConfigurationNiveau()
                        .ConfigurationNiveau(2)
                            .ConfigurationPresentation("General")
                        .FinConfigurationPresentation()
                        .FinConfigurationNiveau()
                .build();

        Generator generator = new Generator();
        scenarioApp.accept(generator);
        arduinoAppGenerated.put(scenarioApp.getName(),generator);
        System.out.println(generator.getGeneratedCode());
        scenarioApp =
                scenario("scenario2")
                        .CreerStructureSelection("cross",3)
                        .ConfigurationNiveau(1)
                            .DebutAjoutVariables()
                                .AjoutColonneUtilisateur("Name","Int")
                                .AjoutColonneUtilisateur("Type","String")
                            .EndAddVariable()
                            .ConfigurationRapport()
                            .ConfigurationPresentation("General")
                            .FinConfigurationPresentation()
                            .ConfigurationPresentation("All")
                                .FinConfigurationPresentation()
                            .FinConfigurationNiveau()
                            .ConfigurationNiveau(2)
                                .DebutAjoutVariables()
                                    .AjoutConditionExperimental("irrigation","bool")
                                .EndAddVariable()
                                .ConfigurationPresentation("General")
                            .FinConfigurationPresentation()
                        .FinConfigurationNiveau()
                        .ConfigurationNiveau(3)
                            .DebutAjoutVariables()
                                .AjoutColonneUtilisateur("lotSemence","string")
                            .EndAddVariable()
                            .ConfigurationPresentation("General")
                            .FinConfigurationPresentation()
                        .FinConfigurationNiveau()
                        .build();

        generator = new Generator();
        scenarioApp.accept(generator);
        arduinoAppGenerated.put(scenarioApp.getName(),generator);
        scenarioApp =
                scenario("scenario3")
                        .CreerStructureStock("stock",2)
                        .ConfigurationNiveau(1)
                        .DebutAjoutVariables()
                        .AjoutColonneUtilisateur("tg","Int")
                        .AjoutColonneUtilisateur("mdr","String")
                        .EndAddVariable()

                        .ConfigurationPresentation("General")
                        .FinConfigurationPresentation()
                        .ConfigurationPresentation("All")
                        .FinConfigurationPresentation()
                        .FinConfigurationNiveau()
                        .ConfigurationNiveau(2)
                        .DebutAjoutVariables()
                        .EndAddVariable()
                        .ConfigurationPresentation("General")
                        .FinConfigurationPresentation()
                        .FinConfigurationNiveau()

                        .build();

        generator = new Generator();
        scenarioApp.accept(generator);
        arduinoAppGenerated.put(scenarioApp.getName(),generator);
        try {
            new File("out/").mkdir();

            for (Map.Entry<String, Generator> entry : arduinoAppGenerated.entrySet()) {
                PrintWriter writer = new PrintWriter("out/" + entry.getKey() + ".xml", "UTF-8");
                writer.print(entry.getValue().getGeneratedCode());
                writer.close();
            }

        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
