package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Sasha
 *
 */

@Entity
@Table(name = "rooms")
@Access( AccessType.FIELD )
public class Room implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private int capacity;

	@Enumerated(EnumType.STRING)
	private ClassOfHotelRoom classOfRoom;

	@Column(nullable = false)
	private float price;

	@Column
	private String description;

	@Column
	private float avgRating;

	@Column
	private String mainPhoto;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "photos")
	private List<String> photos;
	
	public Room(){
	}
	
	public Room(int capacity, ClassOfHotelRoom classOfRoom, float price, String description, String mainPhoto) {
		this.capacity = capacity;
		this.classOfRoom = classOfRoom;
		this.price = price;
		this.description = description;
		this.mainPhoto = mainPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ClassOfHotelRoom getClassOfRoom() {
		return classOfRoom;
	}

	public void setClassOfRoom(ClassOfHotelRoom classOfRoom) {
		this.classOfRoom = classOfRoom;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", capacity=" + capacity + ", classOfRoom=" + classOfRoom + ", price=" + price
				+ ", description=" + description
				+ ", avgRating=" + avgRating + ", mainPhoto=" + mainPhoto + ", photos=" + photos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((classOfRoom == null) ? 0 : classOfRoom.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (capacity != other.capacity)
			return false;
		if (classOfRoom != other.classOfRoom)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
