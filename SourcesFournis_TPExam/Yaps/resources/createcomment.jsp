<%@ page errorPage="error.jsp"%>
<%@ taglib uri="/WEB-INF/petstore.tld" prefix="petstore" %>
<html>
<head>
	<title>YAPS PetStore - Create Comment</title>
</head>
<body>

<table cellspacing="0" cellpadding="5" width="100%">
    <%--HEADER--%>
	<tr>
		<td colspan="3">
			<jsp:include page="common/header.jsp"/>
		</td>
	</tr>

	<tr>
        <%--NAVIGATION--%>
        <td valign="top" width="20%">
    		<jsp:include page="common/navigation.jsp"/>
    	</td>

        <td align="left" width="60%">
        <%--CENTRAL BODY--%>


               <%-- customerId may be given as parameter ... to enable HttpUnit test!! --%>
               <% 
               String possibleURLparameters = "";
               if ( request.getParameter("customerId") != null )
            	   possibleURLparameters = "?customerId=" + request.getParameter("customerId");
               %>
            <form name="commentForm" method="post" action="<%= request.getContextPath() %>/createcomment<%= possibleURLparameters %>">

                <h2>Create Comment Form</h2>

                <table border="0" cellspacing="4">

	                <!-- Personal information -->
	                <tr>
		                <td colspan="2"><strong>Personal information</strong></td>
	                </tr>
	                <tr>
		                <td>*Title :</td>
		                <td><input type="text" name="title" value=""></td>
	                </tr>
	                <tr>
		                <td>*Text :</td>
		                <td><input type="text" name="text" value=""></td>
	                </tr>
                </table>
                <p>
                <input type="submit" name="Submit" value="Submit">
            </form>



    <%--FOOTER--%>
    	</td>
        <td></td>
    </tr>
    <tr>
    	<td colspan="3">
    		<jsp:include page="common/footer.jsp"/>
    	</td>
    </tr>
</table>
</body>
</html>