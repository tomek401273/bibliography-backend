package com.tgrajkowski.model.authors;

public class Publication implements Comparable<Publication> {
    private String authorName;
    private int publicationYear;

    public Publication(String authorName) {
        this.authorName = authorName;
    }

    public Publication(String authorName, int publicationYear) {
        this.authorName = authorName;
        this.publicationYear = publicationYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;

        Publication that = (Publication) o;

        if (publicationYear != that.publicationYear) return false;
        return authorName != null ? authorName.equals(that.authorName) : that.authorName == null;
    }

    @Override
    public int hashCode() {
        int result = authorName != null ? authorName.hashCode() : 0;
        result = 31 * result + publicationYear;
        return result;
    }




    @Override
    public String toString() {
        return "Publication{" +
                "authorName='" + authorName + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }

    @Override
    public int compareTo(Publication other) {
        String thisObject = this.authorName+this.publicationYear;
        String otherObject = other.authorName+other.publicationYear;
        return thisObject.compareTo(otherObject);
    }
}
