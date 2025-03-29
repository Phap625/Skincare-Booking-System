document.addEventListener("DOMContentLoaded", function () {
  const loginSection = document.getElementById("loginSection");
  const userDropdown = document.getElementById("userDropdown");
  const usernameDisplay = document.getElementById("usernameDisplay");
  const logoutButton = document.querySelector("#userDropdown a[href='#']"); // Tìm nút Đăng xuất

  // Lấy thông tin từ localStorage
  const token = localStorage.getItem("token")?.trim();
  const username = localStorage.getItem("username")?.trim();


  console.log("Token:", token); // Debug
  console.log("Username:", username); // Debug

  if (token && token!=="undefined" && username) {
    console.log("Đã đăng nhập, ẩn nút Login và hiển thị Dropdown");
    if (loginSection) loginSection.style.display = "none";  // Ẩn nút Login
    if (userDropdown) userDropdown.classList.remove("hidden"); // Hiện dropdown user
    if (usernameDisplay) usernameDisplay.textContent = username; // Gán username vào UI
  } else {
    console.log("Chưa đăng nhập, hiển thị Login và ẩn Dropdown");
    if (loginSection) loginSection.style.display = "block";  // Hiện nút Login
    if (userDropdown) userDropdown.style.display = "none"; // Ẩn dropdown user
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
