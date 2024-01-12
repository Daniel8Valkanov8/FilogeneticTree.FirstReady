package org.example;

import org.example.fragments.Sample;
import org.example.fragments.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Reference {

    // Метод за стартиране на рекурсивното сравнение
    public static Map<Sample, Integer> compareAllSamples(ArrayList<Sample> samples) {
        Map<Sample, Integer> similarityMap = new HashMap<>();
        compareSamplesRecursive(samples, 0, similarityMap);

        // Сортиране на картата по стойност в низходящ ред
        return similarityMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Sample, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new)); // LinkedHashMap preserves the order of elements
    }

    // Рекурсивен метод за сравнение на пробите
    private static void compareSamplesRecursive(ArrayList<Sample> samples, int index, Map<Sample, Integer> similarityMap) {
        if (index >= samples.size()) {
            return;
        }

        Sample currentSample = samples.get(index);
        int similarityScore = 0;

        for (int i = 0; i < samples.size(); i++) {
            if (i != index) {
                similarityScore += compareTwoSamples(currentSample, samples.get(i));
            }
        }

        similarityMap.put(currentSample, similarityScore);

        compareSamplesRecursive(samples, index + 1, similarityMap);
    }

    // Сравняване на две проби
    private static int compareTwoSamples(Sample s1, Sample s2) {
        int similarity = 0;
        for (Size size1 : s1.getSizes()) {
            for (Size size2 : s2.getSizes()) {
                if (size1.getValue() == size2.getValue()) {
                    similarity++;
                }
            }
        }
        return similarity;
    }
}

