$(document).ready(function () {
    $('ul.tabs').tabs();

});

function resolveApp(appid) {
    $.ajax({
        url: "/application/setAppId",
        cache: false,
        type: 'POST',
        data: {
            appId: appid.substring(3)
        }
    });

    var capacity = $("#" + appid).find("td").eq(1).html();
    var from = $("#" + appid).find("td").eq(2).html();
    var to = $("#" + appid).find("td").eq(3).html();
    var classOfRoom = $("#" + appid).find("td").eq(4).html();
    $("#search-to-date").val(to);
    $("#search-from-date").val(from);

    $.ajax({
        url: "/room/search",
        cache: false,
        type: 'GET',
        data: {
            classOfRoom: classOfRoom,
            capacity: capacity,
            from: from,
            to: to,
            maxPrice: $("#search-max-price").val(),
            sort: $("#sortBy").val()
        },

        success: function (result) {
            fillResultTable(result);
        }
    });

    $('#createAppResponseModal').modal();
}

function openRecomendModal() {
    $('#recomend-modal').modal();
}

function openConfirmPaymentModal(id) {
    $('#confirmPaymentModal').modal();
    $('#confirm-reserve-id').val(id);
}

function sendAppResponse(appId, roomId) {
    var comment = $("#recomend-comment").val() || "";

    $.ajax({
        url: "/application/appResponse",
        cache: false,
        type: 'POST',
        data: {
            appId: appId,
            roomId: roomId,
            comment: comment
        },
        success: function () {
            $('#recomend-modal').modal("close");
            redirect();
        }
    });
}

function confirmPayment() {
    var id = $('#confirm-reserve-id').val();
    $.ajax({
        url: "/room/changeReservationStatus",
        cache: false,
        type: 'POST',
        data: {
            reserveId: id,
            status: 'CONFIRMED'
        },
        success: function () {
            $('#confirmPaymentModal').modal('close');

            $('tr#' + id).insertBefore($('#confirmed-reservationsList tbody tr:first'));
            changeCounters();
        }
    });
}

function changeCounters() {

    var paid = parseInt($("#paid-counter-server").text());
    var conf = parseInt($("#conf-counter-server").text());

    $("#paid-counter-server").text("");
    $("#conf-counter-server").text("");

    $("#paid-counter").text((paid - 1));
    $("#conf-counter").text((conf + 1));
}