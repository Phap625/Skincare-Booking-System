const token = localStorage.getItem("token");
const role = localStorage.getItem("role");
console.log(role);
console.log(token);

//API CRUD danh cho ADMIN
document.getElementById('logout-btn').addEventListener('click', function(event) {
    event.preventDefault(); // chặn chuyển trang khi click
    if (confirm('Bạn có chắc chắn muốn đăng xuất?')) {
        // Xóa token và role trong localStorage
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        localStorage.removeItem("username")

        // Chuyển hướng về trang login
        window.location.href = '/auths/login'; // thay đổi nếu đường link login khác
    }
});



const API_URL = 'http://localhost:8080/admin';
window.onload = function () {
    if (!token ) {
        alert("Token không tồn tại. Vui lòng đăng nhập lại.");
        window.location.href="/auths/login";
        return;
    }
    if(role!=="ROLE_ADMIN") {
        alert("Bạn không có quyền truy cập trang này! Chỉ admin mới được phép.");
        window.location.href = "/"; // Chuyển hướng về trang chính hoặc trang khác
        return;
    }
    loadUsers();
}


function loadUsers() {
    const token = localStorage.getItem("token");
    fetch(API_URL, {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        }
    })
        .then(res => {
            if (!res.ok) {
                throw new Error(`Lỗi khi gọi API: ${res.status}`);
            }
            return res.json();
        })
        .then(data => {
            const tableBody = document.querySelector("#userTable tbody");
            tableBody.innerHTML = '';
            data.forEach(user => {
                tableBody.innerHTML += `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>${user.phone || ''}</td>
                            <td>${user.dob || ''}</td>
                            <td>
                                <button onclick="editUser(${user.id}, '${user.username}','${user.password}','${user.email}' ,'${user.phone || ''}', '${user.role}', '${user.dob || ''}')">Sửa</button>
                                <button onclick="deleteUser(${user.id})">Xoá</button>
                            </td>
                        </tr>
                    `;
            });
        })
        .catch(error => {
            console.error("Lỗi khi tải danh sách users:", error);
            alert("Không thể tải danh sách users: " + error.message);
        });
}

function showAddUserForm() {
    document.getElementById("formContainer").style.display = 'block';
    document.getElementById("userId").value = '';
    document.getElementById("username").value = '';
    document.getElementById("password").value = '';
    document.getElementById("email").value = '';
    document.getElementById("role").value = '';
    document.getElementById("phone").value = '';
    document.getElementById("dob").value = '';
}

function cancelEdit() {
    document.getElementById("formContainer").style.display = 'none';
    document.getElementById("userId").value = '';
    document.getElementById("username").value = '';
    document.getElementById("password").value = '';
    document.getElementById("email").value = '';
    document.getElementById("role").value = '';
    document.getElementById("phone").value = '';
    document.getElementById("dob").value = '';
}

function createOrUpdateUser() {
    const token = localStorage.getItem("token");
    console.log("Token đang gửi:", token);
    if (!token) {
        alert("Token không tồn tại. Vui lòng đăng nhập lại.");
        window.location.href="/auths/login";
        return;
    }

    const id = document.getElementById("userId").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const email = document.getElementById("email").value;
    const role = document.getElementById("role").value;
    const phone = document.getElementById("phone").value;
    const dob = document.getElementById("dob").value;

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_URL}/${id}` : API_URL;

    fetch(url, {
        method: method,
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"

        },

        body: JSON.stringify({ id, username,password,email, phone, role, dob })
    })
        .then(res => {
            if (!res.ok) {
                throw new Error(`Lỗi khi lưu user: ${res.status}`);
            }
            return res.text();
        })
        .then(() => {
            loadUsers();
            document.getElementById("formContainer").style.display = 'none';
            document.getElementById("userId").value = '';
            document.getElementById("username").value = '';
            document.getElementById("password").value = '';
            document.getElementById("email").value = '';
            document.getElementById("role").value = '';
            document.getElementById("phone").value = '';
            document.getElementById("dob").value = '';
        })
        .catch(error => {
            console.error("Lỗi khi lưu user:", error);
            alert("Không thể lưu user: " + error.message);
        });
}

function editUser(id, username,password,email, phone, role, dob) {
    document.getElementById("formContainer").style.display = 'block';
    document.getElementById("userId").value = id;
    document.getElementById("username").value = username;
    document.getElementById("password").value = password;
    document.getElementById("email").value = email;
    document.getElementById("role").value = role;
    document.getElementById("phone").value = phone || '';
    document.getElementById("dob").value = dob || '';
}

function deleteUser(id) {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Token không tồn tại. Vui lòng đăng nhập lại.");
        window.location.href="/auths/login";
        return;
    }

    if (confirm("Bạn có chắc muốn xoá user này?")) {
        fetch(`${API_URL}/${id}`, {
            method: 'DELETE',
            headers: {
                "Authorization": "Bearer " + token,
            }
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error(`Lỗi khi xóa user: ${res.status}`);
                }
                loadUsers();
            })
            .catch(error => {
                console.error("Lỗi khi xóa user:", error);
                alert("Không thể xóa user: " + error.message);
            });
    }
}