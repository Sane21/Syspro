function isBlank(str){ //nullか全角スペースか半角スペースか判定
	let pattern = /^\s*$/;
	let result = pattern.test(str);
	return result;
}

function  isSize(str, num){
	return str.length <= num
}

function isISBN(str){
	let pattern = /^\d{13}$/;
	let result = pattern.test(str);
	return result;
}

window.addEventListener("load", () => {
	let title = document.getElementById("title");
	let ISBN = document.getElementById("ISBN");
	let author = document.getElementById("author");
	
	let socket = new SockJS("/client");
	stompClient = Stomp.over(socket);
	let form = document.getElementById("form");
	form.addEventListener("submit", () => { //フォーム送信できなかったらsendしない
		if(!isBlank(title.value) && isSize(title.value, 1024)){
			if(!isBlank(ISBN.value) && isISBN(ISBN.value)){ 
				if(!isBlank(author.value) && isSize(author.value, 64)) stompClient.send("/app/update");
			}
		}
	});
	
});