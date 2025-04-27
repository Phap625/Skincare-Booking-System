document.addEventListener("DOMContentLoaded", async function () {
    const loginSection = document.getElementById("loginSection");
    const userDropdown = document.getElementById("userDropdown");
    const usernameDisplay = document.getElementById("usernameDisplay");
    const logoutButton = document.querySelector("#userDropdown a[href='#']");
    const extendSessionPrompt = document.getElementById("extendSessionPrompt");

    // Lấy token + username từ localStorage
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

    if (token && token !== "undefined" && username && !isTokenExpired(token)) {
        console.log("Login successful");
        if (loginSection) loginSection.style.display = "none";
        if (userDropdown) userDropdown.classList.remove("hidden");
        if (usernameDisplay) usernameDisplay.textContent = username;
    } else {
        console.log("Login fail");
        if (loginSection) loginSection.style.display = "block";
        if (userDropdown) userDropdown.style.display = "none";
        if (extendSessionPrompt) extendSessionPrompt.style.display = "block";
        localStorage.removeItem("token");
        localStorage.removeItem("username");
    }

    // Xử lý logout
    if (logoutButton) {
        logoutButton.addEventListener("click", function (e) {
            e.preventDefault();
            console.log("Người dùng đăng xuất");
            localStorage.removeItem("token");
            localStorage.removeItem("username");
            window.location.reload();
        });
    }

    // Xử lý submit contact form
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
        contactForm.addEventListener('submit', async function(event) {
            event.preventDefault();
            const formData = new FormData(contactForm);
            const data = {
                name: formData.get('name'),
                email: formData.get('email'),
                subject: formData.get('subject'),
                message: formData.get('message')
            };

            const jwtToken = localStorage.getItem('token'); // Đúng là token, không phải jwtToken khác

            const loading = contactForm.querySelector('.loading');
            const errorMessage = contactForm.querySelector('.error-message');
            const sentMessage = contactForm.querySelector('.sent-message');

            loading.style.display = 'block';
            errorMessage.style.display = 'none';
            sentMessage.style.display = 'none';

            try {
                const headers = {
                    'Content-Type': 'application/json'
                };
                if (jwtToken) {
                    headers['Authorization'] = `Bearer ${jwtToken}`;
                }

                const response = await fetch('http://localhost:8080/contact', {
                    method: 'POST',
                    headers: headers,
                    body: JSON.stringify(data)
                });

                if (response.ok) {
                    sentMessage.style.display = 'block';
                    contactForm.reset();
                } else {
                    const errorData = await response.json();
                    errorMessage.textContent = errorData.message || 'Đã xảy ra lỗi khi gửi thông tin!';
                    errorMessage.style.display = 'block';
                }
            } catch (error) {
                errorMessage.textContent = 'Lỗi kết nối. Vui lòng thử lại sau!';
                errorMessage.style.display = 'block';
            } finally {
                loading.style.display = 'none';
            }
        });
    }

    // Xử lý xem lịch hẹn
    const viewAppointmentsButton = document.getElementById('viewAppointmentsButton');

    if (viewAppointmentsButton) {
        viewAppointmentsButton.addEventListener('click', async function () {
            alert('abcc')
            const token = localStorage.getItem('token');

            if (!token) {
                alert('Bạn chưa đăng nhập!');
                window.location.href = '/auths/login';
                return;
            }

            try {
                const response = await fetch('/customer/appointment/list', {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + token
                    }
                });

                if (response.ok) {
                    const html = await response.text();
                    document.body.innerHTML = html;
                } else if (response.status === 403 || response.status === 401) {
                    alert('Phiên đăng nhập hết hạn. Mời đăng nhập lại!');
                    localStorage.removeItem('token');
                    localStorage.removeItem('username');
                    window.location.href = '/auths/login';
                } else {
                    alert('Có lỗi xảy ra khi tải danh sách!');
                }
            } catch (error) {
                console.error(error);
                alert('Lỗi kết nối server!');
            }
        });
    }
});
