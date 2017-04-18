$(document).ready(function () {
    $("#application-form").on("change", function () {
        var fromDate = $("#from-date").val();
        var toDate = $("#to-date").val();

        if (validAppDates(fromDate, toDate)) {
            $("#new-app-error").text("");
        } else {
            $("#new-app-error").text("Fill up date-fields correctly");
        }
    })
})

function openAppForm() {

    $("#start-app-btn").slideUp("slow", function () {
    });

    $("#app-form").fadeIn("slow", function () {
    });

}

function closeAppForm() {
    $("#app-form").fadeOut("slow", function () {
        $("#start-app-btn").slideDown("slow", function () {
        });
        document.forms.application.reset();
    });
}

function addApp(userId) {
    var numberOfGuests = $("#capacity").val() || "1";
    var classOfRoom = $("#classOfRoom").val() || "STANDARD";
    var fromDate = $("#from-date").val();
    var toDate = $("#to-date").val();
    var comment = $("#comment").val();

    if (validAppDates(fromDate, toDate)) {
        $("#new-app-error").text("");
        $.ajax({
            url: "/room/app",
            cache: false,
            type: 'POST',
            data: {
                userId: userId,
                numberOfGuests: numberOfGuests,
                classOfRoom: classOfRoom,
                fromDate: fromDate,
                toDate: toDate,
                comment: comment
            }
        });

        closeAppForm();

    } else {
        $("#new-app-error").text("Fill up date-fields correctly");
    }
}

function validAppDates(from, to) {
    if (from && to) {
        if (new Date(to) > new Date(from)) {
            return true;
        }
        return false;
    }
    return false;
}

function addAppRow(app) {

    var td_userId = "<td>" + app.user.id + "</td>";
    var td_numOfGuests = "<td>" + app.numberOfGuests + "</td>";
    var td_from = "<td>" + getDate(app.checkInDate) + "</td>";
    var td_to = "<td>" + getDate(app.checkOutDate) + "</td>";
    var td_class = "<td>" + app.classOfRoom + "</td>";
    var td_comment = "<td>" + app.comment + "</td>";
    $("#newApplicationsList").append("<tr id='app" + app.id +
        "' data-target='createAppResponseModal' onclick='resolveApp(this.id)'>"
        + td_userId + td_numOfGuests + td_from + td_to + td_class + td_comment + "</tr>");
}