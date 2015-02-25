<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="cs.dartmouth.edu.myrunsjf.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query Result</title>
</head>
<body>
	<table border="1" style="width:100%">
		<tr>
			<td>ID</td>
			<td>Input Type</td>
			<td>Activity Type</td>
			<td>Date Time</td>
			<td>Duration</td>
			<td>Distance</td>
			<td>Average Pace</td>
			<td>Average Speed</td>
			<td>Current Speed</td>
			<td>Calorie</td>
			<td>Climb</td>
			<td>Heart Rate</td>
			<td>Comment</td>
			<td></td>
		</tr>	

			<%
				ArrayList<ExerciseEntry> entryList = (ArrayList<ExerciseEntry>) request
						.getAttribute("result");
				for (ExerciseEntry entry : entryList) {
			%> 
		 <tr>
			 <td><%=entry.id%>&nbsp;&nbsp;</td> 
			 <td><%=entry.mInputType%>&nbsp;&nbsp;</td>  
			 <td><%=entry.mActivityType%>&nbsp;&nbsp;</td>  
			 <td><%=entry.mDateTime%>&nbsp;&nbsp;</td>
			 <td><%=entry.mDuration%>&nbsp;&nbsp;</td>
			 <td><%=entry.mDistance%>&nbsp;&nbsp;</td>
			 <td><%=entry.mAvgPace%>&nbsp;&nbsp;</td>
			 <td><%=entry.mAvgSpeed%>&nbsp;&nbsp;</td>
			 <td><%=entry.mCurSpeed%>&nbsp;&nbsp;</td>
			 <td><%=entry.mCalorie%>&nbsp;&nbsp;</td>
			 <td><%=entry.mClimb%>&nbsp;&nbsp;</td>
			 <td><%=entry.mHeartRate%>&nbsp;&nbsp;</td>
			 <td><%=entry.mComment%>&nbsp;&nbsp;</td>
			 <td>
			 <FORM METHOD="POST" ACTION="/delete.do?id=<%=entry.id%>">
				<INPUT TYPE="submit" VALUE="delete">
				<input type="hidden" name="user" value="<%=entry.id%>">
			 </FORM>
			 </td>
			<br>
			<%
				}
			%>
		</tr>
	</table>

</body>
</html>