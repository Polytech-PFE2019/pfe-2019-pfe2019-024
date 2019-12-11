import java.io.*;
import java.util.Scanner;

public class GenerateActivityUML {

    private FileWriter fr;
    private BufferedWriter writer;
    private BufferedReader readerMain;

    public GenerateActivityUML() throws IOException {
        File file = new File("Manager/src/main/resources/myActivityUml.txt");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        file = new File("Manager/src/main/resources/myActivityUml.txt");
        fr =new FileWriter("Manager/src/main/resources/myActivityUml.txt",true);
        writer = new BufferedWriter(fr);
        readerMain = new BufferedReader(new FileReader("Manager/src/main/java/Main.java"));
        writer.write("@startuml \n");
        writer.write("(*) ");
        this.generateDiagramme();
        this.endFile();

    }

    public void generateDiagramme() throws IOException {
        Scanner sc = new Scanner(readerMain);
        BufferedReader readerScenario = new BufferedReader(new FileReader("Manager/src/main/java/Scenario.java"));
        String line;
        String tmp;
        while(sc.hasNextLine()){
            System.out.println("test");
            line = sc.nextLine();
            if(line.contains("Activity")){
                line = sc.nextLine();
                while(!line.contains("End Activity")) {
                    if(line.contains("Scenario")){
                        tmp = line.substring(line.indexOf("."),line.indexOf("("));
                        if(line.contains("Structure")){
                            generateStructureActivity();
                        }
                    }
                    line = sc.nextLine();
                }
            }

        }
        readerScenario.close();
    }

    private void generateStructureActivity() throws IOException {
        generateActivityLink("","generateStructure");
        generateActivityIf("chooseStructure");
        generateActivityLink("","Experience");
        generateActivityLink("","chooseExperienceType");
        generateActivityElse("Stock");
    }

    public void generateActivity(String activity) throws IOException {
        writer.write(" \""+activity+"\"");
    }

    public void generateActivityLink(String label,String activity) throws IOException {
        writer.write("-->");
        if(!label.equals("")) {
            writer.write("[" + label + "] ");
        }
        writer.write(" \"" + activity + "\" as " +activity +" \n");
    }

    public void generateActivityIf(String label) throws IOException {
        writer.write("if " + " \""+label+"\" " + "then \n");
    }

    public void generateActivityElse(String activity) throws IOException {
        writer.write("else \n" + "--> \""+activity+"\" " + "\n endif \n");
    }

    public void generateActivitySynchronisation(){

    }

    private void endFile() throws IOException {
        writer.append("@enduml");

        readerMain.close();
        writer.close();
    }
}
