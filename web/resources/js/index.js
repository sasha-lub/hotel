$(document).ready(function() {
	$('.parallax').parallax();
	
	$('select').material_select();

//	$(".dropdown-button").dropdown();

	$(".button-collapse").sideNav();
	
	$('.datepicker').pickadate({
	    selectMonths: true, 
	    selectYears: 2, 
	    format: 'yyyy-mm-dd',
	    min: Date.now(),
	    firstDay: 'Monday',
	 // Strings and translations
	    monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
	    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
	    weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
	    weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
	    showMonthsShort: undefined,
	    showWeekdaysFull: undefined,

	    // Buttons
	    today: 'Today',
	    clear: 'Clear',
	    close: 'Close',

	    // Accessibility labels
	    labelMonthNext: 'Next month',
	    labelMonthPrev: 'Previous month',
	    labelMonthSelect: 'Select a month',
	    labelYearSelect: 'Select a year',
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
