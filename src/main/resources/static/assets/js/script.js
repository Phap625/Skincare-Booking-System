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
      e.preventDefault();
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      window.location.href = '/auths/login';
    });
  }
});
