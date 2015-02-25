package cs.dartmouth.edu.myrunsjf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final Logger mLogger = Logger
			.getLogger(QueryServlet.class.getName());
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		mLogger.log(Level.INFO, "DoPost");
		ArrayList<ExerciseEntry> result = ExerciseEntryDatastore.query();
		req.setAttribute("result", result);
		getServletContext().getRequestDispatcher("/query_result.jsp").forward(
				req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
