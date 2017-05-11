$(document).ready(
		function() {
			$("#reserv-form").change(
					function() {
						$("#general-price").removeClass('error');
						if ($("#reserv-from-date").val() != ''
								&& ($("#reserv-to-date").val() != '')) {
							getGeneralPrice();
						} else {
							$("#general-price").text(
									"Please, fill up both date fields");
						}
					})
		})

function getGeneralPrice() {
	$("#general-price").removeClass('error');
	var date1 = new Date($("#reserv-from-date").val());
	var date2 = new Date($("#reserv-to-date").val());
	var timeDiff = Math.abs(date2.getTime() - date1.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
	var price = parseFloat($("#room-price").text());

	$("#general-price").text("Total price: " + diffDays * price + "$");
}

function openReservModal() {
	$('#reservation-modal').modal();
}

function closeReservModal() {
	$('#reservation-modal').modal('close');
	document.forms.reserve.reset();
}

function confirmReserve(userId, roomId) {
	if ($("#reserv-from-date").val() === ''
			|| ($("#reserv-to-date").val() === '')) {
		$("#general-price").addClass('error').text(
				"Please, fill up both date fields");
	} else if (new Date($("#reserv-from-date").val()) >= new Date($(
			"#reserv-to-date").val())) {

		$("#general-price").addClass('error').text(
				"Please, fill up dates correctly!");

	} else {

        $.ajax({
			url : "/client/reserve",
			cache : false,
			type : 'POST',
			data : {
				userId : userId,
				roomId : roomId,
				from : $("#reserv-from-date").val(),
				to : $("#reserv-to-date").val(),
			},
			statusCode : {
				200 : function(id) {
					closeReservModal();
				},
				423 : function() {
					$("#reserv-form-error").text(
							"Sorry, room is not available for these dates");
				},
				400 : function() {
					$("#reserv-form-error").text("Fill up date fields!");
				},
				401 : function() {
					$("#reserv-form-error").text(
							"You have no permission for this operation!");
				},
				500 : function() {
					$("#reserv-form-error").text(
							"Oops some errors occured on server");
				}
			}

		});
	}
}

function checkIfAvailable(roomId) {

    $("#reserv-form-ok").text("");
	$("#reserv-form-error").text("");

	if ($("#reserv-from-date").val() === ''
			|| ($("#reserv-to-date").val() === '')) {

		$("#general-price").addClass('error').text(
				"Please, fill up both date fields");
	} else if (new Date($("#reserv-from-date").val()) >= new Date($(
			"#reserv-to-date").val())) {

		$("#general-price").addClass('error').text(
				"Please, fill up dates correctly!");

	} else {
		$.ajax({
			url : "/room/check",
			cache : false,
			type : 'GET',
			data : {
				roomId : roomId,
				from : $("#reserv-from-date").val(),
				to : $("#reserv-to-date").val(),
			},
			statusCode : {
				200 : function() {
					$("#reserv-form-error").text("");
					$("#reserv-form-ok").text(
							"Room is available for these days");
				},
				423 : function() {
					$("#reserv-form-ok").text("");
					$("#reserv-form-error").text(
							"Sorry, room is not available for these dates");
				},
				401 : function() {
					$("#reserv-form-ok").text("");
					$("#reserv-form-error").text("Fill up date fields!");
				},
				400 : function() {
					$("#reserv-form-ok").text("");
					$("#reserv-form-error").text(
							"You have no permission for this operation!");
				},
				500 : function() {
					$("#reserv-form-ok").text("");
					$("#reserv-form-error").text(
							"Oops some errors occured on server");
				}
			}
		});
	}
}