{
	let showedit = false;
	
	
	window.addEventListener("load", () => {
		let button = document.getElementById("editProfile");
		let profile = document.getElementById("profile");
		let editProfile = document.getElementById("edit");
		
		button.addEventListener("click", (e) => {
			e.preventDefault();
			if(!showedit){
				editProfile.classList.remove("no-show");
				profile.classList.add("no-show");
			}else {
				editProfile.classList.add("no-show");
				profile.classList.remove("no-show");
			}
			showedit = !showedit;
			
		})
		
		
	});
}