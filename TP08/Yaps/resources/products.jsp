<%@ page import = "com.yaps .petstore.common.dto.ProductDTO " %> 
<%@ page import = "java.util.Collection" %>
<%@ page import = "java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>YAPS Products</title>
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
        
        <% Collection productsDTO = (Collection)request.getAttribute("productsDTO");%>
        <% ProductDTO productDTO; %>
        <% Iterator<ProductDTO> iter = productsDTO.iterator();%>
        <%while (iter.hasNext()){ %>
        <%productDTO = iter.next();%>
			<p>
				<A Href="<%= request.getContextPath() %>/finditems?productId=<%= productDTO.getId()%>" > <%= productDTO.getName() %> </A>
				<%= productDTO.getDescription() %>
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
