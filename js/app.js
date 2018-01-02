App = window.App || {}

App.environment = window.location.host == 'localhost' ? 'sdk' : 'production'

App.domain = (App.environment == 'production' ? 'http://ergoreader.altervista.org' : "http://localhost/reader")

App.maintenance = false

App.isApp = false

if(App.maintenance){
	window.location.href = App.domain + '/maintenance.php'
}

App.data = App.data || {}

App.init = function() {

	// Add parameters
	var query = window.location.search.replace('?', '')

	if(query != ""){
		
		App.params = new Array()

		var params = query.includes('&') ? query.split('&') : query

		if(query.includes('app=true'))
			App.isApp = true

		for (var i = 0; i < params.length; i++){
			App.params.push({
				'key' : params[i].split('=')[0],
				'value' : params[i].split('=')[1],
			})
		}

	}

	$.getJSON( "config.json", function( data ) {

		// Gather Mangas info
		var mangas = App.data = data.mangas

		// Populate series with correct status
		var series_list = $('.series')
		if(series_list.length){

			for(var i = 0; i < mangas.length; i++){
				series_list.each(function(){
					var that = $(this),
						list_status = that.data('status')

					if((mangas[i].status == list_status) && !mangas[i].hidden){
						that.append($('<li class="single-serie ' + (mangas[i].external ? 'external' : '') + '" data-serie-id="' + mangas[i].id + '" data-serie-name="' + mangas[i].name + '"></li>'))

						/*
						if(window.location.href.includes('?activate_tags=true')){
							console.log('tags activated')
							$('.single-serie[data-serie-id="' + mangas[i].id + '"]').attr('data-serie-desc', mangas[i].desc)
																					.attr('data-serie-tags', mangas[i].tags)
						}
						*/

						var href = !mangas[i].external ? ('reader.php?chapter=' + (mangas[i].end < 10 ? ('0' + mangas[i].end) : mangas[i].end) + '&series=' + mangas[i].id) : mangas[i].external
						$('.single-serie[data-serie-id="' + mangas[i].id + '"]').append($('<a href="' + href + '" target="_blank"><p>' + mangas[i].name + ' - Capitolo '+ mangas[i].end +'</p></a>'))
					}
				})	
			}

		}

		// Populated dropdown with series
		var main_dropdown = $('#choose_manga_dropdown')
		if(main_dropdown.length)
			for(var i = 0; i < mangas.length; i++){
				if(!mangas[i].external){
					var option = $('<option value="' + mangas[i].id + '" data-manga-id="' + mangas[i].id + '" data-start="' + mangas[i].start + '" data-end="' + mangas[i].end + '">' + mangas[i].name + '</option>')
					
					// Add Extra
					if(mangas[i].extra.length > 0)
						option.attr('data-extra', mangas[i].extra)

					main_dropdown.append(option)
				}
			}

		// Subscribe Form
		var subscribe_form = $('.from-subscribe .checkbox-wrapper')
		if(subscribe_form.length){
			for(var i = 0; i < mangas.length; i++){
				subscribe_form.append($('<div class="checkbox"><label for="input_' + mangas[i].id + '"><input type="checkbox" name="' + mangas[i].id + '" id="input_' + mangas[i].id + '">' + mangas[i].name + '</label></div>'))
			}
		}

	});

}()

App.dispatch = function(img_element, src, time){

	setTimeout(function(){
		img_element.attr('src', src)
	}, time)

}