package service;

import exception.ServiceException;
import model.Application;
import model.ApplicationResponse;
import model.ClassOfHotelRoom;
import model.User;

import java.time.LocalDate;
import java.util.List;

public interface IApplicationService {
	
	Application newApplication(User user, int numOfGuests, LocalDate from, LocalDate to, ClassOfHotelRoom claasOfRoom, String comment) throws ServiceException;
	
	Application getAppById(int appId) throws ServiceException;
	
	List<Application> getAllApplications() throws ServiceException;
	
	List<Application> getAllApplicationsByUser(int userId) throws ServiceException;
	
	int getCountOfNewApps() throws ServiceException;

	List<Application> getAllNewApplications() throws ServiceException;
	
	void deleteApplication(int appId) throws ServiceException;
	
	ApplicationResponse newAppResponse(Application app, int idUser, String comment) throws ServiceException;
	
	ApplicationResponse getResponseById(int responseId) throws ServiceException;
	
	ApplicationResponse getResponseForApplication(int appId) throws ServiceException;
	
	List<ApplicationResponse> getAllAppResponses() throws ServiceException;
	
	void deleteResponse(int responseId) throws ServiceException;

    void updateApplicationIsNewStatus(int appId, boolean b) throws ServiceException;
}
