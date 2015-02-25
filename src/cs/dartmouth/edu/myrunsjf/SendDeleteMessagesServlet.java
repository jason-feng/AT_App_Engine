/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cs.dartmouth.edu.myrunsjf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet that adds a new message to all registered devices.
 * <p>
 * This servlet is used just by the browser (i.e., not device).
 */
@SuppressWarnings("serial")
public class SendDeleteMessagesServlet extends HttpServlet {

	private static final int MAX_RETRY = 5;
	private static final Logger mLogger = Logger
			.getLogger(SendDeleteMessagesServlet.class.getName());
	/**
	 * Processes the request to add a new message.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		mLogger.log(Level.INFO, "DoPost, Delete: " + Long.toString(Global.deleteID));
		List<String> devices = RegDatastore.getDevices();
		Message message = new Message(devices);
		message.addData("message", "update");
		message.addData("deleteID", Long.toString(Global.deleteID));
		
		// Have to hard-coding the API key when creating the Sender
		Sender sender = new Sender(Global.GCMAPIKEY);
		// Send the message to device, at most retrying MAX_RETRY times
		sender.send(message, MAX_RETRY);

		resp.sendRedirect("/query.do");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}
}
