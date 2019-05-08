package com.tgrajkowski.model;

import com.tgrajkowski.model.authors.BibliographyReturn;
import com.tgrajkowski.model.authors.PublicationReturn;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReturnMainObject {
    private BibliographyReturn bibliographyReturn;
    private PublicationReturn publicationReturn;
    private long calculationTime;
}
