document.addEventListener("DOMContentLoaded", function () {
  const loginSection = document.getElementById("loginSection");
  const userDropdown = document.getElementById("userDropdown");
  const usernameDisplay = document.getElementById("usernameDisplay");
  const logoutButton = document.querySelector("#userDropdown a[href='#']"); // Tìm nút Đăng xuất
  const extendSessionPrompt = document.getElementById("extendSessionPrompt");

  // Lấy thông tin từ localStorage
  const token = localStorage.getItem("token")?.trim();
  const username = localStorage.getItem("username")?.trim();


  console.log("Token:", token);
  console.log("Username:", username);
  function isTokenExpired(token) {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const currentTime = Math.floor(Date.now() / 1000);
      return payload.exp < currentTime;
    } catch (e) {
      console.error("Token không hợp lệ:", e);
      return true;
    }
  }

  if (token && token!=="undefined" && username && !isTokenExpired(token)) {
    console.log("Login successful");
    if (loginSection) loginSection.style.display = "none";  // Ẩn nút Login
    if (userDropdown) userDropdown.classList.remove("hidden"); // Hiện dropdown user
    if (usernameDisplay) usernameDisplay.textContent = username; // Gán username vào UI
  } else {
    console.log("login fail");
    if (loginSection) loginSection.style.display = "block";  // Hiện nút Login
    if (userDropdown) userDropdown.style.display = "none"; // Ẩn dropdown user
    if (extendSessionPrompt) extendSessionPrompt.style.display = "block";
    localStorage.removeItem("token");
    localStorage.removeItem("username");
  }



  // Xử lý ĐĂNG XUẤT
  if (logoutButton) {
    logoutButton.addEventListener("click", function (e) {
      e.preventDefault(); // Ngăn chặn hành động mặc định
      console.log("Người dùng đăng xuất");
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      window.location.reload();
    });
  }



});



