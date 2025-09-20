<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .login-container { width: 400px; margin: 100px auto; padding: 30px; background: #fff; border-radius: 10px; box-shadow: 0 0 10px #aaa; }
        h2 { text-align: center; margin-bottom: 20px; }
        label { display: block; margin-top: 10px; }
        input[type="text"], input[type="password"] { width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box; }
        input[type="submit"] { width: 100%; padding: 10px; margin-top: 20px; background-color: #4CAF50; color: #fff; border: none; border-radius: 5px; cursor: pointer; }
        .error { color: red; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Đăng nhập</h2>

    <form method="post" action="login">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>

        <input type="submit" value="Login">
    </form>

    <!-- Hiển thị lỗi nếu có -->
    <div class="error">
        ${error}
    </div>
</div>

</body>
</html>
