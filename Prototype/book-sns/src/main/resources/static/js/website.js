'use strict'

const passwordElement = document.getElementById('password');
const eye = document.getElementById('eye');
const eyebtn = document.getElementById("eyebtn");
eyebtn.addEventListener('click', function(){
	if(eye.classList.contains('bi-eye-fill')){
		eye.classList.remove('bi-eye-fill');
		eye.classList.add('bi-eye-slash-fill');
		passwordElement.type = "text";
	}else if(eye.classList.contains('bi-eye-slash-fill')){
		eye.classList.remove('bi-eye-slash-fill');
		eye.classList.add('bi-eye-fill');
		passwordElement.type = "password";
	}
});