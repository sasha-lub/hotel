var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/search/result', function (resultList) {
            fillResultTable(JSON.parse(resultList.body));
        });
        stompClient.subscribe('/application/new', function (application) {
            addAppRow(JSON.parse(application.body));
            stompClient.send("/app/appsCount", {}, {});
        });

        stompClient.subscribe('/application/appsCount', function (count) {
            newApps(count.body);
        });
        stompClient.subscribe('/reservation/newrow', function (reservation) {
            addReserveRow(JSON.parse(reservation.body));
            stompClient.send("/app/reservationCounters", {}, {});
        });
        stompClient.subscribe('/reservation/counters', function (count) {
            changeCounters(JSON.parse(count.body).paid, JSON.parse(count.body).unpaid, JSON.parse(count.body).confirmed);
        });
    });
}

$(document).ready(function() {
    connect();
}
);