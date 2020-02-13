import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GenerateFeatureModelWithScenario {

    private FileWriter fr;
    private File file;
    private BufferedWriter writer;

    public GenerateFeatureModelWithScenario() throws IOException {
        File file = new File("Manager/src/main/resources/myModel.xml");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        file = new File("Manager/src/main/resources/myModel.xml");
        fr =new FileWriter("Manager/src/main/resources/myModel.xml",true);
        writer = new BufferedWriter(fr);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<featureModel>\n" +
                "\t<properties>\n" +
                "\t\t<graphics key=\"showhiddenfeatures\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"legendautolayout\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"showshortnames\" value=\"false\"/>\n" +
                "\t\t<graphics key=\"layout\" value=\"horizontal\"/>\n" +
                "\t\t<graphics key=\"showcollapsedconstraints\" value=\"true\"/>\n" +
                "\t\t<graphics key=\"legendhidden\" value=\"false\"/>\n" +
                "\t\t<graphics key=\"layoutalgorithm\" value=\"1\"/>\n" +
                "\t</properties> \n"+
                "\t<struct> \n");

    }
}
