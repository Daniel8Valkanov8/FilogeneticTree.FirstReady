package org.example;

import org.example.fragments.Sample;
import org.example.fragments.Size;
import org.example.view.tree;

import java.util.ArrayList;
import java.util.Map;

public class MainRunners {
    public static void run1() {

        Repository repository = new Repository();
        ArrayList<String> data = FileReaderExample.readLines("C:\\Users\\danie\\FilogeneticTree\\src\\main\\java\\org\\example\\Trichoderma Txt.txt");
        String[] samplesData = data.get(1).split("\t");
        repository.setData(data);


        //samle creator
        sampleCreator(samplesData, repository);

        //datas of samples only
        ArrayList<String[]> dataOfReference = sizeCreator(repository);


        //connect samples and sizes
        connectionCreator(dataOfReference, repository);


        printResults1(repository);

    }

    private static void printResults1(Repository repository) {
        Map<Sample, Integer> similarityMap = Reference.compareAllSamples(repository.getSamples());
        printSimilarityResults(similarityMap);
        System.out.println(repository.getSamples().size());
        tree.DataVisualizationFrame.showVisualization(similarityMap);
    }

    private static void connectionCreator(ArrayList<String[]> dataOfReference, Repository repository) {
        for (int indexOfLine = 0; indexOfLine < dataOfReference.size(); indexOfLine++) {


            int indexOfSampleSet = 0;
            String[] sampleData = dataOfReference.get(indexOfLine);


            for (int indexOfSample = 1; indexOfSample < sampleData.length; indexOfSample++) {
                //System.out.print(sampleData[indexOfSample] + "\t");
                if (sampleData[indexOfSample].equals("1")) {
                    repository.getSamples().get(indexOfSample - 1)
                            .addSize(repository.getSizes().get(indexOfLine));
                }
            }
            //System.out.println();
        }
    }

    private static ArrayList<String[]> sizeCreator(Repository repository) {
        ArrayList<String> samplesDataOnly = repository.getData();
        samplesDataOnly.remove(0);
        samplesDataOnly.remove(0);


        //create sizes of file and add them to repo
        for (String s : samplesDataOnly) {
            String[] sampleData = s.split("\t");
            double value = Double.parseDouble(sampleData[0]);
            Size size = new Size(value);
            repository.addSize(size);
        }
        ArrayList<String[]> dataOfReference = new ArrayList<>();
        for (String lineData : samplesDataOnly) {
            String[] sampleData = lineData.split("\t");
            dataOfReference.add(sampleData);
        }
        return dataOfReference;
    }

    private static void sampleCreator(String[] samplesData, Repository repository) {
        for (int i = 1; i < samplesData.length; i++) {
            Sample sample = new Sample(samplesData[i]);
            repository.addSample(sample);
        }
    }

    private static void printSimilarityResults(Map<Sample, Integer> similarityMap) {
        for (Map.Entry<Sample, Integer> entry : similarityMap.entrySet()) {
            Sample sample = entry.getKey();
            Integer similarityScore = entry.getValue();
            System.out.println("Проба: " + sample.getName() + ", Сходство: " + similarityScore);
        }
    }

}
