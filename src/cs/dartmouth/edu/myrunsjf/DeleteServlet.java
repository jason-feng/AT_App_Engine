package cs.dartmouth.edu.myrunsjf;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	private static final Logger mLogger = Logger
			.getLogger(DeleteServlet.class.getName());
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		mLogger.log(Level.INFO, "DoPost");
		long id = Long.parseLong(req.getParameter("id"));
		ExerciseEntryDatastore.delete(id);
		Global.deleteID = id;
		resp.sendRedirect("/sendmsg.do");
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
