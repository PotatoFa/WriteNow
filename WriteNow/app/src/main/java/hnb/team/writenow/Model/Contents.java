package hnb.team.writenow.Model;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class Contents {

    private int contentsId;
    private int titleImage;

    public Contents(int contentsId, int titleImage) {
        this.contentsId = contentsId;
        this.titleImage = titleImage;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }

    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }
}
