function showToast() {
	$(".toast").each(function() {
		const $toastBodyText = $(this).find(".toast-body").eq(0).text();
		if ($toastBodyText) $(this).show(300);
		$(this).find(".btn-close").eq(0).on("click", () => $(this).hide(300));
	})
}

$(document).ready(() => {
	showToast();
})