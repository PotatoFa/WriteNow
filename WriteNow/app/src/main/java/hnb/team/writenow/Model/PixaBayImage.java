package hnb.team.writenow.Model;

/**
 * Created by jaehoonjung on 2017. 2. 11..
 */

public class PixaBayImage {

    private int id;
    private String pageURL;
    private String type;
    private String tags;
    private String previewURL;
    private int previewWidth;
    private int previewHeight;
    private String webformatURL;
    private int webformatWidth;
    private int webformatHeight;
    private int imageWidth;
    private int imageHeight;
    private int views;
    private int downloads;
    private int favorites;
    private int likes;
    private int comments;
    private int user_id;
    private String user;
    private String userImageURL;

    private int testImageUrl;

    public PixaBayImage(){

    }

    public PixaBayImage(int id, int testImageUrl){
        setId(id);
        setTestImageUrl(testImageUrl);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestImageUrl() {
        return testImageUrl;
    }

    public void setTestImageUrl(int testImageUrl) {
        this.testImageUrl = testImageUrl;
    }
}