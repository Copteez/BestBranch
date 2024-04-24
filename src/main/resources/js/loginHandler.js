document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loginForm');
    const messageBox = document.getElementById('message-box');

    form.addEventListener('submit', function(event) {
        event.preventDefault();  // Prevent the default form submission

        const loginData = {
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        };

        fetch(form.action, {  // Use the action attribute of the form
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            if (data.success) {
                window.location.href = '/dashboard';
            } else {
                messageBox.textContent = 'Login failed: ' + data.message;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            messageBox.textContent = 'Login error. Please try again later.';
        });
    });
});