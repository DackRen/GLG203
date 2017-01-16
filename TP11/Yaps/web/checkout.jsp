<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="error.jsp"%>
<html>
<head>
	<title>YAPS PetStore Checkout</title>
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

        <td align="left" valign="top" width="60%">
        <%--CENTRAL BODY--%>

		<jsp:useBean id="itemDTO" class="com.yaps.petstore.common.dto.ItemDTO" scope="request" />

        <P><strong>Your Order is Complete</strong></P>

        <P>Your order id is <%= request.getAttribute("orderId") %></P>
        <P>Thank you for shopping with the YAPS Pet Store</P>



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