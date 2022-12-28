'use strict;'

function show(){
	books.forEach(book => {
	fetch("https://api.openbd.jp/v1/get?isbn=" + book.isbn).then(res => {
	   return res.json();
	})
	.then(json => {
		//let contents = json[0].onix.CollateralDetail.TextContent;
		content = json[0].onix.CollateralDetail.TextContent[0];
		let pElement = document.getElementById(book.bookId);
		console.log(pElement);
		pElement.innerText = content.Text;
	});
		
	});
}