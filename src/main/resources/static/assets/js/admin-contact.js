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

async function loadContacts() {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Token không tồn tại. Vui lòng đăng nhập lại.");
        window.location.href = "/auths/login";
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/admin/contact", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            throw new Error(`Lỗi khi lấy danh sách contact: ${response.status}`);
        }

        const contacts = await response.json();

        console.log("Danh sách contact:", contacts);

        // Ví dụ: render contact ra 1 bảng
        const contactTableBody = document.querySelector("#contactTable tbody");
        contactTableBody.innerHTML = "";
        contacts.forEach(contact => {
            contactTableBody.innerHTML += `
                <tr>
                    <td>${contact.id}</td>
                    <td>${contact.name}</td>
                    <td>${contact.email}</td>
                    <td>${contact.subject}</td>
                    <td>${contact.message}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Lỗi khi tải contact:", error);
        alert("Không thể tải danh sách contact: " + error.message);
    }
}
loadContacts();