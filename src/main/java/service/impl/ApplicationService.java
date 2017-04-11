package service.impl;

import dao.IApplicationDao;
import dao.IResponseDao;
import exception.DaoException;
import exception.ServiceException;
import model.Application;
import model.ApplicationResponse;
import model.ClassOfHotelRoom;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IApplicationService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Business logic methods for Application and Application Response entities
 * 
 * @author Sasha
 *
 */
@Service
public class ApplicationService implements IApplicationService {

	@Inject
	private IApplicationDao applicationDao;
	@Inject
	private IResponseDao responseDao;

	public ApplicationService() {
		System.out.println(0x81 );
		float i = 0x81;
	}

	@Transactional
	@Override
	public Application newApplication(User user, int numOfGuests, LocalDate from, LocalDate to,
							   ClassOfHotelRoom clasOfRoom, String comment) throws ServiceException {

		Application app = new Application(user, numOfGuests, clasOfRoom, from, to, comment);
		try {
			return applicationDao.add(app);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public Application getAppById(int appId) throws ServiceException {
		try {
			return applicationDao.getById(appId, Application.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void updateApplication(Application application) throws ServiceException {
		//todo
	}

	@Transactional
	@Override
	public List<Application> getAllApplications() throws ServiceException {
		try {
			return applicationDao.getAll(Application.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Application> getAllApplicationsByUser(int userId) throws ServiceException {
		try {
			return applicationDao.getAllByUser(userId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void deleteApplication(int appId) throws ServiceException {
		try {
			applicationDao.remove(applicationDao.getById(appId, Application.class));
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public ApplicationResponse newAppResponse(Application app, int roomId, String comment) throws ServiceException {

		ApplicationResponse response = new ApplicationResponse(app, roomId, comment);
		try {
			return responseDao.add(response);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public ApplicationResponse getResponseById(int responseId) throws ServiceException {
		try {
			return responseDao.getById(responseId, ApplicationResponse.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public ApplicationResponse getResponseForApplication(int appId) throws ServiceException {
		try {
			return responseDao.getResponseByApplication(appId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<ApplicationResponse> getAllAppResponses() throws ServiceException {
		try {
			return responseDao.getAll(ApplicationResponse.class);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void deleteResponse(int responseId) throws ServiceException {
		try {
			responseDao.remove(responseDao.getById(responseId, ApplicationResponse.class));
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public int getCountOfNewApps() throws ServiceException {
		try {
			return applicationDao.getCountOfNewApps();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public List<Application> getAllNewApplications() throws ServiceException {
		try {
			return applicationDao.getAllNew();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
