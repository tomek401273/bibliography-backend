package com.tgrajkowski.model.authors;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BibliographyReturn {
    private Set<Publication> duplicates = new HashSet<>();
    private int countOfPublication;
    private Set<Publication> publicationsNotUsed= new HashSet<>();
}
