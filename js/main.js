$(document).ready(function(){

	// Toggle Menu
	$(document).on('click', '.btn-toggle-menu', function(e){
		e.preventDefault();

		var menu_container = $('#main-menu')

		menu_container.toggleClass('active')

	})
	

})
