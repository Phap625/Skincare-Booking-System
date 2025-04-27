// viewAppointments.js

document.addEventListener("DOMContentLoaded", function () {
    const viewAppointmentsButton = document.getElementById('viewAppointmentsButton');
    const token = localStorage.getItem('token');

alert(viewAppointmentsButton)
    if (viewAppointmentsButton) {
        viewAppointmentsButton.addEventListener('click', async function () {
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
alert(response)
                if (response.ok) {

                   /* window.location.href = '/customer/appointment/list';*/
                } else if (response.status === 401 || response.status === 403) {
                    alert('Phiên đăng nhập hết hạn. Mời đăng nhập lại!');
                    localStorage.removeItem('token');
                    localStorage.removeItem('username');
                    window.location.href = '/auths/login';
                } else {
                    alert('Có lỗi xảy ra!');
                }
            } catch (error) {
                console.error(error);
                alert('Không kết nối được đến server!');
            }
        });
    }
});
