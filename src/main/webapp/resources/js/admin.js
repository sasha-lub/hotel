$(document).ready(function () {
    $('ul.tabs').tabs();

});

function resolveApp(appid) {
    var id = appid.substring(3);
    $.ajax({
        url: "/manager/setAppId",
        cache: false,
        type: 'POST',
        data: {
            appId: id
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
        }
    });

    $('#createAppResponseModal').modal();
}

function sendReserveRecordToAdmin(id) {
    stompClient.send("/app/newReservation", {}, JSON.stringify({
        id: id
    }));
}

function addReserveRow(reserve) {
    var table_id;

    $('tr#' + reserve.id).remove();
    switch (reserve.status) {
        case "UNPAID" : {
            table_id = "#reservationsList";
            break;
        }
        case "PAID" : {
            table_id = "#paid-reservationsList";
            break;
        }
        case "CONFIRMED" : {
            table_id = "#confirmed-reservationsList";
            break;
        }
    }
    var td_userId = "<td>" + reserve.user.id + "</td>";
    var td_roomId = "<td>" + reserve.room.id + "</td>";
    var td_from = "<td>" + getDate(reserve.checkInDate) + "</td>";
    var td_to = "<td>" + getDate(reserve.checkOutDate) + "</td>";
    var td_deadline = "<td>" + getDateTime(reserve.paymentDeadline) + "</td>";
    var td_price = "<td>" + reserve.price + "</td>";
    var td_status = "<td>" + reserve.status + "</td>";
    $(table_id).append("<tr id='" + reserve.id + "'  data-target='confirmPaymentModal' onclick='openConfirmPaymentModal(this.id)'>" + td_userId + td_roomId + td_from + td_to + td_deadline + td_price + td_status + "</tr>");
}

function openRecomendModal() {
    $('#recomend-modal').modal();
}

function openConfirmPaymentModal(id) {
    $('#confirmPaymentModal').modal();
    $('#confirm-reserve-id').val(id);
}

function getDateTime(dateTime) {
    var d = new Date(dateTime.year, getMonthFromString(dateTime.month), dateTime.dayOfMonth, dateTime.hour, dateTime.minute, dateTime.second);
    var date = d.format("yyyy-mm-dd");
    var time = d.format("HH:MM:ss");
    return date + " " + time;
}

function getDate(date) {
    var d = new Date(date.year, getMonthFromString(date.month), date.dayOfMonth);
    return d.format("yyyy-mm-dd");
}

function getMonthFromString(mon) {

    var d = Date.parse(mon + "1, 2012");
    if (!isNaN(d)) {
        return new Date(d).getMonth();
    }
    return -1;
}

function sendAppResponse(appId, roomId) {

    var comment = $("#recomend-comment").val() ? $("#recomend-comment").val() : "";
    $.ajax({
        url: "/manager/appResponse",
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
            sendReserveRecordToAdmin(id);
        }
    });
}

function changeCounters(paid, unpaid, conf) {
    $("#paid-counter-server").text("");
    $("#unpaid-counter-server").text("");
    $("#conf-counter-server").text("");

    $("#paid-counter").text("(" + paid + ")");
    $("#unpaid-counter").text("(" + unpaid + ")");
    $("#conf-counter").text("(" + conf + ")");

}