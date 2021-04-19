package models;

import java.util.Arrays;
import java.util.Objects;

public class Pet {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String[] getPhotoUrls() {
        int length = photoUrls.length;
        return Arrays.copyOf(photoUrls, length);
    }

    private Category category;
    private Tag[] tags;

    public Tag[] getTags() {
        int length = tags.length;
        return Arrays.copyOf(tags, length);
    }

    public void setTags(Tag[] tags) {
        int length = tags.length;
        this.tags = Arrays.copyOf(tags, length);
    }

    private String status;
    private String[] photoUrls;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhotoUrls(String[] photoUrls) {
        int length = photoUrls.length;
        this.photoUrls = Arrays.copyOf(photoUrls, length);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", tags=" + Arrays.toString(tags) +
                ", status='" + status + '\'' +
                ", photoUrls=" + Arrays.toString(photoUrls) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId() && Objects.equals(getName(), pet.getName()) && Objects.equals(getCategory(), pet.getCategory()) && Arrays.equals(getTags(), pet.getTags()) && Objects.equals(getStatus(), pet.getStatus()) && Arrays.equals(getPhotoUrls(), pet.getPhotoUrls());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getCategory(), getStatus());
        result = 31 * result + Arrays.hashCode(getTags());
        result = 31 * result + Arrays.hashCode(getPhotoUrls());
        return result;
    }
}

