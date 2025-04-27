// main.js

document.addEventListener("DOMContentLoaded", async function () {
  const loginSection = document.getElementById("loginSection");
  const userDropdown = document.getElementById("userDropdown");
  const usernameDisplay = document.getElementById("usernameDisplay");
  const logoutButton = document.querySelector("#userDropdown a[href='#']");
  const extendSessionPrompt = document.getElementById("extendSessionPrompt");

  // Lấy token + username từ localStorage
  const token = localStorage.getItem("token")?.trim();
  const username = localStorage.getItem("username")?.trim();

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

  // Kiểm tra login
  if (token && token !== "undefined" && username && !isTokenExpired(token)) {
    if (loginSection) loginSection.style.display = "none";
    if (userDropdown) userDropdown.classList.remove("hidden");
    if (usernameDisplay) usernameDisplay.textContent = username;
  } else {
    if (loginSection) loginSection.style.display = "block";
    if (userDropdown) userDropdown.style.display = "none";
    if (extendSessionPrompt) extendSessionPrompt.style.display = "block";
    localStorage.removeItem("token");
    localStorage.removeItem("username");
  }

  // Logout
  if (logoutButton) {
    logoutButton.addEventListener("click", function (e) {

      e.preventDefault(); // Ngăn chặn hành động mặc định
      console.log("Người dùng đăng xuất");
      localStorage.removeItem("role");
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      window.location.href = '/auths/login';
    });
  }


  // gọi API contact
  const contactForm = document.getElementById('contactForm');
  if (contactForm) {
    contactForm.addEventListener('submit', async function(event) {
      event.preventDefault(); // Ngăn form submit mặc định

      // Lấy dữ liệu từ form
      const formData = new FormData(contactForm);
      const data = {
        name: formData.get('name'),
        email: formData.get('email'),
        subject: formData.get('subject'),
        message: formData.get('message')
      };

      // Lấy JWT token từ localStorage
      const jwtToken = localStorage.getItem('token');

      // Lấy các phần tử thông báo
      const loading = contactForm.querySelector('.loading');
      const errorMessage = contactForm.querySelector('.error-message');
      const sentMessage = contactForm.querySelector('.sent-message');

      // Reset trạng thái thông báo
      loading.style.display = 'block';
      errorMessage.style.display = 'none';
      sentMessage.style.display = 'none';

      try {
        // Chuẩn bị headers
        const headers = {
          'Content-Type': 'application/json'
        };
        if (jwtToken) {
          headers['Authorization'] = `Bearer ${jwtToken}`; // Thêm JWT token vào header
        }

        // Gửi yêu cầu POST đến API
        const response = await fetch('http://localhost:8080/contact', {
          method: 'POST',
          headers: headers,
          body: JSON.stringify(data)
        });

        if (response.ok) {
          // Hiển thị thông báo thành công
          sentMessage.style.display = 'block';
          contactForm.reset(); // Xóa form
        } else {
          // Hiển thị thông báo lỗi
          const errorData = await response.json();
          errorMessage.textContent = errorData.message || 'Đã xảy ra lỗi khi gửi thông tin!';
          errorMessage.style.display = 'block';
        }
      } catch (error) {
        // Xử lý lỗi mạng
        errorMessage.textContent = 'Lỗi kết nối. Vui lòng thử lại sau!';
        errorMessage.style.display = 'block';
      } finally {
        loading.style.display = 'none';
      }
    });
  }



});
