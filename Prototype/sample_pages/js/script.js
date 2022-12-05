'use strict;'

let passwordElement;
let eye;
let eyeButton;

function changeEye(){
    if(eye.classList.contains('bi-eye-fill')){
        eye.classList.remove('bi-eye-fill');
        eye.classList.add('bi-eye-slash-fill');
        passwordElement.type = "text";
    }else if(eye.classList.contains('bi-eye-slash-fill')){
        eye.classList.remove('bi-eye-slash-fill');
        eye.classList.add('bi-eye-fill');
        passwordElement.type = "password";
    }
}

window.addEventListener('load', () => {
    passwordElement = document.getElementById("password");
    eye = document.getElementById("eye");
    eyeButton = document.getElementById("eyebtn");
    eyeButton.addEventListener("click", changeEye);
});