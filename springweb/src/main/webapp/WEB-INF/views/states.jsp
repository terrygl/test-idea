<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>    
	
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Customers</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/site.css"/>	
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script>
	$(document).ready(
			function() {

				$("#country").selectmenu({
					select: function () {
						refreshStateList();
					}}						
				);

				$.getJSON('getCountriesDropDownData', function(data) {
					var html = '';
					var len = data.length;
					if (len > 0) {
						html += '<option value="' + data[0].id + '" selected>' + data[0].name + '</option>';
						for (var i = 1; i < len; i++) {
							html += '<option value="' + data[i].id + ' ">'
									+ data[i].name + '</option>';
						}
						$('#country').append(html);		
					}
				});
			});

	function refreshStateList() {
		$('#state_table').hide();
		$('#state_table TBODY tr').remove();
		countryId = $("#country option:selected").val();

		$.ajax({
					dataType : "json",
					url : "json/getStates",
					data : {
						countryId : countryId
					},
					success : function(data) {

						var html = "";
						var len = data.length;
						if (len > 0) {
							for (var i = 0; i < len; i++) {
								html += "<tr><td>" + data[i].id + "</td><td>"
										+ data[i].name + "</td><td>" + data[i].code
										+ "</td></tr>";
							}
						} else {
							html="<tr><td colspan=\"3\">No results found.</td></tr>";
						}
						$('#table_contain H1').html($("#country option:selected").text());	
						$('#state_table TBODY').html(html);	
						$('#state_table tr:even' ).css( "background-color", "#bbbbff" );
						$('#state_table tr:odd' ).css( "background-color", "#ffeeee" );
						$('#state_table').show();
						
					}
				});
		event.preventDefault();
	}
</script>
</head>
<body>
	<header>Simple AJAX Data Fetch with JQuery UI SelectMenu</header>
	<p id="description">An example of a simple JQuery UI SelectMenu component that fetches a list of states/provinces 
	depending on the selected country.</p>	
	<p id="description">When a country is selected from the SelectMenu component, an AJAX call is made to fetch a list of states. 
	The state list is returned as JSON data.</p>
	<p><a href="index.jsp">Return to Home</a></p>
	<form id="countrySelectForm">
		<fieldset>
			<label for="country">Country</label><select name="country" id="country" class="ui-select"></select><br />
		</fieldset>
	</form>
	<hr />
	<div class="table-contain">
		<h1 id="selected_state">&nbsp;&nbsp;</h1>
		<table id="state_table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>ISO Code</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>	

</body>
</html>