<%--@elvariable id="user" type="model.User"--%>
<%--@elvariable id="apps" type="java.util.List<model.Application>"--%>
<%--@elvariable id="paidReservations" type="java.util.List<model.Reservation>"--%>
<%--@elvariable id="unpaidReservations" type="java.util.List<model.Reservation>"--%>
<%--@elvariable id="confirmedReservations" type="java.util.List<model.Reservation>"--%>
<%--@elvariable id="users" type="java.util.List<model.User>"--%>


<template:main htmlTitle="hello">
    <jsp:attribute name="activeLink">admin</jsp:attribute>

    <jsp:body>

        <div class="row">
            <div class="col s12">
                <ul class="tabs">
                    <li class="tab col s4"><a href="#apps"><spring:message code='admin.apps'/></a></li>
                    <li class="tab col s4"><a href="#reservs"><spring:message code='admin.reservs'/></a></li>
                    <li class="tab col s4"><a href="#users"><spring:message code='admin.users'/></a></li>
                </ul>
            </div>
            <div class="container">
                <div id="apps" class="col s12">
                    <br><br>
                    <table class="highlight" id="newApplicationsList">
                        <thead>
                        <tr>
                            <th><spring:message code='admin.user_id'/></th>
                            <th><spring:message code='admin.num_of_guests'/></th>
                            <th><spring:message code='admin.checkin'/></th>
                            <th><spring:message code='admin.checkout'/></th>
                            <th><spring:message code='admin.class'/></th>
                            <th><spring:message code='admin.comment'/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${apps}" var="app">
                            <tr id="app${app.id}" data-target="createAppResponseModal" onclick="resolveApp(this.id)">
                                <td>${app.user.id}</td>
                                <td>${app.numberOfGuests}</td>
                                <td>${app.checkInDate}</td>
                                <td>${app.checkOutDate}</td>
                                <td>${app.classOfRoom}</td>
                                <td>${app.comment}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="container">
                <div id="reservs" class="col s12">
                    <br><br>
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="yellow accent-1 collapsible-header active"><i class="material-icons">done</i>
                                <spring:message code='admin.paid'/><b> <span
                                        id="paid-counter-server">(${paidReservations.size()})</span><span
                                        id="paid-counter"></span></b></div>
                            <div class="collapsible-body">
                                <table class="highlight" id="paid-reservationsList">
                                    <thead>
                                    <tr>
                                        <th><spring:message code='admin.user_id'/></th>
                                        <th><spring:message code='admin.room_id'/></th>
                                        <th><spring:message code='admin.checkin'/></th>
                                        <th><spring:message code='admin.checkout'/></th>
                                        <th><spring:message code='admin.deadline'/></th>
                                        <th><spring:message code='admin.total_price'/></th>
                                        <th><spring:message code='admin.status'/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${paidReservations}" var="reserve">
                                        <tr id="${reserve.id}" data-target="confirmPaymentModal"
                                            onclick="openConfirmPaymentModal(this.id)">
                                            <td>${reserve.user.id}</td>
                                            <td>${reserve.room.id}</td>
                                            <td>${reserve.checkInDate}</td>
                                            <td>${reserve.checkOutDate}</td>
                                            <td>${reserve.paymentDeadline}</td>
                                            <td>${reserve.price}</td>
                                            <td>${reserve.status}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </li>
                        <li>
                            <div class="red accent-1 collapsible-header active"><i class="material-icons">av_timer</i>
                                <spring:message code='admin.unpaid'/> <b> <span
                                        id="unpaid-counter-server">(${unpaidReservations.size()})</span><span
                                        id="unpaid-counter"></span></b></div>
                            <div class="collapsible-body">
                                <table class="highlight" id="reservationsList">
                                    <thead>
                                    <tr>
                                        <th><spring:message code='admin.user_id'/></th>
                                        <th><spring:message code='admin.room_id'/></th>
                                        <th><spring:message code='admin.checkin'/></th>
                                        <th><spring:message code='admin.checkout'/></th>
                                        <th><spring:message code='admin.deadline'/></th>
                                        <th><spring:message code='admin.total_price'/></th>
                                        <th><spring:message code='admin.status'/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${unpaidReservations}" var="reserve">
                                        <tr id="${reserve.id}" data-target="confirmPaymentModal"
                                            onclick="openConfirmPaymentModal(this.id)">

                                            <td>${reserve.user.id}</td>
                                            <td>${reserve.room.id}</td>
                                            <td>${reserve.checkInDate}</td>
                                            <td>${reserve.checkOutDate}</td>
                                            <td>${reserve.paymentDeadline}</td>
                                            <td>${reserve.price}</td>
                                            <td>${reserve.status}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </li>
                        <li>
                            <div class="green accent-2 collapsible-header "><i class="material-icons">done_all</i>
                                <spring:message code='admin.confirmed'/><b> <span
                                        id="conf-counter-server">(${confirmedReservations.size()})</span><span
                                        id="conf-counter"></span></b></div>
                            <div class="collapsible-body">
                                <table class="highlight" id="confirmed-reservationsList">
                                    <thead>
                                    <tr>
                                        <th><spring:message code='admin.user_id'/></th>
                                        <th><spring:message code='admin.room_id'/></th>
                                        <th><spring:message code='admin.checkin'/></th>
                                        <th><spring:message code='admin.checkout'/></th>
                                        <th><spring:message code='admin.deadline'/></th>
                                        <th><spring:message code='admin.total_price'/></th>
                                        <th><spring:message code='admin.status'/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${confirmedReservations}" var="reserve">
                                        <tr id="${reserve.id}">
                                            <td>${reserve.user.id}</td>
                                            <td>${reserve.room.id}</td>
                                            <td>${reserve.checkInDate}</td>
                                            <td>${reserve.checkOutDate}</td>
                                            <td>${reserve.paymentDeadline}</td>
                                            <td>${reserve.price}</td>
                                            <td>${reserve.status}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </li>
                    </ul>

                </div>

            </div>
            <div class="container">

                <div id="users" class="col s12">
                    <table class="highlight" id="usersList">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><spring:message code='admin.name'/></th>
                            <th><spring:message code='admin.email'/></th>
                            <th><spring:message code='admin.phone'/></th>
                            <th><spring:message code='admin.role'/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.phone}</td>
                                <td>${user.role}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div id="createAppResponseModal" class="modal bottom-sheet">
            <div class="modal-content">
                <h5><spring:message code='admin.choose_room'/></h5>
                <form id="search-form" name="search" onchange="searchRooms()" class="validate">
                    <div class="row">

                        <div class="input-field col s4 m3 l2">
                            <i class="material-icons prefix">perm_identity</i>
                            <select id="search-capacity">
                                <option value="" disabled selected>Number of guests:</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <label for="search-capacity"></label>
                        </div>

                        <div class="input-field col s4 m3 l2">
                            <i class="material-icons prefix">stars</i>
                            <select id="search-classOfRoom">
                                <option value="" disabled selected><spring:message code='form.class_of_room'/></option>
                                <option value="ECONOMIC"><spring:message code='class_of_room.economic'/></option>
                                <option value="STANDARD"><spring:message code='class_of_room.standard'/></option>
                                <option value="FAMILY"><spring:message code='class_of_room.family'/></option>
                                <option value="HONEYMOON"><spring:message code='class_of_room.honeymoon'/></option>
                                <option value="DE_LUX"><spring:message code='class_of_room.delux'/></option>
                                <option value="BUSINESS"><spring:message code='class_of_room.business'/></option>
                                <option value="PRESIDENT"><spring:message code='class_of_room.president'/></option>
                            </select>
                        </div>

                        <div class="input-field col s6 l2 m3">
                            <i class="material-icons prefix">find_replace</i>
                            <select id="sortBy">
                                <option value="up"><spring:message code='form.sort_by_price_up_down'/></option>
                                <option value="down"><spring:message code='form.sort_by_price_down_up'/></option>
                                <option value="rating"><spring:message code='form.sort_by_rating'/></option>
                            </select>
                        </div>

                        <div class="input-field col s6 l6 m3">
                            <i class="material-icons prefix">attach_money</i>
                            <input id="search-max-price" name="maxPrice"
                                   type="range" min="50" max="5000" value="5000">
                        </div>
                        <span class="right">Set max price: <span id='price-value'></span> $</span>
                    </div>
                    <input id="search-from-date" type="hidden">
                    <input id="search-to-date" type="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <div class="indigo lighten-4 container">
                    <table class="highlight fixed" id="rooms">
                        <col width="150px"/>
                        <col/>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>


        <div id="confirmPaymentModal" class="modal">
            <div class="modal-content">
                <h4 class="center"><spring:message code='admin.confirm_payment'/></h4>
                <input type="hidden" id="confirm-reserve-id">
                <input type="hidden" id="confirm-reserve-row">

            </div>
            <div class="modal-footer">
                <a class="waves-effect waves-light btn orange right"
                   id="submit-registration" onclick="confirmPayment()"><i
                        class="material-icons left">done</i><spring:message code='admin.yes'/></a>
                <a class="waves-effect waves-light btn white orange-text right modal-close"><i
                        class="material-icons left">cancel</i><spring:message code='admin.no'/></a>
            </div>
        </div>
    </jsp:body>

</template:main>
