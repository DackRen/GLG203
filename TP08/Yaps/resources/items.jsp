<%@ page errorPage="error.jsp"%>
<%@ page import = "com.yaps .petstore.common.dto.ItemDTO " %>

<%@ page import = "java.util.Collection" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Iterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>YAPS Items</title>
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

        <td align="center" width="60%">
        <%--CENTRAL BODY--%>
        
        <% Collection itemsDTO = (ArrayList)request.getAttribute("itemsDTO");%>
        <% ItemDTO itemDTO = new ItemDTO(); %>
        <% Iterator<ItemDTO> iter = itemsDTO.iterator();%>
        <%while ( iter.hasNext() ){ %>
        <% itemDTO = iter.next();%>
			<p>
				<A Href="<%= request.getContextPath() %>/finditem?itemId=<%= itemDTO.getId()%>" > <%= itemDTO.getName() %> </A>
				<%= itemDTO.getProductDescription() %> <%= itemDTO.getUnitCost() %>
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
