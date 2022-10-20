function showActiveNavLink() {
	$(".nav-link").each((_, item) => {
		const $href = $(item).attr("href");
		const path = location.href;
		console.log($href, path);
		if(path.includes($href)) {
			$(item).addClass("active");
		} else {
			$(item).removeClass("active");
		}
	});
}

$(document).ready(function() {
	console.log("Ready");
	showActiveNavLink();
});
