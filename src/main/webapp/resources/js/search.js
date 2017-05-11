$(document).ready(function () {
    $("#price-value").text(document.forms.search.maxPrice.value);
});

function openSearchForm() {

    $("#start-search-btn").slideUp();

    $("#search-form").fadeIn("slow");

}

function closeSearchForm() {
    $("#rooms").fadeOut("slow");
    $("#no-result").text("");
    $("#search-form").fadeOut("slow", function () {
        $("#start-search-btn").slideDown("slow", function () {
        });
        document.forms.search.reset();
    });
}

function searchRooms() {
    $("#price-value").text(document.forms.search.maxPrice.value);
    if (validDates($("#search-from-date").val(), $("#search-to-date").val())) {
        $("#search-error").text("");
        $.ajax({
            url: "/room/search",
            cache: false,
            type: 'GET',
            data: {
                classOfRoom: $("#search-classOfRoom").val() || "",
                capacity: $("#search-capacity").val() || 0,
                from: $("#search-from-date").val() || "",
                to: $("#search-to-date").val() || "",
                maxPrice: $("#search-max-price").val() || 0,
                sort: $("#sortBy").val() || "down"
            },
            success: function (result) {
                if(result.length == 0){
                    $("#rooms tr").remove();
                    $("#no-result").text("Sorry, nothing was found by your request, try other options.");
                }
                else {
                    $("#no-result").text("");
                    fillResultTable(result);
                }
            }
        });
    } else {
        $("#search-error").text("Wrong dates.");
    }
}

function fillResultTable(rooms) {
    $("#rooms tr").remove();
    rooms.forEach(function (item, i, rooms) {
        var td_img = "<td><img class='materialboxed' width='130' height='100' src=" + item.mainPhoto + ">" + "</td>";
        var td_data = "<td> <b>Class: </b>" + item.classOfRoom + "<br><b> Number of beds: </b>" + item.capacity + "<br><b> Price per night: </b>"
            + item.price + "$ </td>";
        $("#rooms").append(
            "<tr onclick='showRoom(" + item.id + ")'>" + td_img + td_data
            + "</tr>").show('slow');
    });
}

function showRoom(id) {
    window.location.href = "/room/show?roomId=" + id;
}

function validDates(from, to) {
    if (from && to) {
        if (to <= from) {
            return false;
        }
        return true;
    }

    return true;
}