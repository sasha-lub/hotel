package model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author Sasha
 *
 */


@Entity
@Table(name = "recalls")
@Access( AccessType.FIELD )
public class Recall implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	private Room room;

	@Column(nullable = false)
	@Min(1)
	@Max(5)
	private int rate;

	@Column
	private String comment;
	
	public Recall(){
	}
	
	public Recall(User user, int rate, String comment) {
		this.user = user;
		this.rate = rate;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String recall) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Recall [id=" + id + ", userId=" + user + ", rate=" + rate + ", comment=" + comment + "]";
	}
}
