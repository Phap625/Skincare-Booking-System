document.addEventListener("DOMContentLoaded", function () {
    const token = localStorage.getItem("token");

    if (!token || token === "undefined") {
        alert("Bạn chưa đăng nhập");
        window.location.href = "/auths/login"; // Điều hướng về trang login
    }
});
