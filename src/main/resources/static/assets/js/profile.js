document.addEventListener("DOMContentLoaded", function () {
    const token = localStorage.getItem("token");

    if (!token || token === "undefined") {
        alert("Bạn chưa đăng nhập");
        window.location.href = "/auths/login"; // Điều hướng về trang login
        return;
    }

    fetch("http://localhost:8080/auths/profile", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
        .then(res => {
            if (!res.ok) throw new Error("Token không hợp lệ hoặc hết hạn");
            return res.json();
        })
        .then(data => {
            console.log("Dữ liệu profile:", data);
            // Gán dữ liệu vào UI
            document.getElementById("usernameDisplay").textContent = data.username;
            document.getElementById("roleDisplay").textContent = data.role[0].authority;
        })
        .catch(err => {
            console.error("Lỗi lấy thông tin:", err);
            alert("Bạn chưa đăng nhập hoặc token không hợp lệ");
            localStorage.removeItem("token");
            window.location.href = "/auths/login";
        });
});
