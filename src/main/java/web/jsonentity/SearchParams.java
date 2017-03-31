package web.jsonentity;

/**
 * Created by Sasha on 02.03.2017.
 */
public class SearchParams {

    private String classOfRoom;
    private String capacity;
    private String fromDate;
    private String toDate;
    private String maxPrice;
    private String sort;

    public SearchParams() {
    }

    public SearchParams(String classOfRoom, String capacity, String fromDate, String toDate, String maxPrice, String sort) {
        this.classOfRoom = classOfRoom;
        this.capacity = capacity;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxPrice = maxPrice;
        this.sort = sort;
    }

    public String getClassOfRoom() {
        return classOfRoom;
    }

    @Override
    public String toString() {
        return "SearchParams{" +
                "classOfRoom='" + classOfRoom + '\'' +
                ", capacity='" + capacity + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }

    private void setClassOfRoom(String classOfRoom) {
        this.classOfRoom = classOfRoom;
    }

    public String getCapacity() {
        return capacity;
    }

    private void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getFromDate() {
        return fromDate;
    }

    private void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    private void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    private void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
