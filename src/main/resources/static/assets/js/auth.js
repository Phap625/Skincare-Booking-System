const logoutButton = document.getElementById("logoutId");  // Nút đăng xuất

logoutButton.addEventListener("click", async (event) => {
    event.preventDefault(); // Ngăn trang reload mặc định

    localStorage.setItem("token", null);
    localStorage.setItem("username", null);
    localStorage.setItem("role",null);
    window.location.href = "/auths/login";
});

