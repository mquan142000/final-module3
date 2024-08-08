<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách mặt bằng cho thuê</title>
    <script>
        function confirmDelete(maMatBang) {
            var confirmAction = confirm("Bạn có chắc chắn muốn xóa mặt bằng với mã số " + maMatBang + " không?");
            if (confirmAction) {
                window.location.href = "${pageContext.request.contextPath}/offices?action=delete&ma_mat_bang=" + maMatBang;
            }
        }
    </script>
</head>
<body>
<h1>Danh sách mặt bằng cho thuê</h1>

<a href="/offices?action=add">Thêm mặt bằng mới</a>

<form action="${pageContext.request.contextPath}/offices" method="get">
    <input type="hidden" name="action" value="search"/>
    <%--@declare id="loai_mat_bang"--%><%--@declare id="gia_tien"--%><%--@declare id="tang"--%>
    <label for="loai_mat_bang">Loại mặt bằng:</label>
    <input type="text" name="loai_mat_bang" required>

    <label for="gia_tien">Giá tiền:</label>
    <input type="number" name="gia_tien" required>

    <label for="tang">Tầng:</label>
    <input type="number" name="tang" required>

    <input type="submit" value="Tìm kiếm">
</form>

<table border="1">
    <thead>
    <tr>
        <th>Mã mặt bằng</th>
        <th>Trạng thái</th>
        <th>Diện tích (m²)</th>
        <th>Tầng</th>
        <th>Loại mặt bằng</th>
        <th>Giá tiền (VNĐ)</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="office" items="${listOffices}">
        <tr>
            <td><c:out value="${office.maMatBang}"/></td>
            <td><c:out value="${office.trangThai}"/></td>
            <td><c:out value="${office.dienTich}"/></td>
            <td><c:out value="${office.tang}"/></td>
            <td><c:out value="${office.loaiMatBang}"/></td>
            <td><c:out value="${office.giaTien}"/></td>
            <td><c:out value="${office.ngayBatDau}"/></td>
            <td><c:out value="${office.ngayKetThuc}"/></td>
            <td>
                <a href="#" onclick="confirmDelete('${office.maMatBang}')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
