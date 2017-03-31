package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Sasha
 *
 */


@Entity
@Table(name = "reservations")
@Access( AccessType.FIELD )
public class Reservation implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	private Room room;

	@Column(nullable = false)
	private LocalDate checkInDate;

	@Column(nullable = false)
	private LocalDate checkOutDate;

	@Column(nullable = false)
	private LocalDateTime paymentDeadline;

	@Enumerated(value = EnumType.STRING)
	private ReservationStatus status;

	@Column(nullable = false)
	private float price;
	
	public Reservation(){};
	
	public Reservation(Room room, User user, LocalDate checkInDate, LocalDate checkOutDate,
			LocalDateTime paymentDeadline, float price) {
		this.room = room;
		this.user = user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.paymentDeadline = paymentDeadline;
		this.price = price;
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
	public Room getRoom() {
		return room;
	}
	public void setRoom(int roomId) {
		this.room = room;
	}
	public ReservationStatus getStatus() {
		return status;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
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
	public LocalDateTime getPaymentDeadline() {
		return paymentDeadline;
	}
	public void setPaymentDeadline(LocalDateTime paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", userId=" + user + ", roomId=" + room + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", paymentDeadline=" + paymentDeadline + ", status=" + status
				+ ", price=" + price + "]";
	}
}
