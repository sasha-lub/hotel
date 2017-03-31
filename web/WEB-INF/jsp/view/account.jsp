<%--@elvariable id="user" type="model.User"--%>
<%--@elvariable id="apps" type="java.util.List<model.Application>"--%>
<%--@elvariable id="reserves" type="java.util.List<model.Reservation>"--%>

<template:main htmlTitle="hello" >
  <jsp:attribute name="activeLink">my cabinet</jsp:attribute>
      <jsp:body>
        <div class="col s12">
        <div class="row container">
        <h2 class="red-text">
          <spring:message code='cabinet.hello' />
          ${user.name}!
        </h2>
        <ul class="tabs">
          <li class="tab col s4">
            <a href="#apps">
              <fmt:message
                key='cabinet.applications' />
            </a>
          </li>
          <li class="tab col s4">
            <a href="#reservs">
              <fmt:message
                key='cabinet.reservations' />
            </a>
          </li>
          <li class="tab col s4">
            <a href="#info">
              <fmt:message
                key='cabinet.info' />
            </a>
          </li>
        </ul>


        <div id="apps" class="col s12">
          <h5 class="left">
            <spring:message code='cabinet.app_inf' />
          </h5>
          <br><br><br><br>
          <c:if test="${empty apps}">
            <h2 align="center">
              <a class="waves-effect waves-light btn-large red center"
                href="/index#app">
                <i
                  class="material-icons right">note_add</i>
                <spring:message code='cabinet.first_app' />
              </a>
            </h2>
          </c:if>
          <c:forEach items="${apps}" var="app">
            <div class="col s12 m12">
              <div class="card blue lighten-5">
                <div class="card-content">
                  <p>
                    <b>
                      <spring:message code='cabinet.num_of_guests' />
                    </b>
                    ${app.numberOfGuests}<br>
                    <b>
                      <spring:message code='cabinet.checkin' />
                    </b>
                    ${app.checkInDate}<br>
                    <b>
                      <spring:message code='cabinet.checkout' />
                    </b>
                    ${app.checkOutDate}<br>
                    <b>
                      <spring:message code='cabinet.class' />
                    </b>
                    ${app.classOfRoom}<br>
                    <b>
                      <spring:message code='cabinet.comment' />
                    </b>
                    ${app.comment}<br>
                  </p>
                </div>
                <div class="card-action">
                  <c:if test="${empty app.response}">
                    <p>
                      <spring:message code='cabinet.place_for_response' />
                    </p>
                  </c:if>
                  <c:if test="${!empty app.response}">
                    <p>
                     <fmt:message
                          key='cabinet.we_are_sure' />
                      <a class="blue-text"
                        href="/room/show?roomId=${app.response.roomId}">
                        <fmt:message
                          key='cabinet.this_room' />
                      </a>
                      <spring:message code='cabinet.is_the_best' />
                      <br>
                      <b>
                        <spring:message code='cabinet.comment' />
                      </b>
                      ${app.response.comment}
                    </p>
                  </c:if>
                </div>
              </div>
            </div>
          </c:forEach>
        </div>

        <!-- reservations tab-->
        <div id="reservs" class="col s12">
          <h5 class="center">
            <spring:message code='cabinet.manage_reservs' />
          </h5>
          <br><br>
          <div class="row">
            <c:if test="${empty reserves}">
              <h2 align="center">
                <a class="waves-effect waves-light btn-large red"
                  href="Controller?command=index#search">
                  <i
                    class="material-icons right">search</i>
                  <spring:message code='cabinet.search_for_room' />
                </a>
              </h2>
            </c:if>
            <c:forEach items="${reserves}" var="reservation">

            <c:if test="${reservation.status == 'UNPAID'}">
            <c:set var="color" scope="page" value="red"/>
              </c:if>
              <c:if test="${reservation.status == 'PAID'}">
                <c:set var="color" scope="page" value="yellow"/>
                </c:if>
                <c:if test="${reservation.status == 'CONFIRMED'}">
                  <c:set var="color" scope="page" value="green"/>
                  </c:if>
            <!-- card -->
            <div class="col s12 m6 l4">

              <div class="card lighten-4 ${color}">

                    <div id="reservation-record-${reservation.id}"
                         class="card-content">
                <span class="card-title"><fmt:message
                        key='cabinet.reserv' /> <a
                        class="right grey-text delete-btn"
                        data-target="cancel-reservation-modal"
                        onclick="openConfirmDeleteModal(${reservation.id})"> <i
                        class="material-icons">close</i></a></span>
                      <p>
                      <b><fmt:message key='cabinet.checkin' /> </b><span>${reservation.checkInDate}</span><br>
                      <b><fmt:message key='cabinet.checkout' /> </b><span>${reservation.checkOutDate}</span><br>
                      <b><fmt:message key='cabinet.deadline' /> </b><span><my:datetime>${reservation.paymentDeadline}</my:datetime></span><br>
                      <b><fmt:message key='cabinet.total_price' /> </b><span>${reservation.price}</span><br>
                      <b><fmt:message key='cabinet.status' /> </b><span id='reserve-record-status-${reservation.id}'>${reservation.status}</span><br>
                      </p>
                      <div class="card-action">
                        <div class='row'>
                          <a class="waves-effect waves-red btn white red-text"
                             href="/room/show?roomId=${reservation.room.id}"><fmt:message
                                  key='cabinet.room' /></a>
                          <c:if test="${reservation.status != 'CONFIRMED'}">
                            <a
                                    class="waves-effect waves-red btn white red-text dropdown-button btn"
                                    data-activates='status${reservation.id}'
                                    data-beloworigin="true"><fmt:message
                                    key='cabinet.status' /></a>
                          </c:if>
                          <c:if test="${reservation.status == 'CONFIRMED'}">
                            <a
                                    class="waves-effect waves-red btn white red-text"
                                    data-target="rating-modal"
                                    onclick="openRateModal(${reservation.room.id})"><fmt:message
                                    key='cabinet.rate' /></a>
                          </c:if>
                          <ul id='status${reservation.id}'
                              class='dropdown-content'>
                            <li><a onclick="changeStatus('PAID', ${reservation.id})"><fmt:message
                                    key='cabinet.paid' /></a></li>
                            <li><a onclick="changeStatus('UNPAID', ${reservation.id})"><fmt:message
                                    key='cabinet.unpaid' /></a></li>
                          </ul>
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
                </c:forEach>

                  <!--cancel modal-->
                  <div id="cancel-reservation-modal" class="modal">
            <div class="modal-content">
            <h5>
            <spring:message code='cabinet.delete' />
            </h5>
            <input type="hidden" id="cancel-reserve-id" name="reserveId"
              value="">
            </div>
            <span class = "error" id = "remove-reserv-error"></span>
            <div class="modal-footer">
            <a class="waves-effect waves-light btn orange right"
              id="submit-registration" onclick="deleteReserve()"><i
              class="material-icons left">done</i>
            <spring:message code='cabinet.yes' /></a>
            <a
              class="waves-effect waves-light btn white orange-text right modal-close"><i
              class="material-icons left">cancel</i>
            <spring:message code='cabinet.no' /></a>
            </div>
            </div>

                  <!--rating modal-->
            <div id="rating-modal" class="modal">
            <div class="modal-content">
            <h5>
            <spring:message code='cabinet.recall_inf' />
            </h5>
            <form id="rate-form" name="rating">
            <input type="hidden" id="user-id" name="userId" value="${user.id}">
            <input type="hidden" id="room-id" name="roomId" value="">
            <div class="row">
            <div class="input-field">
            <i class="material-icons prefix">star</i>
            <select id="rate">
            <option value="" disabled selected><fmt:message
              key='cabinet.rate' /></option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            </select>
            <label for="rate"></label>
            </div>
            <div class="input-field">
            <i class="material-icons prefix">textsms</i>
            <textarea id="recall" class="materialize-textarea"></textarea>
            <label for="recall"><fmt:message
              key='cabinet.recall' /> </label>
            </div>
            </div>
            <a class="waves-effect waves-light btn amber"
              onclick="sendRecall()"><i
              class="material-icons left">done</i>
            <spring:message code='main_app.send' /></a>
            <a class="waves-effect waves-light btn white amber-text"
              onclick="closeRateModal()"><i class="material-icons left">cancel</i>
            <spring:message code='form.cancel' /></a>
            </form>
            </div>
            </div>
          </div></div>
            <!--My info tab -->
            <div id="info" class="col s12">
              <h5 class="right">
                <spring:message code='cabinet.manage_info' />
              </h5>
              <br><br>
              <table>
                <tr>
                  <td>
                    <b><spring:message code='cabinet.name' /></b> <span id = "name">${user.name}</span>
                  </td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-name-modal"
                      onclick="openNameModal()"><i class="material-icons left">edit</i></a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <b><spring:message code='cabinet.phone' /></b> <span id = "phone">${user.phone}</span>
                  </td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-phone-modal"
                      onclick="openPhoneModal()"><i class="material-icons left">edit</i></a>
                  <td>
                </tr>
                <tr>
                  <td>
                    <b><spring:message code='cabinet.email' /></b> <span id = "email">${user.email}</span>
                  </td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-email-modal"
                      onclick="openEmailModal()"><i class="material-icons left">edit</i></a>
                  </td>
                </tr>
                <tr>
                  <td><b><spring:message code='cabinet.password' /></b></td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-pass-modal"
                      onclick="openPassModal()"><i class="material-icons left">edit</i></a>
                  </td>
                </tr>
              </table>
              
              <div id="change-name-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <spring:message code='cabinet.change_name' />
                  </h4>
                  <form id="change-name-form" name="change-name-form">
                    <div class="input-field">
                      <i class="material-icons prefix">account_circle</i> <input
                        id="change-name" name="inputName" type="text" class="validate"
                        pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$">
                      <label
                        data-error="Enter valid name. Use only сyrillic or roman alphabet"
                        for="change-name">
                        <spring:message code='sign_up.name' />
                      </label>
                      <span
                        class="error" id="name-error"> </span>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="change-name-password" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="change-name-password">
                        <fmt:message
                          key='sign_up.password' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-name-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changeName()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <spring:message code='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
              
              <div id="change-pass-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <spring:message code='cabinet.change_pass' />
                  </h4>
                  <form id="change-pass-form" name="change-pass-form">
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="old-pass" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="old-pass">
                        <fmt:message
                          key='cabinet.old_pass' />
                      </label>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="new-pass" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="new-pass">
                        <fmt:message
                          key='cabinet.new_pass' />
                      </label>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="conf-new-pass" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="conf-new-pass">
                        <fmt:message
                          key='cabinet.new_pass_conf' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-pass-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changePass()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <spring:message code='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
              
              <div id="change-phone-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <spring:message code='cabinet.change_phone' />
                  </h4>
                  <form id="change-phone-form" name="change-phone-form">
                    <div class="input-field">
                      <i class="material-icons prefix">phone</i> <input
                        id="change-phone" name="inputPhone" type="text"
                        class="validate" pattern="[+]?[0-9 -]{6,17}"> 
                      <label
                        data-error="Enter valid phone. Example: +38 050 111 22 33"
                        for="change-phone">
                        <spring:message code='sign_up.phone' />
                      </label>
                      <span class="error" id="phone-error"> </span>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="change-phone-password" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="change-phone-password">
                        <fmt:message
                          key='sign_up.password' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-phone-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changePhone()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <spring:message code='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
              
              <div id="change-email-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <spring:message code='cabinet.change_email' />
                  </h4>
                  <form id="change-email-form" name="change-email-form">
                    <div class="input-field">
                      <i class="material-icons prefix">email</i> <input
                        id="change-email" name="inputEmail" type="text"
                        pattern="[a-zA-Z0-9._-]*@[a-zA-Z0-9]+\.[a-zA-Z0-9]+(.[a-zA-Z0-9]+)?"
                        class="validate"> 
                      <label data-error="Enter valid email"
                        for="change-email">
                        <spring:message code='sign_up.email' />
                      </label>
                      <span class="error" id="email-error"> </span>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="change-email-password" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="change-email-password">
                        <fmt:message
                          key='sign_up.password' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-email-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changeEmail()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <spring:message code='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </jsp:body>
    </template:main>