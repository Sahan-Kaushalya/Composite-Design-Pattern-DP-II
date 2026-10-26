package lk.kaushalya.model;

import java.util.ArrayList;

interface Component {
    public void display();

    public double getSize();
}

class File implements Component {

    private String name;
    private double size;

    public File(String name, double size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void display() {
        System.out.println("File: " + this.name + ", Size: " + this.size+" MB");
    }

    @Override
    public double getSize() {
        return this.size;
    }
}

class Folder implements Component {

    private String name;
    private ArrayList<Component> componentsArrayList = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(Component component) {
        this.componentsArrayList.add(component);
    }

    public void remove(Component component) {
        this.componentsArrayList.remove(component);
    }

    @Override
    public void display() {
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Folder: " + this.name);
        System.out.println("Folder Size: " + this.getSize()+" MB");
        System.out.println("------------------------------------");
        System.out.println("Folder contains: ");
        for (Component component : componentsArrayList) {
            component.display();
        }
        System.out.println("------------------------------------");
    }

    @Override
    public double getSize() {
        double totalSize = 0;

        for (Component component : componentsArrayList) {
            totalSize += component.getSize();
        }

        return totalSize;
    }
}

public class Test {
    public static void main(String[] args) {

        Folder desktop = new Folder("Desktop");

        Folder documents = new Folder("Documents");
        desktop.add(documents);

        File game = new File("Game.exe", 2048);
        desktop.add(game);

        Folder Images = new Folder("Images");
        desktop.add(Images);

        File photo = new File("photo.jpg", 5.4);
        Images.add(photo);

        File notes = new File("Notes.txt", 5.2);
        documents.add(notes);

        File report = new File("Report.pdf", 25.6);
        documents.add(report);

        desktop.display();

        System.out.println("Total Size of Desktop: " + desktop.getSize() + " MB");
        System.out.println();

        Images.display();

        System.out.println("Total Size of Images: " + Images.getSize() + " MB");
        System.out.println();

        documents.display();
        System.out.println("Total Size of Documents: " + documents.getSize() + " MB");
        System.out.println();

        report.display();
        System.out.println("Total Size of Report: " + report.getSize() + " MB");
    }
}