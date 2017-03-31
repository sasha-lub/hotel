
package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Answer from manager for client's application entity class.
 * 
 * @author Sasha
 *
 */

@Entity
@Table(name = "responses")
@Access( AccessType.FIELD )
public class ApplicationResponse implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private int roomId;

	@OneToOne
	@JoinColumn(name = "app_id")
	private Application application;

	@Column
	private String comment;
	
	public ApplicationResponse(){}
	
	public ApplicationResponse(Application application, int roomId, String comment) {
		this.roomId = roomId;
		this.application = application;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(int applicationId) {
		this.application = application;
	}
	
	@Override
	public String toString() {
		return "ApplicationResponse [id=" + id + ", roomId=" + roomId + ", applicationId="
				+ application + ", comment=" + comment + "]";
	}
	
	
}
