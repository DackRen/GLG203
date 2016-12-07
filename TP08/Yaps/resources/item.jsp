<%@ page errorPage="error.jsp"%>
<%@ page import = "com.yaps .petstore.common.dto.ItemDTO " %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>YAPS Item</title>
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
        
        	<% final ItemDTO itemDTO = (ItemDTO)request.getAttribute("itemDTO"); %> 
            <P> <strong><%= itemDTO.getName() %></strong> </P>
            <P> <img src="<%= itemDTO.getImagePath() %>" align="left"/> <%= itemDTO.getUnitCost() %> </P> 
            <P> <%= itemDTO.getProductDescription() %> </P>

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
