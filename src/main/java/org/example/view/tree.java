package org.example.view;

import org.example.fragments.Sample;

import javax.swing.*;
import java.util.Map;


    public class DataVisualizationFrame extends JFrame {

        public DataVisualizationFrame(Map<Sample, Integer> similarityMap) {
            setTitle("Филогенетично Дърво Визуализация");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JScrollPane scrollPane = new JScrollPane(panel);
            add(scrollPane);

            for (Map.Entry<Sample, Integer> entry : similarityMap.entrySet()) {
                String text = "Проба: " + entry.getKey().getName() + ", Сходство: " + entry.getValue();
                JLabel label = new JLabel(text);
                panel.add(label);
            }
        }

        public static void showVisualization(Map<Sample, Integer> similarityMap) {
            SwingUtilities.invokeLater(() -> {
                DataVisualizationFrame frame = new DataVisualizationFrame(similarityMap);
                frame.setVisible(true);
            });
        }
    }

