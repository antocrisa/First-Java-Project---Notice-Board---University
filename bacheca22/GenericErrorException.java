package bacheca22;

import java.io.Serial;

public class GenericErrorException extends Exception {
	
	@Serial
	private static final long serialVersionUID = 1L;

	public GenericErrorException (String msg)
	{
		super(msg);
	}
}

