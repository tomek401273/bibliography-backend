package com.tgrajkowski.model.authors;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublicationReturn {
    private Set<Publication> publications = new HashSet<>();

}
