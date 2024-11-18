const authForm = document.getElementById("auth-form");
const loginSpan = document.getElementById("login-span");
const registerSpan = document.getElementById("register-span");
const switchCheckbox = document.getElementById("login-register-switch");

function switchState() {
    const isChecked = switchCheckbox.checked;

    // Toggle active class for spans
    if (isChecked) {
        loginSpan.classList.remove("active");
        registerSpan.classList.add("active");
        authForm.querySelector("button").textContent = "Kayıt Ol";
    } else {
        registerSpan.classList.remove("active");
        loginSpan.classList.add("active");
        authForm.querySelector("button").textContent = "Giriş Yap";
    }
}
