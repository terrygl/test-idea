package joe.spring.springapp.services;

/**
 * A wrapper around a <code>Exception</code> used for services.
 * 
 * @author jsicree
 *
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public ServiceException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}



	

}
