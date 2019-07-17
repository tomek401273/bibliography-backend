package com.tgrajkowski.model.authors;

import com.tgrajkowski.model.PublicationAndDupl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PublicationCreator {

    public PublicationAndDupl createPublications(List<String> lines) {
        PublicationAndDupl publicationAndDupl = new PublicationAndDupl();
        Set<Publication> publications = new TreeSet<>();
        for (String line : lines) {
            String name = "";
          try{
              int indexDot = line.indexOf(",");
               name = line.substring(0, indexDot);

          } catch (StringIndexOutOfBoundsException e){
              System.out.println(e);
              System.out.println(line);
          }

            Publication publication = new Publication(name);

            int pubStart = line.indexOf("(");
            int pubStop = line.indexOf(")");
            String publishYear = "";
            try {
                publishYear = line.substring(pubStart+1, pubStop);
            }catch (StringIndexOutOfBoundsException e) {
                System.out.println(e);
                System.out.println(line);
            }

            try{
                Integer year = Integer.parseInt(publishYear);
                publication.setPublicationYear(year);

            } catch (NumberFormatException e) {
            }
            if (publications.contains(publication)) {
                publicationAndDupl.getDuplicates().add(publication);
//                System.out.println("Duplicate: "+publication);
            }
            publications.add(publication);
        }
        publicationAndDupl.setPublicationSet(publications);

        return publicationAndDupl;
    }
}
