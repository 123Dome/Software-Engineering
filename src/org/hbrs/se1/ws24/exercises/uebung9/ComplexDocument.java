package org.hbrs.se1.ws24.exercises.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument extends CoreDocument{
    private int id;
    private List<CoreDocument> documents;

    public ComplexDocument() {
        this.documents = new ArrayList<>();
    }

    public void addDocument(CoreDocument document) {
        this.documents.add(document);
    }

    public void removeDocument(CoreDocument document) {
        this.documents.remove(document);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getSizeInBytes() {
        int totalSize = 0;
        for (CoreDocument doc : documents) {
            totalSize += doc.getSizeInBytes();
        }
        return totalSize;
    }
}
