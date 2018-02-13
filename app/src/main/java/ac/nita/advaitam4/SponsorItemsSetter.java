package ac.nita.advaitam4;

/**
 * Created by HRITIK on 2/12/2018.
 */

public class SponsorItemsSetter {



    SponsorItemsSetter(){

    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
    SponsorItemsSetter(String Name, String ImageUri){
        this.name = Name;
        this.image = ImageUri;
    }



}
