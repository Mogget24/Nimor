$(document).ready(function(){

	// Toggle Menu
	$(document).on('click', '.btn-toggle-menu', function(e){
		e.preventDefault();

		var menu_container = $('#main-menu')

		menu_container.toggleClass('active')

	})
	
	// Switch active planet
	$(document).on('click', '.planet:not(.active)', function(e){
		e.preventDefault()

		var that = $(this),
			active = $('.planet.active')

		// Add class
		that.addClass('active')
		active.removeClass('active')

	})

})
