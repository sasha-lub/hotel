package model;

import javax.persistence.*;

/**
 * Created by Sasha on 17.04.2017.
 */
@Entity
@Table(name = "photos")
@Access( AccessType.FIELD )
public class Photo {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

    public Photo(){}

    public Photo(Room room, String url){
        this.room = room;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
