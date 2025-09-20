<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tạo tài khoản mới</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        .register-container {
            width: 350px;
            margin: 60px auto;
            background: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-container input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        .register-container button {
            width: 100%;
            padding: 10px;
            background: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
        }
        .register-container button:hover {
            background: #0056b3;
        }
        .alert {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
        .success {
            color: green;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>Tạo tài khoản mới</h2>

    <!-- Hiển thị thông báo lỗi -->
    <c:if test="${not empty alert}">
        <div class="alert">${alert}</div>
    </c:if>

    <!-- Hiển thị thông báo thành công -->
    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <input type="text" name="username" placeholder="Tài khoản" required>
        <input type="text" name="fullname" placeholder="Họ tên" required>
        <input type="email" name="email" placeholder="Nhập Email" required>
        <input type="text" name="phone" placeholder="Số điện thoại">
        <input type="password" name="password" placeholder="Mật khẩu" required>
        <input type="password" name="repassword" placeholder="Nhập lại mật khẩu" required>
        <button type="submit">Tạo tài khoản</button>
    </form>

    <p style="text-align:center; margin-top:10px;">
        Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </p>
</div>
</body>
</html>
