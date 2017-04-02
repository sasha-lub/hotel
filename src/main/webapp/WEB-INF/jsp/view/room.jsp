<%--@elvariable id="user" type="model.User"--%>
<%--@elvariable id="room" type="model.Room"--%>
<%--@elvariable id="recalls" type="java.util.List<model.Recall>"--%>
<%--@elvariable id="appId" type="int"--%>

<template:main htmlTitle="hello" >
	<jsp:attribute name="activeLink">room</jsp:attribute>
	<jsp:body>

<div class="block">
        <div class="container">
			<br><br>
            <div class="row">
            	<div class="col s12 m12 l6">
            		<img class="materialboxed" width="500"
								src="${room.mainPhoto}">
                </div>
                <div class="col s12 m12 l6">
                            <p class="flow-text">
								<b><spring:message code='room.num_of_beds'/></b>${room.capacity}</p>
                            <p class="flow-text">
								<b><spring:message code='room.class'/></b>${room.classOfRoom}</p>
                            <p class="flow-text">
								<b><spring:message code='room.rate'/></b>${room.avgRating}</p>
                            <p class="flow-text">
								<b><spring:message code='room.price'/></b><span id="room-price">${room.price}</span>$</p>
                             <c:if
								test="${sessionScope.containsKey('user')}">
                             <c:if test="${user.role == 'CLIENT'}">
                 <a class="waves-effect waves-light btn-large orange"
										id="reserv-book-btn" data-target="reservation-modal"
										onclick="openReservModal()"><i
										class="material-icons right">hotel</i><spring:message code='room.reserve'/></a>
								</c:if>
								
                             <c:if test="${user.role == 'ADMIN'}">
                              <a
										class="waves-effect waves-light btn-large orange"
										id="recomend-book-btn" data-target="recomend-modal"
										onclick="openRecomendModal()"><i
										class="material-icons right">hotel</i><spring:message code='room.recomend'/></a>
							 </c:if>
								</c:if>
								
							<c:if test="${!sessionScope.containsKey('user')}">
							<spring:message code='room.please'/>
							</c:if>
                </div>
            </div>
            <br>
                                        <p class="flow-text">${room.description}</p>
                                        <br>
                <h4><spring:message code='room.photos'/></h4>
            <div class="row">
                <c:forEach items="${room.photos}" var="photo">
                <div class="col l3 m6 s12">
                <img class="materialboxed" width="250" height="150"
									src="${photo}"><br>
                </div>
            	</c:forEach>
                </div>
            <h4><spring:message code='room.recalls'/></h4>
			  <ul class="collection">
                <c:forEach items="${recalls}" var="recall">
      			<li class="collection-item"><b>${recall.rate}</b><i
								class="material-icons prefix">star</i><br>  ${recall.comment}</li>
					
            </c:forEach>
    		</ul>
        </div>
        </div>

        <div id="reservation-modal" class="modal large">
			<div class="modal-content">
				<h4><spring:message code='room.confirm'/></h4>

					<div>
						<p><spring:message code='room.you_are_going'/></p>
					<b><spring:message code='room.class'/></b>${room.classOfRoom}<br>
					<b><spring:message code='room.num_of_beds'/></b>${room.capacity}<br>
					<b><spring:message code='form.dates'/></b>
          <div class="row">
          <form name="reserve" id="reserv-form"
								onchange="checkIfAvailable(${room.id})">
            <div class="input-field col s4">
            	<i class="material-icons prefix">today</i>            	
                <input id="reserv-from-date" name="fromDate" type="date"
										class="datepicker">
                <label for="reserv-from-date"></label>
            </div>

           <div class="input-field col s4">
            	<i class="material-icons prefix">today</i>
                <input id="reserv-to-date" name="toDate" type="date"
										class="datepicker">
                <label for="reserv-to-date"></label>
            </div>
                </form>
			</div>
					<b><span id="general-price"><spring:message code='room.fill_up_both'/></span></b>
					<p id="reserv-form-error" class="error"></p>
					<p id="reserv-form-ok" class="ok"></p>					
					</div>
					
					<div class="modal-footer">
					<a class="waves-effect waves-light btn orange right"
							id="submit-registration"
							onclick="confirmReserve(${user.id}, ${room.id} )"><i
							class="material-icons left">done</i><spring:message code='room.conf'/></a> 
					<a class="waves-effect waves-light btn white orange-text right"
							onclick="closeReservModal()"><i class="material-icons left">cancel</i><spring:message code='form.cancel'/></a>
    				</div>
			</div>
		</div>
		
		<div id="recomend-modal" class="modal">
			<div class="modal-content">
				<h5><spring:message code='room.confirm'/></h5>
				<div class="input-field">
            	<i class="material-icons prefix">textsms</i>
            	<textarea id="recomend-comment"
							class="materialize-textarea"></textarea>
                <label for="recomend-comment"><spring:message code='room.additional_comment'/></label>
            </div>          
			</div>
					<div class="modal-footer">
					<a class="waves-effect waves-light btn orange right"
						id="submit-registration"
						onclick="sendAppResponse(${appId}, ${room.id})"><i
						class="material-icons left">done</i><spring:message code='room.conf'/></a> 
					<a
						class="waves-effect waves-light btn white orange-text right modal-close"><i
						class="material-icons left">cancel</i><spring:message code='form.cancel'/></a>
    				</div>
</div>
    </jsp:body>
	</template:main>