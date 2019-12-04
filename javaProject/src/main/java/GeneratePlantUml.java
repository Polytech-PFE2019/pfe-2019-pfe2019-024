import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratePlantUml {

    private ArrayList<String> fileNameArray = new ArrayList<>();
    private FileWriter fr;
    private File file;
    private BufferedWriter writer;
    private List<String> filePath;

    public GeneratePlantUml() throws IOException {
        File file = new File("src/main/resources/myUml.txt");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        file = new File("src/main/resources/myUml.txt");
        fr =new FileWriter("src/main/resources/myUml.txt",true);
        writer = new BufferedWriter(fr);
        writer.write("@startuml \n");
        this.findAllFiles();
        this.generateClassAndPackage();
        this.courseFiles();
        this.endFile();
    }

    private void findAllFiles(){
        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"\\src\\main\\java"))) {
            filePath = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateClassAndPackage() throws IOException {
        String tmp = "";
        String packagePath = "";
        String packageName = "";

        for(int i = 0 ; i < filePath.size(); i++) {
            packagePath = filePath.get(i).substring(0,filePath.get(i).lastIndexOf("\\"));
            if(packagePath.substring(packagePath.lastIndexOf("\\")+1).equals("java") ||
                    packagePath.substring(packagePath.lastIndexOf("\\")+1).equals("businessLevel")){
                filePath.remove(i);
                i--;
            }
        }
        packagePath = filePath.get(0).substring(0,filePath.get(0).lastIndexOf("\\"));
        packageName =packagePath.substring(packagePath.lastIndexOf("\\")+1);
        writer.append("\n package \""+packageName+"\" #DDDDDD{ \n");
        for(int i = 0; i< filePath.size();i++){

            packagePath = filePath.get(i).substring(0,filePath.get(i).lastIndexOf("\\"));

            if(!packageName.equals(packagePath.substring(packagePath.lastIndexOf("\\")+1)))
            {
                packageName =packagePath.substring(packagePath.lastIndexOf("\\")+1);
                writer.append("}\n package \""+packageName+"\" #DDDDDD { \n");
            }

            tmp = filePath.get(i).substring(filePath.get(i).lastIndexOf("\\") + 1);
            fileNameArray.add(tmp.substring(0,tmp.indexOf(".")));
            writer.append("Class " + fileNameArray.get(i)+"\n");
        }
        writer.append("}\n");
    }

    public void courseFiles() throws IOException {
        BufferedReader tmpReader;
        Boolean[][] matriceOccurence = new Boolean[fileNameArray.size()][fileNameArray.size()];

        for (int i = 0; i < fileNameArray.size(); i++) {
            Arrays.fill(matriceOccurence[i],false);
        }
        for (int i = 0; i < filePath.size(); i++) {
            tmpReader = new BufferedReader(new FileReader(filePath.get(i)));
            Scanner sc = new Scanner(tmpReader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(!line.contains("import")){
                    for (int j = 0; j < fileNameArray.size(); j++) {
                        if((i != j) && (!matriceOccurence[i][j]) && line.contains(" "+fileNameArray.get(j)+" ") ) {
                            this.generateRelation(line,fileNameArray.get(i),fileNameArray.get(j));
                            matriceOccurence[i][j] = true;
                        }
                    }
                }
            }

        }
    }

    public void generateRelation(String line ,String fileName,String reference) throws IOException {
        if(line.contains("extends")){
            writer.append(fileName + " <|-- " + reference +" : extends  " + "\n");
        }else {
            writer.append(fileName+ " --> " );
            if(line.contains(reference+" []") || line.contains("ArrayList < " +reference+ " >")){
                writer.append("\"0..*\"");
            }
            writer.append(reference + "\n");
        }
    }

    private void endFile() throws IOException {
        writer.append("@enduml");
        writer.close();
    }
}
