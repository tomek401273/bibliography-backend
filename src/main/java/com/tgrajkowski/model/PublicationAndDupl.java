package com.tgrajkowski.model;

import com.tgrajkowski.model.authors.Publication;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PublicationAndDupl {
    private Set<Publication> publicationSet = new TreeSet<>();
    private Set<Publication> duplicates= new TreeSet<>();

    public Set<Publication> getPublicationSet() {
        return publicationSet;
    }

    public void setPublicationSet(Set<Publication> publicationSet) {
        this.publicationSet = publicationSet;
    }

    public Set<Publication> getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(Set<Publication> duplicates) {
        this.duplicates = duplicates;
    }
}
