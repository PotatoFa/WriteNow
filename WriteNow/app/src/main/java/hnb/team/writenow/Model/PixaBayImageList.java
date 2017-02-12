package hnb.team.writenow.Model;

import java.util.List;

/**
 * Created by jaehoonjung on 2017. 2. 11..
 */

public class PixaBayImageList {
    private int total;
    private int totalHits;
    private List<PixaBayImage> hits;

    public int getTotal() {
        return total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public List<PixaBayImage> getHits() {
        return hits;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public void setHits(List<PixaBayImage> hits) {
        this.hits = hits;
    }
}
