package org.example;

import org.example.fragments.Sample;
import org.example.fragments.Size;
import org.example.fragments.Strain;

import java.nio.file.Path;
import java.util.ArrayList;

public class Repository {
    private ArrayList<String>data;
    private ArrayList<Strain> strains;
    private ArrayList<Sample> samples;
    private ArrayList<Size> sizes;

    public Repository() {
        this.data = new ArrayList<>();
        this.strains = new ArrayList<>();
        this.samples = new ArrayList<>();
        this.sizes = new ArrayList<>();
    }

    public ArrayList<Strain> getStrains() {
        return strains;
    }

    public void setStrains(ArrayList<Strain> strains) {
        this.strains = strains;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }

    public void setSamples(ArrayList<Sample> samples) {
        this.samples = samples;
    }

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Size> sizes) {
        this.sizes = sizes;
    }
    public boolean addStrain(Strain strain){
        return strains.add(strain);
    }
    public boolean addSample(Sample sample){
        return samples.add(sample);
    }
    public boolean addSize(Size size){
        return sizes.add(size);
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
