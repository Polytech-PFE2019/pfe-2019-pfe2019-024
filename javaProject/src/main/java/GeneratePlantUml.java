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
        this.courseFiles();
        this.endFile();
    }

    private void findAllFiles(){
        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"\\src\\main\\java"))) {

            filePath = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList());
            filePath.remove(0);
            filePath.remove(0);
            String tmp = "";

            String packagePath = filePath.get(0).substring(0,filePath.get(0).lastIndexOf("\\"));
            String packageName = packagePath.substring(packagePath.lastIndexOf("\\")+1);
            writer.append("package \""+packageName+" { \n");
            for(int i = 0; i< filePath.size();i++){
                packagePath = filePath.get(i).substring(0,filePath.get(i).lastIndexOf("\\"));
                if(!packageName.equals(packagePath.substring(packagePath.lastIndexOf("\\")+1)))
                {
                    packageName =packagePath.substring(packagePath.lastIndexOf("\\")+1);
                    writer.append("} \n package \""+packageName+"\" { \n");
                }
                tmp = filePath.get(i).substring(filePath.get(i).lastIndexOf("\\") + 1);
                fileNameArray.add(tmp.substring(0,tmp.indexOf(".")));
                writer.append("Class " + fileNameArray.get(i)+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                        if(i != j) {
                            if (line.contains(" "+fileNameArray.get(j)+" ") && !matriceOccurence[i][j]) {

                                if(line.contains("extends")){
                                    writer.append(fileNameArray.get(i) + " <|-- " + fileNameArray.get(j) +" : extends  " + "\n");
                                }else {
                                    writer.append(fileNameArray.get(i)+ " --> " );
                                    if(line.contains(fileNameArray.get(j)+" []") || line.contains("ArrayList < " +fileNameArray.get(j)+ " >")){
                                        writer.append("\"0..*\"");
                                    }
                                    writer.append(fileNameArray.get(j) + "\n");
                                }
                                matriceOccurence[i][j] = true;
                            }
                            ;
                        }
                    }
                }
            }

        }
    }

    public void generateRelation(){

    }

    private void endFile() throws IOException {
        writer.append("@enduml");
        writer.close();
    }
}
