const loginForm = document.getElementById("login-form"); // Lấy form đăng nhập
const loginButton = document.getElementById("login");  // Nút đăng nhập
const registerButton = document.getElementById("register");//Nút đăng kí
const container = document.getElementById("container");
const ButtonLogin = document.getElementById("Đăng nhập");//nút chuyển đổi
const regisButton = document.getElementById("Đăng kí");//nút chuyển đổi



loginButton.addEventListener("click", async (event) => {
    event.preventDefault(); // Ngăn trang reload mặc định


    // Lấy dữ liệu từ input
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    if (!username || !password) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    // Gọi API đăng nhập
    try {
        const response = await fetch("http://localhost:8080/auths/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();

        if (response.ok) {
            // ✅ Đăng nhập thành công, lưu token vào localStorage
            localStorage.setItem("token", data.token);
            localStorage.setItem("username", username);
            alert("Đăng nhập thành công!");
            window.location.href = "/"; // Chuyển hướng sau khi đăng nhập
        } else {
            // ❌ Đăng nhập thất bại
            alert("Sai thông tin đăng nhập!");
        }
    } catch (error) {
        console.error("Lỗi khi gọi API:", error);
        alert("Có lỗi xảy ra, vui lòng thử lại!");
    }
});

function validateEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}


registerButton.addEventListener("click", async (event) => {
    event.preventDefault(); // Ngăn trang reload mặc định


    // Lấy dữ liệu từ input
    const username = document.getElementById("username-register").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password-register").value;


    if (!validateEmail(email)) {
        alert("Email không hợp lệ! Vui lòng nhập đúng định dạng (ví dụ: example@gmail.com)");
        return;
    }
    if (!username  || !email || !password) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    // Gọi API đăng kí
    try {
        const response = await fetch("http://localhost:8080/auths/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, email, password, role:"customer"})
        });

        const data = await response.text();

        if (response.ok) {
            // ✅ Đăng kí thành công
            alert("Đăng kí thành công!");
            window.location.href = "/auths/login"; // Chuyển hướng sau khi đăng kí
        } else {
            // ❌ Đăng kí thất bại
            alert("Đăng kí thất bại!");
        }
    } catch (error) {
        console.error("Lỗi khi gọi API:", error);
        alert("Có lỗi xảy ra, vui lòng thử lại!");
    }
});



document.addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        loginButton.click(); // Gọi sự kiện đăng nhập khi nhấn Enter
    }
});

regisButton.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

ButtonLogin.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

