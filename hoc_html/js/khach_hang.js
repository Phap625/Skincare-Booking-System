 // Sample data for customers
 const customers = [
    { name: 'John Doe', email: 'johndoe@example.com', phone: '123-456-7890', address: '123 Main St', status: 'Active' },
    { name: 'Jane Smith', email: 'janesmith@example.com', phone: '234-567-8901', address: '456 Oak St', status: 'Inactive' },
    { name: 'Bob Johnson', email: 'bobjohnson@example.com', phone: '345-678-9012', address: '789 Pine St', status: 'Active' },
    { name: 'Alice Brown', email: 'alicebrown@example.com', phone: '456-789-0123', address: '101 Maple St', status: 'Active' },
    { name: 'Charlie Davis', email: 'charliedavis@example.com', phone: '567-890-1234', address: '202 Birch St', status: 'Inactive' },
    { name: 'Eva White', email: 'evawhite@example.com', phone: '678-901-2345', address: '303 Cedar St', status: 'Active' },
    { name: 'Mark Taylor', email: 'marktaylor@example.com', phone: '789-012-3456', address: '404 Willow St', status: 'Inactive' },
    // Add more customers here as needed
];

let currentPage = 1;
const rowsPerPage = 5;

// Function to render customers in the table
function renderTable(customers) {
    const tableBody = document.getElementById('customerTable').getElementsByTagName('tbody')[0];
    tableBody.innerHTML = ''; // Clear previous rows

    customers.forEach(customer => {
        const row = document.createElement('tr');

        // Create table cells for each customer attribute
        Object.values(customer).forEach(value => {
            const cell = document.createElement('td');
            cell.textContent = value;
            row.appendChild(cell);
        });

        // Create action buttons
        const actionCell = document.createElement('td');
        actionCell.classList.add('action-buttons');

        // Edit Button
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.classList.add('edit-button');
        actionCell.appendChild(editButton);

        // Delete Button
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.classList.add('delete-button');
        actionCell.appendChild(deleteButton);

        // Activate/Deactivate Button
        const statusButton = document.createElement('button');
        statusButton.textContent = customer.status === 'Active' ? 'Deactivate' : 'Activate';
        statusButton.classList.add(customer.status === 'Active' ? 'deactivate-button' : 'activate-button');
        actionCell.appendChild(statusButton);

        row.appendChild(actionCell);
        tableBody.appendChild(row);
    });
}

// Function to handle search and filter
function filterCustomers() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const filteredCustomers = customers.filter(customer => 
        customer.name.toLowerCase().includes(searchInput) ||
        customer.email.toLowerCase().includes(searchInput) ||
        customer.phone.toLowerCase().includes(searchInput) ||
        customer.address.toLowerCase().includes(searchInput)
    );
    renderTable(filteredCustomers);
    setupPagination(filteredCustomers);
}

// Function to create pagination buttons
function setupPagination(filteredCustomers) {
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';

    const totalPages = Math.ceil(filteredCustomers.length / rowsPerPage);
    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement('button');
        button.textContent = i;
        button.addEventListener('click', () => {
            currentPage = i;
            renderPage(filteredCustomers);
        });
        pagination.appendChild(button);
    }
}

// Function to render current page's customers
function renderPage(filteredCustomers) {
    const startIndex = (currentPage - 1) * rowsPerPage;
    const currentPageCustomers = filteredCustomers.slice(startIndex, startIndex + rowsPerPage);
    renderTable(currentPageCustomers);
}

// Initial setup: Render table and pagination
renderTable(customers);
setupPagination(customers);