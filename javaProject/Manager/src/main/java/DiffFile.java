import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiffFile {

    private ArrayList<BufferedReader> tmpReaders;
    private List<String> filePath;
    private List<List<String>> fileToList;
    private List<String> outFile;

    public DiffFile() throws IOException {
        outFile = new ArrayList<>();
        fileToList = new ArrayList<>();
        tmpReaders = new ArrayList<>();
        File file = new File("Manager/src/main/resources/featureDiagram.xml");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Manager/src/main/resources/featureDiagram.xml",true));
        findAllFiles();
        System.out.println(filePath);
        convertFileToList();
        System.out.println(fileToList);
        mergeModel();
        for(int i = 0; i < outFile.size(); i++){
            writer.append(outFile.get(i)+"\n");
        }
        writer.close();
    }

    private void findAllFiles(){
        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"\\out"))) {
            filePath = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xml")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertFileToList() throws IOException {
        int indexFile = 0;
        String currentLine;
        BufferedReader br = null;
        for(String file : filePath){
            fileToList.add(new ArrayList<>());
            br = new BufferedReader(new FileReader(file));
            while((currentLine = br.readLine()) != null){
                fileToList.get(indexFile).add(currentLine);
            }
            indexFile++;
        }
    }
    public String removeMandatory(String feature){
        return feature.replace("mandatory=\"true\"","");
    }
    public String getFeteareName(String feature){
        if(feature.length() > 20){
            return feature.substring(feature.lastIndexOf("=")+2,feature.lastIndexOf("\""));
        }
        return "";

    }
    public void mergeModel(){
        int indexFeatureOut = 0;
        int indexFeatureCurrent = 0;
        int largerFileLength = 0;
        List<String> tmpOutFile = new ArrayList<>() ;
        String featureNameOut = "";
        String featureNameCurrent = "";
        for(int i = 0; i < fileToList.size(); i++){
            if(i ==0){
                outFile.addAll(fileToList.get(i));
            }else{
                if(tmpOutFile.size()>0){
                    outFile.clear();
                    outFile.addAll(tmpOutFile);
                    tmpOutFile.clear();
                }
                indexFeatureOut = getFirstIndexOfFeature(outFile);
                indexFeatureCurrent = getFirstIndexOfFeature(fileToList.get(i));
                largerFileLength = Math.max(outFile.size(), fileToList.get(i).size()) ;
                for(int j = 0 ; j <  indexFeatureOut; j++) {
                    tmpOutFile.add(outFile.get(j));
                }
                for(int j = indexFeatureOut ; j < largerFileLength; j++){
                    if(indexFeatureOut >= largerFileLength || indexFeatureCurrent >= largerFileLength){
                        break;
                    }
                    if(indexFeatureCurrent >= fileToList.get(i).size()){
                        tmpOutFile.add(removeMandatory(outFile.get(indexFeatureOut)));
                        indexFeatureOut++;
                    }
                    else if(indexFeatureOut >= outFile.size()){
                        tmpOutFile.add(removeMandatory(fileToList.get(i).get(indexFeatureCurrent)));
                        indexFeatureCurrent++;
                    }else {
                        featureNameOut = getFeteareName(outFile.get(indexFeatureOut));
                        featureNameCurrent = getFeteareName(fileToList.get(i).get(indexFeatureCurrent));
                        //si le nombre de tabulation est égale
                        if (numberOfTabulation(outFile.get(indexFeatureOut)) == numberOfTabulation(fileToList.get(i).get(indexFeatureCurrent))) {
                            //si les feature n'ont pas le même nom
                            if(!featureNameOut.equals(featureNameCurrent)){
                                tmpOutFile.add(removeMandatory(outFile.get(indexFeatureOut)));
                                tmpOutFile.add(removeMandatory(fileToList.get(i).get(indexFeatureCurrent)));
                                indexFeatureOut++;
                                indexFeatureCurrent++;
                            }else {
                                tmpOutFile.add(outFile.get(indexFeatureOut));
                                indexFeatureOut++;
                                indexFeatureCurrent++;
                            }

                        } else {
                            if (outFile.get(indexFeatureOut).length() < fileToList.get(i).get(indexFeatureCurrent).length()) {
                                //tant que le nombre de tabulation de notre fichier de sortie est inferieur alors
                                //on ajoute toutes les feature de notre fichier courant dans le fichier de sortie
                                while (numberOfTabulation(outFile.get(indexFeatureOut)) < numberOfTabulation(fileToList.get(i).get(indexFeatureCurrent))) {
                                    tmpOutFile.add(removeMandatory(fileToList.get(i).get(indexFeatureCurrent)));
                                    indexFeatureCurrent++;
                                }
                            } else {
                                while (numberOfTabulation(outFile.get(indexFeatureOut)) > numberOfTabulation(fileToList.get(i).get(indexFeatureCurrent))) {
                                    tmpOutFile.add(removeMandatory(outFile.get(indexFeatureOut)));
                                    indexFeatureOut++;
                                }
                            }
                        }
                    }
                   /* if(!outFile.get(j).equals(fileToList.get(i).get(j))){

                    }*/
                }
            }
        }
        if(tmpOutFile.size()>0){
            outFile.clear();
            outFile.addAll(tmpOutFile);
            tmpOutFile.clear();
        }
    }
    private int numberOfTabulation(String feature){
        int counter = 0;
        for(char c : feature.toCharArray()) {
            if("\t".equals(""+c)) {
                counter = counter +1;
            }
            else {
                return counter;
            }
        }
        return counter;
    }
    private int getFirstIndexOfFeature(List<String> outFile) {

        return 0;
    }
}
