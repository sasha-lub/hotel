$(document).ready(function() {
	$('.parallax').parallax();
	
	$('select').material_select();

//	$(".dropdown-button").dropdown();

	$(".button-collapse").sideNav();
    $('.collapsible').collapsible();
	
	$('.datepicker').pickadate({
	    selectMonths: true, 
	    selectYears: 2, 
	    format: 'yyyy-mm-dd',
	    min: Date.now(),
	    firstDay: 'Monday'
	  });

});

function reload () {
   location.reload();
}

function changeLocale(lang) {
	$.ajax({
		url : "/setlocale/",
		cache : false,
		type : 'POST',
		data : {
			language : lang
		},
		success : function(){
			reload();
		}
	});

}

function openInfModal() {
	$("#info-modal").modal();
}
