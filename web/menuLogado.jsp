<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.tipousuario == 'Administrador'}">
    <jsp:include page="menuAdministrador.jsp"/>
</c:if>
<c:if test="${sessionScope.tipousuario == 'Cliente'}">
    <jsp:include page="menuCliente.jsp"/>
</c:if>
<c:if test="${sessionScope.tipousuario == 'Fornecedor'}">
    <jsp:include page="menuFornecedor.jsp"/>
</c:if>




