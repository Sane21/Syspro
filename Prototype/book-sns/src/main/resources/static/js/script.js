'use strict;'


function replace(){
	window.addEventListener("load", () => {
		prevBooks = books;
		books.forEach(book => {
			let pElement = document.getElementById(book.bookId);
			if(book.detail != null){
				let after = book.detail.replace(/(\r\n|\n|\r)/gm, '<br>');
				pElement.innerHTML = after;
			}		
		})
		
	});
}

/**
function show(){
	books.forEach(book => {
	fetch("https://api.openbd.jp/v1/get?isbn=" + book.isbn).then(res => {
	   return res.json();
	})
	.then(json => {
		//let contents = json[0].onix.CollateralDetail.TextContent;
		content = json[0].onix.CollateralDetail.TextContent[0];
		let pElement = document.getElementById(book.bookId);
		pElement.innerText = content.Text;
		if(content.Text.length <= 60) { //ひとつめの要素の説明が短すぎるときは、次のやつの説明も追加
			addContent = json[0].onix.CollateralDetail.TextContent[1];
			pElement.innerText += addContent.Text;
		}
		let img = json[0].onix.CollateralDetail.SupportingResource[0].ResourceVersion[0].ResourceLink;
		let imgElement = document.getElementById("img" + book.bookId);
		imgElement.src = img;
		
	})
	.catch(e => {
		let errorElement = document.getElementById(book.bookId);
		errorElement.innerText = 'APIから情報を取得できませんでした';
		let imgElement = document.getElementById("img" + book.bookId);
		imgElement.src = 'https://www.shoshinsha-design.com/wp-content/uploads/2020/05/noimage-760x460.png';
	})
	
		
	});
}

 */