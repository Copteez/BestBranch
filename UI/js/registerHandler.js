document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.register-form');
    const messageBox = document.getElementById('message-box'); // Ensure there's an element with this ID in your HTML

    form.addEventListener('submit', function(event) {
        event.preventDefault();  // Prevent the default form submission

        const formData = {
            role: document.getElementById('role').value,
            fullName: document.getElementById('full-name').value,
            email: document.getElementById('email').value,
            username: document.getElementById('username').value,
            password: document.getElementById('password').value,
            confirmPassword: document.getElementById('confirm-password').value
        };

        fetch('http://localhost:8080/user/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // Option 1: Redirect
            window.location.href = 'http://127.0.0.1:5500/UI/html/index.html';

            // Option 2: Display a success message (comment out the above line if using this option)
            messageBox.innerHTML = '<p>Registration successful! Welcome aboard.</p>';
            messageBox.style.display = 'block'; // Make sure the message box is visible
        })
        .catch((error) => {
            console.error('Error:', error);
            messageBox.innerHTML = '<p>Registration failed. Please try again later.</p>';
            messageBox.style.display = 'block'; // Show error message
        });
    });
});
