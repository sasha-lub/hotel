<template:main htmlTitle="hello" >
	<jsp:attribute name="activeLink">index</jsp:attribute>
	<jsp:body>
				<div class="parallax-container">
					<div class="parallax">
						<img src="/resources/images/mainView1.jpg">
					</div>
				</div>

				<div class="section white">
					<div class="row container">
						<h2 class="header"><spring:message code='main.welcome'/></h2>
						<c:if test="${sessionScope.containsKey('user')}">
							<p class="grey-text text-darken-3 lighten-3"><spring:message code='main.dear_friend'/><a
									href="#search"><spring:message code='main.search_yourself'/></a><spring:message code='main.or'/>
								<a href="#app"><spring:message code='main.let_us'/></a><spring:message code='main.hope'/></p>
						</c:if>

						<c:if test="${!sessionScope.containsKey('user')}">
							<p class="grey-text text-darken-3 lighten-3"><spring:message code='main.dear_friend'/> <a
									href="#search"><spring:message code='main.search_yourself'/></a>
								<spring:message code='main.more_features'/></p>
						</c:if>
					</div>
				</div>
				<div class="parallax-container">
					<div class="parallax">
						<img src="/resources/images/mainView2.jpg">
					</div>
				</div>

				<div class="section white" id="search">
					<div class="row container">
						<br><br>
						<h2 class="header indigo-text text-accent-1"><spring:message code='main_search.header'/></h2>
						<p class="grey-text text-darken-3 lighten-3"><spring:message code='main_search.here_you_can'/></p>
						<a class="waves-effect waves-light btn indigo"
						   id="start-search-btn" onclick="openSearchForm()"><i
								class="material-icons right">note_add</i><spring:message code='main.start'/></a>
						<div id="search-form" class="container" style="display: none;">
							<form name="search" onchange="searchRooms()" class="validate">
								<div class="row">

									<div class="input-field col s6">
										<i class="material-icons prefix">perm_identity</i>
										<select id="search-capacity">
											<option value="" disabled selected><spring:message code='form.number_of_guests'/></option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
										<label for="search-capacity"></label>
									</div>

									<div class="input-field col s6">
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
								</div>

								<span><spring:message code='form.dates'/></span>
								<div class="row">
									<div class="input-field col s6">
										<i class="material-icons prefix">today</i>
										<input id="search-from-date" name="fromDate" type="date"
											   class="datepicker">
										<label for="search-from-date"></label>
									</div>

									<div class="input-field col s6">
										<i class="material-icons prefix">today</i>
										<input id="search-to-date" name="toDate" type="date"
											   class="datepicker">
										<label for="search-to-date"></label>
									</div>
								</div>
								<span id="search-error" class="error"></span><br>

								<span><spring:message code='form.max_price'/><span id='price-value'></span> $</span>
								<div class="input-field">
									<i class="material-icons prefix">attach_money</i>
									<input id="search-max-price" name="maxPrice"
										   type="range" min="50" max="5000">
								</div>

								<div class='row'>
									<div class="input-field col s6">
										<i class="material-icons prefix">find_replace</i>
										<select id="sortBy">
											<option value="up"><spring:message code='form.sort_by_price_up_down'/></option>
											<option value="down"><spring:message code='form.sort_by_price_down_up'/></option>
											<option value="rating"><spring:message code='form.sort_by_rating'/></option>
										</select>
									</div>
								</div>
								<div>
								</div>
								<a class="waves-effect waves-light btn indigo"
								   id="start-search" onclick="searchRooms()"><i
										class="material-icons left">done</i><spring:message code='form.search'/></a>
								<a class="waves-effect waves-light btn white indigo-text"
								   onclick="closeSearchForm()" id="closeSearch"><i
										class="material-icons left">cancel</i><spring:message code='form.cancel'/></a>
							</form>
						</div>
					</div>

					<div class="indigo lighten-4 container">
						<table class="highlight fixed" id="rooms">
							<col width="150px" />
							<col />
							<tbody>

							</tbody>
						</table>
					</div>
				</div>

				<div class="parallax-container">
					<div class="parallax">
						<img src="/resources/images/mainView5.jpg">
					</div>
				</div>

				<c:if test="${sessionScope.containsKey('user')}">
					<div class="section white" id="app">
						<div class="row container">
							<br><br>
							<h2 class="header amber-text text-accent-3"><spring:message code='main_app.header'/></h2>
							<p class="grey-text text-darken-3 lighten-3">
								<spring:message code='main_app.here_you_can'/></p>
							<a class="waves-effect waves-light btn amber" id="start-app-btn"
							   onclick="openAppForm()"><i class="material-icons right">note_add</i>
								<spring:message code='main.start'/></a>
							<div id="app-form" class="container" style="display: none;">
								<form id="application-form" name="application">
									<div class="row">

										<div class="input-field col s6">
											<i class="material-icons prefix">perm_identity</i>
											<select id="capacity">
												<option value="" disabled selected><spring:message code='form.number_of_guests'/></option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
											<label for="capacity"></label>
										</div>

										<div class="input-field col s6">
											<i class="material-icons prefix">stars</i>
											<select id="classOfRoom">
												<option value="" disabled selected><spring:message code='form.class_of_room'/></option>
												<option value="ECONOMIC"><spring:message code='class_of_room.economic'/></option>
												<option value="STANDARD"><spring:message code='class_of_room.standard'/></option>
												<option value="FAMILY"><spring:message code='class_of_room.family'/></option>
												<option value="HONEYMOON"><spring:message code='class_of_room.honeymoon'/></option>
												<option value="DE_LUX"><spring:message code='class_of_room.delux'/></option>
												<option value="BUSINESS"><spring:message code='class_of_room.business'/></option>
												<option value="PRESIDENT"><spring:message code='class_of_room.president'/></option>
											</select>
											<label for="classOfRoom"></label>
										</div>
									</div>

									<span><spring:message code='form.dates'/></span>
									<div class="row">
										<div class="input-field col s6">
											<i class="material-icons prefix">today</i>
											<input id="from-date" name="fromDate" type="date"
												   class="datepicker">
											<label for="from-date"></label>
										</div>

										<div class="input-field col s6">
											<i class="material-icons prefix">today</i>
											<input id="to-date" name="toDate" type="date"
												   class="datepicker">
											<label for="to-date"></label>
										</div>

									</div>
									<span id="new-app-error" class="error"></span><br>
									<div class="input-field">
										<i class="material-icons prefix">textsms</i>
										<textarea id="comment" class="materialize-textarea"></textarea>
										<label for="comment"><spring:message code='main_app.additional_text'/></label>
									</div>
									<div>
									</div>
									<a class="waves-effect waves-light btn amber"
									   id="submit-registration" onclick="addApp(${user.id})"><i
											class="material-icons left">done</i><spring:message code='main_app.send'/></a>
									<a class="waves-effect waves-light btn white amber-text"
									   onclick="closeAppForm()"><i class="material-icons left">cancel</i>
										<spring:message code='form.cancel'/></a>
								</form>
							</div>
						</div>

					</div>

					<div class="parallax-container">
						<div class="parallax">
							<img src="/resources/images/mainView4.jpg">
						</div>
					</div>
				</c:if>

			</jsp:body>
		</template:main>