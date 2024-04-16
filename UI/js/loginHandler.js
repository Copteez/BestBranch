document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.register-form');
    const messageBox = document.getElementById('message-box'); // Ensure there's an element with this ID in your HTML

    form.addEventListener('submit', function(event) {
        event.preventDefault();  // Prevent the default form submission

        const loginData = {
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        };

        fetch('http://localhost:8080/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log('Login successful:', data);
                window.location.href = 'https://your-website.com/dashboard'; // Redirect on successful login
            } else {
                messageBox.innerHTML = '<p>Login failed: ' + data.message + '</p>'; // Display an error message
                messageBox.style.color = 'red';
                messageBox.style.display = 'block';
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            messageBox.innerHTML = '<p>Login error. Please try again later.</p>';
            messageBox.style.color = 'red';
            messageBox.style.display = 'block'; // Show error message
        });
    });
});
