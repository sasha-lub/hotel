package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Sasha
 *
 */

@Entity
@Table(name = "applications")
@Access( AccessType.FIELD )
public class Application implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private int numberOfGuests;

	@Enumerated(value = EnumType.STRING)
	private ClassOfHotelRoom classOfRoom;

	@Column(nullable = false)
	private LocalDate checkInDate;

	@Column(nullable = false)
	private LocalDate checkOutDate;

	@Column
	private String comment;

	@OneToOne
	private ApplicationResponse response;

	@Column
	private boolean isNew;

	public Application() {
	}
	
	public Application(User user, int numberOfGuests, ClassOfHotelRoom classOfRoom, LocalDate checkInDate,
			LocalDate checkOutDate, String comment) {
		this.user = user;
		this.numberOfGuests = numberOfGuests;
		this.classOfRoom = classOfRoom;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.comment = comment;
		this.isNew = true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(int userId) {
		this.user = user;
	}
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public ClassOfHotelRoom getClassOfRoom() {
		return classOfRoom;
	}
	public void setClassOfRoom(ClassOfHotelRoom classOfRoom) {
		this.classOfRoom = classOfRoom;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public ApplicationResponse getResponse() {
		return response;
	}

	public void setResponse(ApplicationResponse response) {
		this.response = response;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", userId=" + user + ", numberOfGuests=" + numberOfGuests + ", classOfRoom="
				+ classOfRoom + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", comment="
				+ comment + ", response=" + response + ", isNew=" + isNew + "]";
	}
	
	
}
