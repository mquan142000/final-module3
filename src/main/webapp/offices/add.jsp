<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mặt bằng mới</title>
</head>
<body>
<h1>Thêm mặt bằng mới</h1>

<form action="offices" method="post">
    <input type="hidden" name="action" value="add">

    <label for="ma_mat_bang">Mã mặt bằng:</label>
    <input type="text" id="ma_mat_bang" name="ma_mat_bang" required pattern="[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}" title="Định dạng: XXX-XX-XX"><br><br>

    <label for="trang_thai">Trạng thái:</label>
    <select id="trang_thai" name="trang_thai" required>
        <option value="Trong">Trống</option>
        <option value="Ha Tang">Hạ tầng</option>
        <option value="Day Du">Đầy đủ</option>
    </select><br><br>

    <label for="dien_tich">Diện tích (m²):</label>
    <input type="number" id="dien_tich" name="dien_tich" required min="20"><br><br>

    <label for="tang">Tầng:</label>
    <input type="number" id="tang" name="tang" required min="1" max="15"><br><br>

    <label for="loai_mat_bang">Loại mặt bằng:</label>
    <select id="loai_mat_bang" name="loai_mat_bang" required>
        <option value="Van phong chia se">Văn phòng chia sẻ</option>
        <option value="Van phong tron goi">Văn phòng trọn gói</option>
    </select><br><br>

    <label for="gia_tien">Giá tiền (VNĐ):</label>
    <input type="number" id="gia_tien" name="gia_tien" required min="1000000"><br><br>

    <label for="ngay_bat_dau">Ngày bắt đầu:</label>
    <input type="date" id="ngay_bat_dau" name="ngay_bat_dau" required><br><br>

    <label for="ngay_ket_thuc">Ngày kết thúc:</label>
    <input type="date" id="ngay_ket_thuc" name="ngay_ket_thuc" required><br><br>

    <input type="submit" value="Thêm mặt bằng">
</form>

<a href="offices?action=list">Quay lại danh sách mặt bằng</a> <!-- Liên kết quay lại danh sách -->
</body>
</html>
