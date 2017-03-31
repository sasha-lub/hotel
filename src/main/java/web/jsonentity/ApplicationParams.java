package web.jsonentity;

/**
 * Created by Sasha on 02.03.2017.
 */
public class ApplicationParams {
    private String userId;
    private String numberOfGuests;
    private String classOfRoom;
    private String fromDate;
    private String toDate;
    private String comment;

    public ApplicationParams() {
    }

    public String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    private void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getClassOfRoom() {
        return classOfRoom;
    }

    private void setClassOfRoom(String classOfRoom) {
        this.classOfRoom = classOfRoom;
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

    public String getComment() {
        return comment;
    }

    private void setComment(String comment) {
        this.comment = comment;
    }
}
