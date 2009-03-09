

function onFocus() {
	hideHelp();
	$(this).addClass('focused');
	var id = this.id;
	$('div.' + id).show();
}

function onBlur() {
	hideHelp();
	$(this).removeClass('focused');
}

function hideHelp() {
	$('div.help').hide();
}

$(function() {
	
	hideHelp();
	
	$(':text').add(':password').focus(onFocus).blur(onBlur);
	
});