<%@ page errorPage="error.jsp"%>
<%@ taglib uri="/WEB-INF/petstore.tld" prefix="petstore" %>
<%@ page import="com.yaps.petstore.common.dto.CommentDTO"%>

<%@ page import = "java.util.Collection" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Iterator" %>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>YAPS PetStore</title>
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


      
	<% String customerId = (String)request.getAttribute("customerId");
	if(customerId==null)
		out.println("Comments from all customers");
	else
		out.println("Comments from "+customerId);
	
	%>
        	
        <% Collection commentsDTO = (ArrayList)request.getAttribute("commentsDTO");%>
        <% CommentDTO commentDTO = new CommentDTO(); %>
        <% Iterator<CommentDTO> iter = commentsDTO.iterator();%>
        <%while ( iter.hasNext() ){ %>
        <% commentDTO = iter.next();%>
			<p>
				<%= (String)request.getAttribute("customerId") %> <%= commentDTO.getDate() %>
				<%= commentDTO.getTitle() %><%= commentDTO.getText() %>
			</p>
		<%}%> 




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