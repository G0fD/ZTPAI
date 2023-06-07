const loginForm = document.querySelector('.login');
const authText = document.querySelector('p[name="authFailed"]');

loginForm.addEventListener("submit", function (event) {
    event.preventDefault();

    let username = loginForm.login.value;
    let password = loginForm.password.value;

    let loginData = {
        username: username,
        password: password
    };

    fetch('/api/auth/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Authentication failed');
            }
        })
        .then(function (responseData) {
            if (responseData.token) {
                localStorage.setItem('token', responseData.token);
                console.log(responseData.message);
                window.location.href("/auth/site");
            } else {
                console.error('Authentication failed');
            }
        })
        .catch(function (error) {
            console.error(error);
            authText.textContent = 'Authentication failed';
        });
});